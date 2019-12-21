package entities;

import org.springframework.context.annotation.Primary;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

public class HvssObject {


    protected Date creationDate;
    protected  Date lastUpdateDate;

}
