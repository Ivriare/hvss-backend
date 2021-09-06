package dao.content.impl;

import dao.content.HvssContentElementDao;
import dao.factory.HvssDaoImpl;
import entities.content.HvssContentElement;
import entities.user.HvssUserPasswordReset;
import org.springframework.stereotype.Component;

@Component
public class HvssUserPasswordResetDaoImpl extends HvssDaoImpl<Long, HvssUserPasswordReset> implements HvssUserPasswordResetDao {
}
