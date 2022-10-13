package com.example.myapp.ds;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Book> bookList=new ArrayList<>();

    public void addBook(Book book){
        book.setCategory(this);
        bookList.add(book);
    }
}
