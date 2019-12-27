package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import services.SlaveService;
import services.TorrentService;

@EnableAsync
public class ScheduledJobs {

    @Autowired
    SlaveService slaveService;

    @Autowired
    TorrentService torrentService;

    @Async
    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        torrentService.doTorrentMaintenance();
    }
}
