package com.ctseducare.crud;

import com.ctseducare.dao.AuthorDAO;
import com.ctseducare.model.Author;

public class Delete {

  public static void main(String[] args) {
    
    //--------------------------------------------------------------------------------
    // Tip: The books will be removed automatically when an author is removed. Take a
    // look in annotation '@Cascade(CascadeType.DELETE)' in 'Author' table.
    //--------------------------------------------------------------------------------
    AuthorDAO authorDAO = new AuthorDAO();
    try {
      Author author = authorDAO.findByName("Siclano Pereira - changed");
      authorDAO.remove(author);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
