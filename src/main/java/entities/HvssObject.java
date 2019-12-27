package entities;


import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public class HvssObject {
    Timestamp creationDate;
    Timestamp lastUpdateDate;
    String uid;

    @Enumerated(EnumType.STRING)
    HvssTypeEnum hvssType;

   // HvssTypeEnumspring jpa

}
