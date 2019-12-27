package services;

import dto.progress.HvssProgress;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProgressService {

    Map<String, HvssProgress> userTaksProgress = new HashMap<>();

    public HvssProgress getUserProgress(String uuid){
        return userTaksProgress.get(uuid);
    }

}
