package dto.progress;

import lombok.Data;

@Data
public class HvssProgress {

    HvssProgressStatus progressStatus;
    int currentStep;
    int maxStep;

    public void nextStep(){
        this.currentStep++;
    }

}
