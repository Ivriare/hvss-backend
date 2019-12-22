package entities;


import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public class HvssObject {
    Timestamp creationDate;
    Timestamp lastUpdateDate;
    String uid;
}
