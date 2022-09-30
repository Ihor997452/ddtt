package com.my.controller;

import com.my.db.dao.clazz.ClassDao;
import com.my.db.dao.user.UserDao;
import com.my.db.entity.Class;
import com.my.db.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class StudentController {
    private final UserDao userDao;
    private final ClassDao classDao;

    public StudentController(UserDao userDao, ClassDao classDao) {
        this.userDao = userDao;
        this.classDao = classDao;
    }

    @PostMapping("/student/enroll")
    public String enrollPost(HttpServletRequest request, HttpSession session) {
        User student = (User) session.getAttribute("loggedUser");
        int id = Integer.parseInt(request.getParameter("class-to-enroll"));

        Class clazz = classDao.get(id);

        if (clazz == null) {
            session.setAttribute("message", "No Such Class");
            return "redirect:/";
        }

        clazz.addStudent(student);
        student.addClass(clazz);
        userDao.save(student);
        classDao.save(clazz);

        return "redirect:/";
    }

    @PostMapping("/student/dropClass")
    public String dropClassPost(HttpServletRequest request, HttpSession session) {
        int id = Integer.parseInt(request.getParameter("class-id"));

        User student = (User) session.getAttribute("loggedUser");

        Class clazz = classDao.get(id);
        clazz.removeStudent(student);
        student.removeClass(clazz);

        userDao.save(student);
        classDao.save(clazz);

        return "redirect:/";
    }
}
