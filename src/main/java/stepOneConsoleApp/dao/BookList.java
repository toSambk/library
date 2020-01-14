package stepOneConsoleApp.dao;

import stepOneConsoleApp.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BookList {

    private static BookList bookList = new BookList();

    private List<Book> books = new ArrayList<>();

    private BookList(){}


    public void deleteBook(Book book) {
        books.remove(book);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks(){
        return books;
    }

    public static BookList getInstance(){
       return bookList;
    }





}
