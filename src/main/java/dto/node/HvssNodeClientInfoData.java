package dto.node;

import dto.node.commdata.HvssNodeCommDataObject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HvssNodeClientInfoData extends HvssNodeCommDataObject {

    public HvssNodeClientInfoData(HvssNodeClientData data){
        this.torrentSlave = data.isTorrentSlave();
        this.encodeSlave = data.isEncodeSlave();
    }

    public HvssNodeClientInfoData(HvssNodeClientInfoData data){
        this.torrentSlave = data.isTorrentSlave();
        this.encodeSlave = data.isEncodeSlave();
        this.uptime =data.uptime;
        this.startTime = data.startTime;
        this.threadCount = data.threadCount;
        this.peakThreadCount =data.peakThreadCount;
        this.availableProcessors = data.availableProcessors;
        this.systemLoad = data.systemLoad;
        this.heapMemoryUsage = data.heapMemoryUsage;
        this.heapMemoryMax = data.heapMemoryMax;
        this.nonHeapMemoryUsage = data.nonHeapMemoryUsage;
        this.nonHeapMemoryMax = data.nonHeapMemoryMax;
        this.torrentsRunning = data.torrentsRunning;
        this.torrentsTotal = data.torrentsTotal;
    }

    boolean torrentSlave;
    boolean encodeSlave;
    long uptime;
    long startTime;
    int threadCount;
    int peakThreadCount;
    int availableProcessors;
    double systemLoad;
    long heapMemoryUsage;
    long heapMemoryMax;
    long nonHeapMemoryUsage;
    long nonHeapMemoryMax;
    int torrentsRunning;
    int torrentsTotal;

}
