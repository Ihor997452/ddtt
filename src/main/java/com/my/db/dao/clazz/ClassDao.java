package com.my.db.dao.clazz;

import com.my.db.dao.AbstractDao;
import com.my.db.entity.Class;
import com.my.db.entity.Task;
import com.my.db.entity.User;

import java.util.List;

public interface ClassDao extends AbstractDao<Class> {
    List<Class> getTeacherClasses(int teacherId);
    List<Task> getClassTasks(int classId);
    List<User> getClassStudents(int classId);
    List<Class> searchTeacherClasses(String name);
    List<Class> searchTeacherClasses(int teacherId, String name);
}
