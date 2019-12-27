package dto.node;

import lombok.Data;

@Data
public class HvssNodeSlaveData extends HvssNodeClientInfoData {

    public HvssNodeSlaveData(HvssNodeClientInfoData data, String oid, HvssNodeClientRegisterData nodeClientRegisterData){
        super(data);
        this.oid = oid;
        this.ipAddress = nodeClientRegisterData.getIp();
        this.slavePort = nodeClientRegisterData.getPort();
        this.sslEnabled = nodeClientRegisterData.getSslEnabled();
    }

    public void updateData(HvssNodeClientInfoData hvssNodeClientInfoData){
        this.setAvailableProcessors(hvssNodeClientInfoData.getAvailableProcessors());
        this.setUptime(hvssNodeClientInfoData.getUptime());
        this.setStartTime(hvssNodeClientInfoData.getStartTime());
        this.setThreadCount(hvssNodeClientInfoData.getThreadCount());
        this.setPeakThreadCount(hvssNodeClientInfoData.getPeakThreadCount());
        this.setSystemLoad(hvssNodeClientInfoData.getSystemLoad());
        this.setHeapMemoryUsage(hvssNodeClientInfoData.getHeapMemoryUsage());
        this.setHeapMemoryMax(hvssNodeClientInfoData.getHeapMemoryMax());
        this.setNonHeapMemoryUsage(hvssNodeClientInfoData.getNonHeapMemoryUsage());
        this.setNonHeapMemoryMax(hvssNodeClientInfoData.getNonHeapMemoryMax());
        this.setTorrentsRunning(hvssNodeClientInfoData.getTorrentsRunning());
        this.setTorrentsTotal(hvssNodeClientInfoData.getTorrentsTotal());
    }

    String oid;
    String ipAddress;
    int slavePort;
    Boolean sslEnabled;
}
