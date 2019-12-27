package dto.node;

import dto.node.commdata.HvssNodeCommDataObject;
import dto.node.commdata.HvssSocketCommandDataTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HvssNodeCommData<T extends HvssNodeCommDataObject> {

    public HvssNodeCommData(){}

    HvssSocketCommandDataTypeEnum commandData;
    String clientOid;
    List<T> data;


}
