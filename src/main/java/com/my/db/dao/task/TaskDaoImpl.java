package com.my.db.dao.task;

import com.my.db.dao.AbstractDaoImpl;
import com.my.db.entity.Task;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImpl extends AbstractDaoImpl<Task> implements TaskDao {
    public TaskDaoImpl() {
        clazz = Task.class;
    }
}
