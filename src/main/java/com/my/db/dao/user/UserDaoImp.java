package com.my.db.dao.user;

import com.my.db.dao.AbstractDaoImpl;
import com.my.db.entity.User;
import com.my.db.entity.Class;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp extends AbstractDaoImpl<User> implements UserDao {
    public UserDaoImp() {
        clazz = User.class;
    }

    @Override
    public User getByEmail(String email) {
        Session session = sessionFactory.openSession();
        User user = session.byNaturalId(User.class).using("email", email).load();
        session.close();

        return user;
    }

    @Override
    public List<Class> getUserClasses(int id) {
        return get(id).getClasses();
    }
}
