package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.node.*;
import dto.node.commdata.HvssNodeCommDataObject;
import dto.node.commdata.HvssSocketCommandDataTypeEnum;
import dto.web.HvssTorrentInfoData;
import entities.torrent.HvssTorrentDataEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Component
public class SlaveService <T extends HvssNodeCommDataObject> {

    ObjectMapper mapper = new ObjectMapper();
    Map<String, HvssNodeSlaveData> slaveClientMap = new HashMap<>();

    public HvssNodeCommData registerNode(HvssNodeCommData<HvssNodeClientRegisterData> hvssNodeCommData) {
        HvssNodeClientRegisterData regData = hvssNodeCommData.getData().get(0);
        String uuid = UUID.randomUUID().toString();
        HvssNodeClientData data = getSlaveRegisterInformation();
        slaveClientMap.put(uuid, new HvssNodeSlaveData(new HvssNodeClientInfoData(data), uuid, regData));

        return new HvssNodeCommData<>(HvssSocketCommandDataTypeEnum.CLIENT_REGISTERED, uuid, Arrays.asList(data));
    }

    public void updateSlaveNodeInformation(){

    }

    public HvssTorrentInfoData addTorrentToSlaveNode(HvssTorrentDataEntity torrentData){
        HvssNodeSlaveData slaveToSendTorrent = getLeastLoadSlaveNode();
        HvssNodeCommData torrentNewDataObject = new HvssNodeCommData();
        HvssNodeTorrentData newTorrentData = new HvssNodeTorrentData();
        torrentNewDataObject.setCommandData(HvssSocketCommandDataTypeEnum.TORRENT_NEW);
        torrentNewDataObject.setClientOid(slaveToSendTorrent.getOid());
        newTorrentData.setTorrentOid(torrentData.getUid());
        newTorrentData.setTorrentStorageFolder(torrentData.getGeneralFolderName());
        newTorrentData.setTorrentUrl(torrentData.getTorrentFileURL());
        newTorrentData.setTorrentName(torrentData.getTorrentName());
        torrentNewDataObject.setData(Arrays.asList(newTorrentData));

        HvssNodeCommData<HvssNodeTorrentInfoData> response = (HvssNodeCommData<HvssNodeTorrentInfoData>)  sendNodeServiceMessages(torrentNewDataObject, slaveToSendTorrent);
        return new HvssTorrentInfoData(slaveToSendTorrent, mapper.convertValue( response.getData().get(0), HvssNodeTorrentInfoData.class));
    }

    public HvssNodeClientData getSlaveRegisterInformation(){
        HvssNodeClientData data = new HvssNodeClientData();
        data.setEncodeSlave(true);
        data.setTorrentSlave(true);
        return data;
    }

    private HvssNodeSlaveData getLeastLoadSlaveNode(){
        updateAllSlaveNodeData();
        SortedMap<Double, String> slaveLoadScores = new TreeMap<>();
        slaveClientMap.entrySet().stream().forEach(e -> {
                double loadScore = (((e.getValue().getAvailableProcessors() * e.getValue().getSystemLoad() * e.getValue().getTorrentsRunning() - e.getValue().getTorrentsTotal()) +
                        (((e.getValue().getHeapMemoryUsage() +  e.getValue().getNonHeapMemoryUsage()) / 1024 ) / 1024 )) /
                        e.getValue().getThreadCount());
                slaveLoadScores.put(loadScore, e.getKey());
            });
        return slaveClientMap.get(slaveLoadScores.get(slaveLoadScores.firstKey()));
    }

    private void updateAllSlaveNodeData(){
        slaveClientMap.entrySet().stream().forEach(e -> {
            HvssNodeSlaveData slaveData = e.getValue();
            HvssNodeCommData updateObject = new HvssNodeCommData();
            updateObject.setCommandData(HvssSocketCommandDataTypeEnum.CLIENT_INFO);
            updateObject.setClientOid(slaveData.getOid());

            HvssNodeCommData<HvssNodeClientInfoData> response = (HvssNodeCommData<HvssNodeClientInfoData>)  sendNodeServiceMessages(updateObject, slaveData);
            slaveData.updateData(mapper.convertValue(response.getData().get(0), HvssNodeClientInfoData.class));
        });
    }

    public HvssNodeTorrentInfoData updateTorrentInfoDataFromSlave(String torrentId, HvssNodeSlaveData slaveData){
        HvssNodeCommData<HvssNodeTorrentData> data = new HvssNodeCommData<>();
        HvssNodeTorrentData torrentInfoData = new HvssNodeTorrentData();
        data.setCommandData(HvssSocketCommandDataTypeEnum.TORRENT_INFO);
        data.setClientOid(slaveData.getOid());
        torrentInfoData.setTorrentOid(torrentId);
        data.setData(Arrays.asList(torrentInfoData));
        return mapper.convertValue(sendNodeServiceMessages((HvssNodeCommData<T>) data, slaveData).getData().get(0), HvssNodeTorrentInfoData.class);
    }

    public List<HvssNodeTorrentInfoData> getAllTorrentInfoData(){
        List<HvssNodeTorrentInfoData> combinedList = new ArrayList<>();
        slaveClientMap.entrySet().stream().forEach((e) -> {
            combinedList.addAll(updateAllTorrentInfoDataFromSlave(e.getValue()));

        });
        return combinedList;
    }

    public List<HvssNodeTorrentInfoData> updateAllTorrentInfoDataFromSlave(HvssNodeSlaveData slaveData){
        List<HvssNodeTorrentInfoData> returnList = new ArrayList<>();
        HvssNodeCommData<HvssNodeTorrentData> data = new HvssNodeCommData<HvssNodeTorrentData>();
        HvssNodeTorrentData torrentInfoData = new HvssNodeTorrentData();
        data.setCommandData(HvssSocketCommandDataTypeEnum.TORRENT_INFO_ALL);
        data.setClientOid(slaveData.getOid());
        HvssNodeCommData<T> response = sendNodeServiceMessages((HvssNodeCommData<T>) data, slaveData);
        for (T object: response.getData()) {
            returnList.add(mapper.convertValue(object, HvssNodeTorrentInfoData.class));
        }
        return returnList;
    }

    public void deleteTorrent(String torrentId, HvssNodeSlaveData slaveData){
        HvssNodeCommData<HvssNodeTorrentData> data = new HvssNodeCommData<>();
        HvssNodeTorrentData torrentInfoData = new HvssNodeTorrentData();
        data.setCommandData(HvssSocketCommandDataTypeEnum.TORRENT_DELETE);
        data.setClientOid(slaveData.getOid());
        torrentInfoData.setTorrentOid(torrentId);
        data.setData(Arrays.asList(torrentInfoData));
        sendNodeServiceMessages((HvssNodeCommData<T>) data, slaveData);
    }

    private HvssNodeCommData<T> sendNodeServiceMessages(HvssNodeCommData<T> nodeCommData, HvssNodeSlaveData slaveNode) {
        String endpoint = "/node/command";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String remoteAddress = ( slaveNode.getSslEnabled() ? "https://" : "http://" ) + slaveNode.getIpAddress() + ":" + slaveNode.getSlavePort() + endpoint;
        HttpEntity<String> entity = null;
        try {
            entity = new HttpEntity<>(mapper.writeValueAsString(nodeCommData), headers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
            return (HvssNodeCommData<T>) restTemplate.exchange(remoteAddress, HttpMethod.POST, entity, new ParameterizedTypeReference<HvssNodeCommData<?>>() {}).getBody();
    }



}
