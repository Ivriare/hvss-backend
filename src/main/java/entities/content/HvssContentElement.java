package entities.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.HvssContentObject;
import entities.HvssTypeEnum;
import entities.file.HvssFileLocation;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "hvss_content_element")
public class HvssContentElement extends HvssContentObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private int elementOrder;





    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "content_id", nullable = false)
    private HvssContent hvssContent;

    @OneToMany(mappedBy= "contentElement", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private List<HvssFileLocation> fileLocation;

    private String name;

    public HvssContentElement(){
        this.setHvssType(HvssTypeEnum.HvssContentElement);
        this.setCreationDate(new Timestamp(System.currentTimeMillis()));
    }
}
