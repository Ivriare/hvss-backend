package dao.content.impl;

import dao.content.HvssContentGroupDao;
import dao.factory.HvssDaoImpl;
import entities.content.HvssContentGroup;
import org.springframework.stereotype.Component;

@Component
public class HvssContentGroupDaoImpl extends HvssDaoImpl<Long, HvssContentGroup> implements HvssContentGroupDao {
}
