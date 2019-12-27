package dto.node;

import com.fasterxml.jackson.annotation.JsonCreator;
import dto.node.commdata.HvssNodeCommDataObject;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HvssNodeClientRegisterData extends HvssNodeCommDataObject {

    @JsonCreator
    HvssNodeClientRegisterData(){
        super();
    }

    String ip;
    int port;
    Boolean sslEnabled;
}
