package com.my.controller;

import com.my.db.dao.user.UserDao;
import com.my.db.entity.User;
import com.my.db.enums.Roles;
import com.my.util.EmailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;

@Controller
public class AuthController {
    private final UserDao userDao;

    private final EmailSender sender;

    public AuthController(UserDao userDao, EmailSender sender) {
        this.userDao = userDao;
        this.sender = sender;
    }

    @PostMapping("/logout")
    public String logoutPost(HttpSession session) {
        session.removeAttribute("classes");
        session.removeAttribute("loggedUser");
        return "redirect:/";
    }

    @PostMapping("/guest/login")
    public String loginPost(HttpServletRequest request) {
        HttpSession session = request.getSession();

        User user = userDao.getByEmail(request.getParameter("email-login"));
        if (user != null &&
                user.getPassword().equals(request.getParameter("password-login"))) {
            session.setAttribute("loggedUser", user);
            return "redirect:/";
        }

        session.setAttribute("message", "Invalid email of password");

        return "redirect:/";
    }

    @PostMapping("/guest/verify")
    public String verifyPost(HttpServletRequest request) {
        HttpSession session = request.getSession();

        int expectedCode = (int) session.getAttribute("verificationCode");
        int actualCode = Integer.parseInt(request.getParameter("code"));

        if (expectedCode == actualCode) {
            session.removeAttribute("verificationCode");

            if (session.getAttribute("restore") != null) {
                return "redirect:/guest/newPassword";
            }

            User user = (User) session.getAttribute("user");
            userDao.save(user);

            session.removeAttribute("user");
            session.setAttribute("message", "Successful");

            return "redirect:/";
        }

        session.setAttribute("message", "Incorrect Code");
        return "redirect:/guest/verify";
    }


    @PostMapping("/guest/newPassword")
    private String newPasswordPost(HttpServletRequest request) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        user.setPassword(request.getParameter("new-password"));
        userDao.save(user);

        session.removeAttribute("user");
        session.setAttribute("message", "Successful");

        return "redirect:/" ;
    }

    @PostMapping("/guest/restore")
    public String restorePost(HttpServletRequest request) {
        int code = verificationCode();
        HttpSession session = request.getSession();
        session.setAttribute("restore", true);

        User user = userDao.getByEmail(request.getParameter("email-restore"));
        if (user == null) {
            session.setAttribute("message", "User with this email does not exist");
            return "redirect:/";
        }

        session.setAttribute("user", user);
        session.setAttribute("verificationCode", code);

        sender.sendSimpleEmail(user.getEmail(), "Restore Verification Code",
                "Verification code: " + code);

        return "redirect:/guest/verify";
    }

    @PostMapping("/guest/register")
    public String registerPost(HttpServletRequest request) {
        int code = verificationCode();
        User user = createUser(request);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("verificationCode", code);

        sender.sendSimpleEmail(user.getEmail(), "Register Verification Code",
                "Verification Code: " + code);

        return "redirect:/guest/verify";
    }

    private User createUser(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter("email-register"));
        user.setName(request.getParameter("name-register"));
        user.setSurname(request.getParameter("surname-register"));
        user.setPassword(request.getParameter("password-register"));
        user.setRole(Roles.evaluate(request.getParameter("role-register")));
        return user;
    }

    private int verificationCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < 6; ++i) {
            if (i == 0) {
                stringBuilder.append(random.nextInt(9) + 1);
                continue;
            }

            stringBuilder.append(random.nextInt(10));
        }

        return Integer.parseInt(stringBuilder.toString());
    }
}
