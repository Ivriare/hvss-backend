package dao.content.impl;

import dao.content.HvssContentElementDao;
import dao.content.HvssContentGroupDao;
import dao.factory.HvssDaoImpl;
import entities.content.HvssContentElement;
import entities.content.HvssContentGroup;
import org.springframework.stereotype.Component;

@Component
public class HvssContentElementDaoImpl extends HvssDaoImpl<Long, HvssContentElement> implements HvssContentElementDao {
}
