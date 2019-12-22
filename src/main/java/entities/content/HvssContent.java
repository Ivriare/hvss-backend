package entities.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "hvss_content")
public class HvssContent extends HvssContentObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String uid;

    private String contentName;
    private String coverPictureLocalPath;
    private String archiveFolderName;
    private String contentDescription;

    @OneToMany(mappedBy = "hvssContent", fetch = FetchType.EAGER, orphanRemoval=true, cascade = CascadeType.ALL)
    private List<HvssContentElement> contentElement;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "content_group_id", nullable = false)
    private HvssContentGroup contentGroup;
}
