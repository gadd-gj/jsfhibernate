package com.uv.jsfhibernate.model;

import com.uv.jsfhibernate.entity.Departamento;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DepartamentoModel {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public boolean create(Departamento departamento) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(departamento);
            transaction.commit();
        } catch (Exception e) {
            result = false;
            System.err.println("Error: " + e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public List<Departamento> findAldd() {
        List<Departamento> result = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Departamento");
            result = (List<Departamento>) query.list();
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

}
