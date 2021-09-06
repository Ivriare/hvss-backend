package entities.user;

import entities.HvssObject;
import entities.HvssTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "HVSS_USER_TOKEN")
@NoArgsConstructor
public class HvssUserToken extends HvssObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String sessionToken;

    public HvssUserToken(String sessionToken){
        this.sessionToken = sessionToken;
        this.setOid(UUID.randomUUID().toString());
        this.setHvssType(HvssTypeEnum.HvssUserToken);
        this.setCreationDate(new Timestamp(System.currentTimeMillis()));
    }
}
