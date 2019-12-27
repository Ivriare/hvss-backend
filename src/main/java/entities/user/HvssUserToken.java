package entities.user;

import entities.HvssObject;
import entities.HvssTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "HVSS_USER_TOKEN")
public class HvssUserToken extends HvssObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String sessionToken;

    public HvssUserToken(){
        this.setHvssType(HvssTypeEnum.HvssUserToken);
        this.setCreationDate(new Timestamp(System.currentTimeMillis()));
    }
}
