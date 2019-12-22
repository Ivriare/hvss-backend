package dao.content;

import dao.HvssDao;
import entities.content.HvssContent;

import java.util.List;

public interface HvssContentDao extends HvssDao<Long , HvssContent> {

    List<HvssContent> getContentListByGroupId(Long id);

}
