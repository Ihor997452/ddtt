package com.my.db.dao.user;

import com.my.db.dao.AbstractDao;
import com.my.db.entity.User;
import com.my.db.entity.Class;

import java.util.List;

public interface UserDao extends AbstractDao<User> {
    User getByEmail(String email);
    List<Class> getUserClasses(int id);
}
