package com.ctseducare.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher implements java.io.Serializable {

  private static final long serialVersionUID = -2687082317975336124L;

  private Integer id;
  private String name;
  private Set<Book> books = new HashSet<>(0);

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "name", nullable = false, length = 60)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }  
    if (obj == null) {
      return false;
    }  
    if (getClass() != obj.getClass()) {
      return false;
    }  
    Publisher other = (Publisher) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }  
    } else if (!id.equals(other.id)) {
      return false;
    }  
    return true;
  }
  
}