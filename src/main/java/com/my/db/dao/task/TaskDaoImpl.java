package com.my.db.dao.task;

import com.my.db.dao.AbstractDaoImpl;
import com.my.db.entity.Task;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.List;

@Repository
public class TaskDaoImpl extends AbstractDaoImpl<Task> implements TaskDao {
    public TaskDaoImpl() {
        clazz = Task.class;
    }

    @Override
    public List<Task> searchByName(String name) {
        String hql = "From Task WHERE name LIKE CONCAT('%', '" + name + "','%')";

        Session session = sessionFactory.openSession();
        List<Task> result = session.createQuery(hql).list();
        session.close();

        return result;
    }
}
