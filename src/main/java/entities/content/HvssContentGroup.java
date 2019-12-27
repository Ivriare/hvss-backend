package entities.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.HvssContentObject;
import entities.HvssObject;
import entities.HvssTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "hvss_content_group")
public class HvssContentGroup extends HvssContentObject {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private HvssContentTypeEnum contentTypeId;

    @OneToMany(mappedBy= "contentGroup", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    List<HvssContent> hvssContentList ;

    public HvssContentGroup(){
        this.setHvssType(HvssTypeEnum.HvssContentGroup);
        this.setCreationDate(new Timestamp(System.currentTimeMillis()));
    }

}
