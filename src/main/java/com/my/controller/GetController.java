package com.my.controller;

import com.my.db.dao.clazz.ClassDao;
import com.my.db.dao.user.UserDao;
import com.my.db.entity.Class;
import com.my.db.entity.Task;
import com.my.db.entity.User;
import com.my.db.enums.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GetController {
    private final ClassDao classDao;
    private final UserDao userDao;

    public GetController(ClassDao classDao, UserDao userDao) {
        this.classDao = classDao;
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String homeGet(HttpSession session, HttpServletRequest request) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser != null && loggedUser.getRole() == Roles.STUDENT) {
            List<Class> classes = userDao.getUserClasses(loggedUser.getId());

            int size = getPaginationSize(classes);
            int page = getPaginationPage(request, size);

            classes = classes.stream().skip(page * 5L).limit(5).collect(Collectors.toList());

            session.setAttribute("classes", classes);
            request.setAttribute("size", size);
            request.setAttribute("page", page);

            return "home";
        }

        if (loggedUser != null && loggedUser.getRole() == Roles.TEACHER) {
            List<Class> classes = classDao.getTeacherClasses(loggedUser.getId());

            int size = getPaginationSize(classes);
            int page = getPaginationPage(request, size);

            classes = classes.stream().skip(page * 5L).limit(5).collect(Collectors.toList());

            session.setAttribute("classes", classes);
            request.setAttribute("size", size);
            request.setAttribute("page", page);
            return "home";
        }

        return "index";
    }

    @GetMapping("/guest/verify")
    public String verifyGet(HttpSession session) {
        session.setAttribute("message", "Check your email!");
        return "verify";
    }

    @GetMapping("/tasks")
    public String tasksGet(HttpSession session, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Task> tasks = classDao.getClassTasks(id);

        int size = getPaginationSize(tasks);
        int page = getPaginationPage(request, size);

        tasks = tasks.stream().skip(page * 5L).limit(5).collect(Collectors.toList());

        session.setAttribute("clazz", classDao.get(id));
        session.setAttribute("tasks", tasks);
        request.setAttribute("size", size);
        request.setAttribute("page", page);

        return "tasks";
    }

    @GetMapping("/students")
    public String studentsGet(HttpSession session, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<User> students = classDao.getClassStudents(id);

        int size = getPaginationSize(students);
        int page = getPaginationPage(request, size);

        students = students.stream().skip(page * 5L).limit(5).collect(Collectors.toList());

        session.setAttribute("clazz", classDao.get(id));
        session.setAttribute("students", students);
        request.setAttribute("size", size);
        request.setAttribute("page", page);

        return "students";
    }

    protected int getPaginationPage(HttpServletRequest req, int size) {
        if (req.getParameter("page") == null) {
            return 0;
        } else {
            int page = Integer.parseInt(req.getParameter("page"));
            if (page < 0) {
                page = 0;
            }

            if (page > size) {
                page = size;
            }

            return page;
        }
    }

    protected int getPaginationSize(List entities) {
        int size = entities.size();
        if (size % 5 == 0) {
            --size;
        }

        size /= 5;
        return size;
    }

    @GetMapping("/guest/newPassword")
    private String newPasswordGet() {
        return "newPassword";
    }
}
