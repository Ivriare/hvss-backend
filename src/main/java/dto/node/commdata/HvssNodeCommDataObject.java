package dto.node.commdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class HvssNodeCommDataObject {

    @JsonCreator
    public HvssNodeCommDataObject() {
    }
}
