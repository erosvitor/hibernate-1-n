package com.ctseducare.dao;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ctseducare.hibernate.HibernateUtils;
import com.ctseducare.model.Publisher;

public class PublisherDAO extends DAO<Publisher> {

  public PublisherDAO() {
    super(Publisher.class);
  }
  
  public Publisher findByName(String name) {
    String hql = "FROM Publisher WHERE name = :name";
    
    Session session = HibernateUtils.getSessionFactory().openSession();
    
    TypedQuery<Publisher> typedQuery = session.createQuery(hql, Publisher.class);
    typedQuery.setParameter("name", name);
    
    Publisher Editora = typedQuery.getSingleResult();
    
    session.close();
    
    return Editora;
  }
  
  public void removeAll() throws Exception {
    Session session = HibernateUtils.getSessionFactory().openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();

      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaDelete<Publisher> delete = cb.createCriteriaDelete(Publisher.class);
      delete.from(Publisher.class);

      Query query = session.createQuery(delete);
      query.executeUpdate();

      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw new Exception(e);
    } finally {
      session.close();
    }
  }

}
