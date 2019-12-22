package services;

import dao.content.HvssContentDao;
import dao.content.HvssContentGroupDao;
import dto.web.ContentData;
import dto.web.ContentGroupData;
import entities.content.HvssContent;
import entities.content.HvssContentGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.HashIdentifierUtils;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ContentService {

    @Autowired
    HvssContentGroupDao hvssContentGroupDao;

    @Autowired
    HvssContentDao hvssContentDao;

    @Autowired
    HashIdentifierUtils hashIdentifierUtils;

    public List<HvssContentGroup> getContentGroupList(){
        return hvssContentGroupDao.findAll();
    }

    public HvssContentGroup getContentGroup(Long ID){
        return hvssContentGroupDao.findById(ID);
    }

    public void addNewContentGroup(ContentGroupData contentGroupData){
        HvssContentGroup hvssContentGroup = new HvssContentGroup();
        hvssContentGroup.setName(contentGroupData.getContentGroupName());
        hvssContentGroup.setContentTypeId(contentGroupData.getContentType());
        hvssContentGroup.setCreationDate(new Timestamp((System.currentTimeMillis())));
        hvssContentGroup.setLastUpdateDate(new Timestamp((System.currentTimeMillis())));
        hvssContentGroup.setUid(hashIdentifierUtils.createNewHashId());
        hvssContentGroupDao.persist(hvssContentGroup);
    }

    public void deleteContentGroup(Long id){
        hvssContentGroupDao.remove(hvssContentGroupDao.findById(id));
    }

    public HvssContent getContentById(Long ID){
        return hvssContentDao.findById(ID);
    }

    public List<HvssContent> getContentListByGroupId(Long ID){
        return hvssContentDao.getContentListByGroupId(ID);
    }

    public void addContentToContentGroup(Long id, ContentData contentData){
        HvssContent hvssContent = new HvssContent();
        hvssContent.setContentName(contentData.getName());
        hvssContent.setContentDescription(contentData.getDesc());
        hvssContent.setCreationDate(new Timestamp((System.currentTimeMillis())));
        hvssContent.setLastUpdateDate(new Timestamp((System.currentTimeMillis())));
        hvssContent.setContentGroup(hvssContentGroupDao.findById(id));
        hvssContent.setUid(hashIdentifierUtils.createNewHashId());
        hvssContentDao.merge(hvssContent);
    }

   //private
}
