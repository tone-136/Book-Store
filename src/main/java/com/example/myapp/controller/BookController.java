package com.example.myapp.controller;

import com.example.myapp.ds.Author;
import com.example.myapp.ds.Book;
import com.example.myapp.ds.Category;
import com.example.myapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index( Model model){
        model.addAttribute("category",new Category());
        model.addAttribute("author",new Author());
        model.addAttribute("book",new Book());
        model.addAttribute("authors",bookService.findAllAuthor());
        model.addAttribute("categories",bookService.findAllCategory());
        model.addAttribute("books",bookService.findAllBooks());

        return "dash-board";
    }

    @GetMapping("/category-form")
    public String categoryForm(Model model){
        model.addAttribute("category",new Category());
        return "category-Form";
    }
    @PostMapping("/save-category")
    public String saveCategory(Category category, BindingResult result){
        if(result.hasErrors()){
            return "dash-board";
        }
        bookService.saveCategory(category);
        return "redirect:/";
    }

    @PostMapping("/save-author")
    public String saveAuthor(Author author,BindingResult result){
        if (result.hasErrors()){
            return "dash-board";
        }
        bookService.saveAuthor(author);
        return "redirect:/";

    }

    @PostMapping("/save-book")
    public String saveBook(Book book, BindingResult result){
        if(result.hasErrors()){
            return "dash-board";
        }
        bookService.saveBook(book,book.getCategoryId(),book.getAuthorId());
        return "redirect:/";
    }

    @GetMapping("/list-books")
    public String listAllBooks(Model model){
        model.addAttribute("books",bookService.findAllBooks());
        return "post-list";
    }

    @GetMapping("/list-category")
    public String listCategory(Model model){
        model.addAttribute("categories",bookService.findAllCategory());
        return "list-category";
    }

    @GetMapping("/list-authors")
    public String listAuthors(Model model){
        model.addAttribute("authors",bookService.findAllAuthor());
        return "list-author";
    }
}
