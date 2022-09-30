package com.my.db.dao.clazz;

import com.my.db.dao.AbstractDaoImpl;
import com.my.db.entity.Class;
import com.my.db.entity.Task;
import com.my.db.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ClassDaoImpl extends AbstractDaoImpl<Class> implements ClassDao {
    public ClassDaoImpl() {
        clazz = Class.class;
    }

    @Override
    public List<Class> getTeacherClasses(int teacherId) {
        String hql = "From Class cl WHERE cl.teacherId = " + teacherId;

        Session session = sessionFactory.openSession();
        List<Class> result = session.createQuery(hql).list();
        session.close();

        return result;
    }

    @Override
    public List<Task> getClassTasks(int classId) {
        return get(classId).getTasks();
    }

    @Override
    public List<User> getClassStudents(int classId) {
        return get(classId).getStudents();
    }
}
