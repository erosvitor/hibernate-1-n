package com.ctseducare.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ctseducare.hibernate.HibernateUtils;
import com.ctseducare.model.Author;

public class AuthorDAO extends DAO<Author> {

  public AuthorDAO() {
    super(Author.class);
  }
  
  public Author findByName(String name) {
    String hql = "FROM Author WHERE name = :name";
    
    Session session = HibernateUtils.getSessionFactory().openSession();
    
    TypedQuery<Author> typedQuery = session.createQuery(hql, Author.class);
    typedQuery.setParameter("name", name);
    
    Author Autor = typedQuery.getSingleResult();
    
    session.close();
    
    return Autor;
  }
  
  public void removeAll() throws Exception {
    Session session = HibernateUtils.getSessionFactory().openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();

      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaDelete<Author> delete = cb.createCriteriaDelete(Author.class);
      delete.from(Author.class);

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
  
  public List<Author> findAllJoinFetch() {
    String hql = "SELECT a FROM Author a JOIN FETCH a.books";
    
    Session session = HibernateUtils.getSessionFactory().openSession();
    
    TypedQuery<Author> typedQuery = session.createQuery(hql, Author.class);
    List<Author> authors = typedQuery.getResultList();
    
    session.close();
    
    return authors;
  }
    
}