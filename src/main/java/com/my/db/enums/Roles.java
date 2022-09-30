package com.my.db.enums;

public enum Roles {
    STUDENT,
    TEACHER,
    ADMIN;

    public static Roles evaluate(String id) {
        return evaluate(Integer.parseInt(id));
    }

    public static Roles evaluate(int id) {
        switch (id) {
            case 0:
                return Roles.STUDENT;
            case 1:
                return Roles.TEACHER;
            default:
                return null;
        }
    }
}
