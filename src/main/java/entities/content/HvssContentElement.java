package entities.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.HvssObject;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "hvss_content_element")
public class HvssContentElement extends HvssContentObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String uid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "content_id", nullable = false)
    private HvssContent hvssContent;

    private String name;
    private String archiveLocationPath;
    private String bufferLocationPath;
}
