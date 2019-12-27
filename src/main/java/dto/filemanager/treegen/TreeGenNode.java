package dto.filemanager.treegen;

import dto.filemanager.TreeGenEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeGenNode {

    String groupLevelName;
    List<TreeGenNode> elementList = new ArrayList<>();
    TreeGenEnum nodeType;
    TreeGenNodeData data;

}
