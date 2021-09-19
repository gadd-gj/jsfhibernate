package com.uv.jsfhibernate.model;

import com.uv.jsfhibernate.entity.Empleado;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class EmpleadoModel {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public boolean create(Empleado empleado) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(empleado);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Empleado> findAll() {
        List<Empleado> result = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Empleado");
            result = (List<Empleado>) query.list();
            transaction.commit();
        } catch (Exception e) {
            result = null;

            System.err.println("Error: " + e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public boolean validate(String username, String password) {
        boolean valid = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Empleado where username = :username AND password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            if (!query.list().isEmpty()) {
                valid = true;
            } else {
                valid = false;
            }
        } catch (Exception e) {
            valid = false;
            System.err.println("Error: " + e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return valid;
    }

}
