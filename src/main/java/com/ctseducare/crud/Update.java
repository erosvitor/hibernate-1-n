package com.ctseducare.crud;

import com.ctseducare.dao.BookDAO;
import com.ctseducare.model.Book;

public class Update {

  public static void main(String[] args) {

    BookDAO bookDAO = new BookDAO();
    try {
      Book book = bookDAO.findByTitle("Livro ABC");
      
      book.getAuthor().setName("Siclano Pereira - changed");
      
      bookDAO.update(book);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
