package com.ctseducare.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ctseducare.hibernate.HibernateUtils;
import com.ctseducare.model.Book;

public class BookDAO extends DAO<Book> {
  
  public BookDAO() {
    super(Book.class);
  }
  
  public Book findByTitle(String title) {
    String hql = "FROM Book WHERE title = :title";
    
    Session session = HibernateUtils.getSessionFactory().openSession();
    
    TypedQuery<Book> typedQuery = session.createQuery(hql, Book.class);
    typedQuery.setParameter("title", title);
    
    Book book = typedQuery.getSingleResult();
    
    session.close();
    
    return book;
  }
  
  public void removeAll() throws Exception {
    Session session = HibernateUtils.getSessionFactory().openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();

      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaDelete<Book> delete = cb.createCriteriaDelete(Book.class);
      delete.from(Book.class);

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
  
  public List<Book> findAllJoinFetch() {
    String hql = "SELECT b FROM Book b JOIN FETCH b.author JOIN FETCH b.publisher";
    
    Session session = HibernateUtils.getSessionFactory().openSession();
    
    TypedQuery<Book> typedQuery = session.createQuery(hql, Book.class);
    
    List<Book> books = typedQuery.getResultList();
    
    session.close();
    
    return books;
  }

}
