package stepOneConsoleApp.dao;

import stepOneConsoleApp.entities.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorList {

    private static AuthorList authorList = new AuthorList();

    private List<Author> authors = new ArrayList<>();

    private AuthorList(){}


    public void deleteAuthor(Author author) {
        authors.remove(author);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public List<Author> getAuthors(){
        return authors;
    }

    public static AuthorList getInstance(){
        return authorList;
    }


}
