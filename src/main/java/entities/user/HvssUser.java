package entities.user;


import dto.user.UserRegisterData;
import entities.HvssObject;
import entities.HvssTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "HVSS_USER")
public class HvssUser extends HvssObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String loginRememberToken;

    String username;
    String emailAddress;
    String password;
    String profilePicturePath;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "USER_TOKEN_ID", referencedColumnName = "id")
    HvssUserToken userTokenId;

    public HvssUser(UserRegisterData userRegisterData){
        this.username = userRegisterData.getUsername();
        this.password = userRegisterData.getPassword();
        this.emailAddress = userRegisterData.getEmailAddress();
        this.setCreationDate(new Timestamp(System.currentTimeMillis()));
        this.setHvssType(HvssTypeEnum.HvssUser);
    }

    public HvssUser(){
        this.setHvssType(HvssTypeEnum.HvssUser);
        this.setCreationDate(new Timestamp(System.currentTimeMillis()));
    }
}
