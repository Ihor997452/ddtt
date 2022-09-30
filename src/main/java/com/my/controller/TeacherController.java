package com.my.controller;

import com.my.db.dao.clazz.ClassDao;
import com.my.db.dao.task.TaskDao;
import com.my.db.dao.user.UserDao;
import com.my.db.entity.Class;
import com.my.db.entity.Task;
import com.my.db.entity.User;
import com.my.db.enums.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TeacherController {
    private final UserDao userDao;
    private final ClassDao classDao;
    private final TaskDao taskDao;

    public TeacherController(UserDao userDao, ClassDao classDao, TaskDao taskDao) {
        this.userDao = userDao;
        this.classDao = classDao;
        this.taskDao = taskDao;
    }

    @PostMapping("/teacher/addStudent")
    public String addStudentPos(HttpServletRequest request, HttpSession session) {
        String email = request.getParameter("student-email");
        User student = userDao.getByEmail(email);
        User teacher = (User) session.getAttribute("loggedUser");
        Class clazz = (Class) session.getAttribute("clazz");

        System.out.println(student);

        if (teacher.getId() != clazz.getTeacherId()) {
            session.setAttribute("message", "Something Went Wrong");
            return "redirect:/";
        }

        if (student == null || student.getRole() == Roles.TEACHER) {
            session.setAttribute("message", "No Students With That Email");
            return "redirect:/students?id=" + clazz.getId();
        }

        if (clazz.getStudents().contains(student)) {
            session.setAttribute("message", "Student Already In Class");
            return "redirect:/students?id=" + clazz.getId();
        }

        student.addClass(clazz);
        clazz.addStudent(student);
        userDao.save(student);
        classDao.save(clazz);

        return "redirect:/students?id=" + clazz.getId();
    }

    @PostMapping("/teacher/removeStudent")
    public String removeStudentPost(HttpServletRequest request, HttpSession session) {
        int id = Integer.parseInt(request.getParameter("student-id"));
        User student = userDao.get(id);
        User teacher = (User) session.getAttribute("loggedUser");
        Class clazz = (Class) session.getAttribute("clazz");

        if (teacher.getId() != clazz.getTeacherId()) {
            session.setAttribute("message", "Something Went Wrong");
            return "redirect:/";
        }

        student.removeClass(clazz);
        clazz.removeStudent(student);
        userDao.save(student);
        classDao.save(clazz);

        return "redirect:/students?id=" + clazz.getId();
    }

    @PostMapping("/teacher/newTask")
    public String newTaskPost(HttpServletRequest request, HttpSession session) {
        Task task = new Task();
        task.setName(request.getParameter("task-name"));
        task.setDescription(request.getParameter("task-desc"));

        Class clazz = (Class) session.getAttribute("clazz");
        User user = (User) session.getAttribute("loggedUser");

        if (user.getId() == clazz.getTeacherId()) {
            clazz.addTask(task);
            classDao.save(clazz);

            return "redirect:/tasks?id=" + clazz.getId();
        }

        session.setAttribute("message", "Something Went Wrong");
        return "redirect:/";
    }

    @PostMapping("/teacher/deleteClass")
    public String deleteClassPost(HttpServletRequest request, HttpSession session) {
        int id = Integer.parseInt(request.getParameter("class-id"));

        Class clazz = classDao.get(id);
        User user = (User) session.getAttribute("loggedUser");
        if (user.getId() == clazz.getTeacherId()) {
            classDao.delete(clazz.getId());
            return "redirect:/";
        }

        session.setAttribute("message", "Something Went Wrong");
        return "redirect:/";
    }

    @PostMapping("/teacher/deleteTask")
    public String deleteTaskPost(HttpServletRequest request, HttpSession session){
        int id = Integer.parseInt(request.getParameter("task-id"));

        Class clazz = (Class) session.getAttribute("clazz");
        User user = (User) session.getAttribute("loggedUser");
        if (user.getId() == clazz.getTeacherId()) {
            clazz.removeTask(taskDao.get(id));
            classDao.save(clazz);
            return "redirect:/tasks?id=" + clazz.getId();
        }

        session.setAttribute("message", "Something Went Wrong");
        return "redirect:/";
    }

    @PostMapping("/teacher/newClass")
    public String newClassPost(HttpServletRequest request, HttpSession session) {
        Class clazz = new Class();
        clazz.setTeacherId((((User) session.getAttribute("loggedUser")).getId()));
        clazz.setName(request.getParameter("class-name"));
        clazz.setDescription(request.getParameter("class-desc"));
        User user = (User) session.getAttribute("loggedUser");

        if (user.getId() == clazz.getTeacherId()) {
            classDao.save(clazz);
            return "redirect:/";
        }

        session.setAttribute("message", "Something Went Wrong");
        return "redirect:/";
    }
}
