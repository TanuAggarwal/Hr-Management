package com.jocata.hrmanagement.dao;

import com.jocata.hrmanagement.entity.EmployeeDetails;
import com.jocata.hrmanagement.entity.EmployeeManagerProjectMapping;
import com.jocata.hrmanagement.entity.Values;
import com.jocata.hrmanagement.response.GetEmployeeDetailsListByNameResponse;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;


@Component
public class HibernateUtils {

    @Autowired
    public SessionFactory sessionFactory;


    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void closeSession(Session session) {
        if (session.isOpen()) session.close();
    }

    public <T> T save(T entity) {
        Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();
            Serializable id = session.save(entity);
            tx.commit();
            return (T) id;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }

    public <T> void update(T entity) {
        Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();
            session.update(entity);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        //return null;
    }

    public <T> void delete(T entity) {
        Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();

            session.delete(entity);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        //return null;
    }

    public <T> T findEntityById(T entity, Serializable id) {
        Session session = getSession();
        try {
            return (T) session.get(entity.getClass(), id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }

    public <T> List<T> loadEntityByClassName(T entity) {
        Session session = getSession();
        try {
            Query<T> qry = session.createQuery("from " + entity.getClass().getName());
            return qry.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }

    public <T> List<T> loadEntityByHql(String hql) {
        Session session = getSession();
        try {
            Query<T> qry = session.createQuery(hql);
            return qry.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }

    public Values getValuesByName(String name) {

        Values values;
        try (Session session = sessionFactory.openSession()) {

            String hqlQuery = "SELECT em FROM Values as em WHERE em.name =: name";
            Query query = session.createQuery(hqlQuery);
            query.setParameter("name", name);
            values = (Values) query.getSingleResult();

            return values;
        } catch (Exception e) {

            throw e;
        }
    }


    public List<GetEmployeeDetailsListByNameResponse> getEmployeesListByName(String name) {
        List<GetEmployeeDetailsListByNameResponse> getEmployeeDetailsListByNameResponse;
        try (Session session = sessionFactory.openSession()) {

            String hqlQuery = "SELECT em FROM EmployeeDetails as em WHERE empName =: name";

            Query query = session.createQuery(hqlQuery);
            query.setParameter("name", name);

            getEmployeeDetailsListByNameResponse = (List<GetEmployeeDetailsListByNameResponse>) query.getResultList();
            return getEmployeeDetailsListByNameResponse;

        } catch (Exception e) {
            throw e;
        }

    }

    public EmployeeManagerProjectMapping getMappingByEmpId(EmployeeDetails employeeDetails) {

        EmployeeManagerProjectMapping mapping;
        try (Session session = sessionFactory.openSession()) {

            String hqlQuery = "SELECT em FROM EmployeeManagerProjectMapping as em WHERE em.empDetails =: id";
            Query query = session.createQuery(hqlQuery);
            query.setParameter("id", employeeDetails);
            mapping = (EmployeeManagerProjectMapping) query.getSingleResult();

            return mapping;
        } catch (Exception e) {

            throw e;
        }
    }

    public <T> List<T> orderbyname(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List orderbyname = null;
        try {
            tx = session.beginTransaction();

            //getting list of pramametric class in cr object of type criteria;
            Criteria cr = session.createCriteria(EmployeeDetails.class);
//
//            System.out.println(cr.toString());
            cr.addOrder(Order.asc("empName"));
            orderbyname = cr.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orderbyname;
    }


    public <T> List<T> orderByNameAndDesignationMustBeIntern(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List intern = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(EmployeeDetails.class);


            Values value = new Values();
            value.setId(1);
            cr.add(Restrictions.eq("designation" , value));
            cr.addOrder(Order.asc("empName"));

            intern = cr.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return intern;
    }

    String s = "tvrId, version, misCategory,tvrName ,aliasName,dbschemaName ,dbobjectName, isActive, createDt, updateDt, expiryDt, tvrType, parentTvrId, misSql, misReportDef, queryConfigId, dataRestrictionType, isSlave";
}
