package com.ctseducare.queries;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.ctseducare.dao.AuthorDAO;
import com.ctseducare.dao.BookDAO;
import com.ctseducare.hibernate.HibernateUtils;
import com.ctseducare.model.Author;
import com.ctseducare.model.Book;

public class Queries {

  public static void main(String[] args) {
    
    {
      // Here will not be throw the exception 'org.hibernate.LazyInitializationException' because
      // the access to 'author' is inside session
      Session session = HibernateUtils.getSessionFactory().openSession();
   
      String hql = "FROM Book";
      TypedQuery<Book> typedQuery = session.createQuery(hql, Book.class);
    
      List<Book> books = typedQuery.getResultList();
      for (Book book : books) {
        System.out.println(book.getTitle() + "," + book.getAuthor().getName());
      }
    
      session.close();
    }    
    
    {
      // Here will be throw the exception 'org.hibernate.LazyInitializationException' because
      // the access to 'author' is out session
      try {
        BookDAO bookDAO = new BookDAO();
        List<Book> books = bookDAO.findAll();
        for (Book book : books) {
          System.out.println(book.getTitle() + "," + book.getAuthor().getName());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    {
      // Here will not be throw the exception 'org.hibernate.LazyInitializationException' because
      // we are using JOIN FETCH
      try {
        BookDAO bookDAO = new BookDAO();
        List<Book> books = bookDAO.findAllJoinFetch();
        for (Book book : books) {
          System.out.println(book.getTitle() + "," + book.getAuthor().getName());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    {
      // Standard use
      try {
        AuthorDAO authorDAO = new AuthorDAO();
        List<Author> authors = authorDAO.findAll();
        for (Author author : authors) {
          System.out.println(author.getName());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    {
      // The use of JOIN FETCH cause duplication of some data due one-to-many relationship
      try {
        AuthorDAO authorDAO = new AuthorDAO();
        List<Author> authors = authorDAO.findAllJoinFetch();
        for (Author author : authors) {
          System.out.println(author.getName());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

}
