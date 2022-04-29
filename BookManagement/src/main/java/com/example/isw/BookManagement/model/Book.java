package com.example.isw.BookManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "*Please enter book title")
    @NotBlank(message = "*Please enter book title")
    @Column(name = "title")
    private String title;

    @NotNull(message = "*Please enter book tag")
    @NotBlank(message = "*Please enter book tag")
    @Column(name = "tag")
    private String tag;

    @NotNull(message = "*Please enter book authors")
    @NotBlank(message = "*Please enter book authors")
    @Column(name = "authors")
    private String authors;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_date")
    @DateTimeFormat(pattern = "dd-MMM-yyyy")
    private Date createDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "*Please select category")
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
