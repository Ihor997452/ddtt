package com.my.db.dao.user;

import com.my.db.dao.AbstractDaoImpl;
import com.my.db.entity.User;
import com.my.db.entity.Class;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    @Override
    public List<Class> searchStudentClassesByName(String name) {
        String hql = "From Class WHERE name LIKE CONCAT('%', '" + name + "','%')";

        Session session = sessionFactory.openSession();
        List<Class> result = session.createQuery(hql).list();
        session.close();

        return result;
    }

    @Override
    public List<Class> searchStudentClassesByName(int id, String name) {
        List<Class> result = new ArrayList<>();

        for (Class c : searchStudentClassesByName(name)) {
            for (User u : c.getStudents()) {
                if (u.getId() == id) {
                    result.add(c);
                }
            }
        }

        return result;
    }

    @Override
    public List<User> searchByEmail(String email) {
        String hql = "From User WHERE email LIKE CONCAT('%', '" + email + "','%')";

        Session session = sessionFactory.openSession();
        List<User> result = session.createQuery(hql).list();
        session.close();

        return result;
    }

    @Override
    public List<User> searchByEmailInClass(String email, int id) {
        List<User> result = new ArrayList<>();

        for (User u :
                searchByEmail(email)) {
            for (Class c :
                    u.getClasses()) {
                if (c.getId() == id) {
                    result.add(u);
                }
            }
        }

        return result;
    }
}
