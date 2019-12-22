package entities.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.HvssObject;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "hvss_content_group")
public class HvssContentGroup extends HvssObject {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String uid;

    private String name;

    @Enumerated(EnumType.STRING)
    private HvssContentTypeEnum contentTypeId;

    @OneToMany(mappedBy= "contentGroup", fetch = FetchType.EAGER, orphanRemoval=true, cascade = CascadeType.ALL)
    List<HvssContent> hvssContentList ;


}
