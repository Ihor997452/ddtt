package com.my.db.dao.task;

import com.my.db.dao.AbstractDao;
import com.my.db.entity.Task;

import java.util.List;

public interface TaskDao extends AbstractDao<Task> {
    List<Task> searchByName(String name);
}
