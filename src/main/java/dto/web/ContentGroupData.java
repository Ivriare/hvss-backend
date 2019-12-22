package dto.web;

import entities.content.HvssContentTypeEnum;
import lombok.Data;

@Data
public class ContentGroupData {
    HvssContentTypeEnum contentType;
    String contentGroupName;

}
