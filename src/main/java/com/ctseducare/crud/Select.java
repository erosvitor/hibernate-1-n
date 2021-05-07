package com.ctseducare.crud;

import com.ctseducare.dao.BookDAO;
import com.ctseducare.model.Book;

public class Select {

  public static void main(String[] args) {

    BookDAO bookDAO = new BookDAO();
    try {
      Book book = bookDAO.findByTitle("Livro ABC");
      System.out.println("Title: " + book.getTitle());
      System.out.println("Edition: " + book.getEdition());
      System.out.println("Price: " + book.getPrice());
      System.out.println("Author: " + book.getAuthor().getName());
      System.out.println("Publisher: " + book.getPublisher().getName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
