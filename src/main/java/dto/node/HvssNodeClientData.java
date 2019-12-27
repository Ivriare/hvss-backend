package dto.node;


import dto.node.commdata.HvssNodeCommDataObject;
import lombok.Data;

@Data
public class HvssNodeClientData extends HvssNodeCommDataObject {

    boolean torrentSlave;
    boolean encodeSlave;

}
