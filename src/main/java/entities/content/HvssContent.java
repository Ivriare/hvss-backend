package entities.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.HvssContentObject;
import entities.HvssTypeEnum;
import entities.file.HvssFileLocationTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@Entity
@Table(name = "hvss_content")
public class HvssContent extends HvssContentObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String contentName;
    private String coverPictureLocalPath;
    private String contentDescription;

    @OneToMany(mappedBy = "hvssContent", fetch = FetchType.EAGER, orphanRemoval=true, cascade = CascadeType.ALL)
    private List<HvssContentElement> contentElement;

    @Enumerated(EnumType.STRING)
    HvssFileLocationTypeEnum primaryLocation;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "content_group_id", nullable = false)
    private HvssContentGroup contentGroup;

    public HvssContent(){
        this.setHvssType(HvssTypeEnum.HvssContent);
        this.setCreationDate(new Timestamp(System.currentTimeMillis()));
    }

}
