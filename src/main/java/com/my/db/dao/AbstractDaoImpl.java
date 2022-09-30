package com.my.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDaoImpl<T extends Serializable> implements AbstractDao<T> {
    protected Class<T> clazz;
    @Autowired
    protected SessionFactory sessionFactory;

    public T get(int id) {
        Session session = sessionFactory.openSession();
        T entity = session.get(clazz, id);
        session.close();
        return entity;
    }

    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        List<T> result = session.createQuery("from " + clazz.getName()).list();
        session.close();
        return result;
    }

    public void save(T entity) {
        if (entity == null) return;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(T entity) {
        if (entity == null) return;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(int id) {
        T entity = get(id);
        if (entity == null) return;
        delete(entity);
    }
}
