package com.ctseducare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "books")
public class Book implements java.io.Serializable {

  private static final long serialVersionUID = 2128318314504265685L;

  private Integer id;
  private String title;
  private Short edition;
  private Double price;
  private Author author;
  private Publisher publisher;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "title", nullable = false, length = 100)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(name = "edition", nullable = false)
  public Short getEdition() {
    return edition;
  }

  public void setEdition(Short edition) {
    this.edition = edition;
  }

  @Column(name = "price", nullable = false, precision = 8)
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_author")
  @Cascade(CascadeType.SAVE_UPDATE)
  public Author getAuthor() {
    return this.author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_publisher")
  @Cascade(CascadeType.SAVE_UPDATE)
  public Publisher getPublisher() {
    return this.publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
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
    Book other = (Book) obj;
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