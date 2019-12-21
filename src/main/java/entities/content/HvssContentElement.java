package entities.content;

import entities.HvssObject;

import javax.persistence.*;

@Entity
@Table(name = "hvss_content_element")
public class HvssContentElement extends HvssObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "CONTENT_ID"
    )
    private HvssContent hvssContent;

    private String name;
    private String archiveLocationPath;
    private String bufferLocationPath;
}
