package com.ctseducare.crud;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.ctseducare.dao.BookDAO;
import com.ctseducare.model.Author;
import com.ctseducare.model.Publisher;
import com.ctseducare.model.Book;

public class Insert {

  public static void main(String[] args) {
    
    //--------------------------------------------------------------------------------
    // Tip: The author and publisher will be automatically inserted when book is 
    // inserted. Take a look in the annotation @Cascade(CascadeType.SAVE_UPDATE) in
    // 'Book' table.
    //--------------------------------------------------------------------------------
    Author author = new Author();
    author.setName("Siclano Pereira");

    Publisher publisher = new Publisher();
    publisher.setName("Editora Alves");
    
    Book book = new Book();
    book.setTitle("Livro ABC");
    book.setEdition((short)1);
    book.setPrice(187.00);
    book.setAuthor(author);
    book.setPublisher(publisher);
    
    Set<Book> books = new HashSet<>(Arrays.asList(book));
    author.setBooks(books);
    publisher.setBooks(books);
    
    BookDAO bookDAO = new BookDAO();
    try {
      bookDAO.insert(book);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
