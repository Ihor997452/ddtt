package com.my.db.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "classes")
public class Class implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "teacher_id")
    private int teacherId;
    private String name;
    private String description;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name="task_class")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Task> tasks = new ArrayList<>();

    @ManyToMany(mappedBy = "classes")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> students = new ArrayList<>();

    public Class(int id, int teacherId, String name, String description) {
        this.id = id;
        this.teacherId = teacherId;
        this.name = name;
        this.description = description;
    }

    public Class(int teacherId, String name, String description) {
        this.teacherId = teacherId;
        this.name = name;
        this.description = description;
    }

    public Class() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean addStudent(User student) {
        return students.add(student);
    }

    public boolean removeStudent(User student) {
        return students.remove(student);
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return id == aClass.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", desc='" + description + '\'' +
                ", tasks=" + tasks +
                ", students=" + students +
                '}';
    }
}
