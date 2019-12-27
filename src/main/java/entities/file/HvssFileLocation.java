package entities.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.HvssObject;
import entities.HvssTypeEnum;
import entities.content.HvssContentElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "HVSS_FILE_LOCATION")
public class HvssFileLocation extends HvssObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private HvssFileLocationTypeEnum fileLocationType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "content_element_id", nullable = false)
    private HvssContentElement contentElement;

    private String filePath;
    private String subtitlePath;


    public HvssFileLocation(){
        this.setHvssType(HvssTypeEnum.HvssFileLocation);
        this.setCreationDate(new Timestamp(System.currentTimeMillis()));
    }
}
