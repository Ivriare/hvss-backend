package entities.content;

import entities.HvssObject;
import jdk.jfr.ContentType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "HVSS_CONTENT")
public class HvssContent extends HvssObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private HvssContentTypeEnum contentTypeId;

    private String contentName;
    private String coverPicturePath;
    private String description;
    private String torrentFileName;

    @OneToMany(mappedBy="HVSS_CONTENT")
    @OrderBy("name ASC")
    private List<HvssContentElement> contentList;


}
