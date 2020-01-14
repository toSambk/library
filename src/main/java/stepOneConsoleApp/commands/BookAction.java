package stepOneConsoleApp.commands;

import stepOneConsoleApp.builders.AuthorBuilder;
import stepOneConsoleApp.builders.BookBuilder;
import stepOneConsoleApp.builders.PublisherBuilder;
import stepOneConsoleApp.dao.AuthorList;
import stepOneConsoleApp.dao.BookList;
import stepOneConsoleApp.dao.PublisherList;
import stepOneConsoleApp.entities.Author;
import stepOneConsoleApp.entities.Book;
import stepOneConsoleApp.entities.Publisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BookAction implements Action {

    private BookList bookList = BookList.getInstance();

    private AuthorList authorList = AuthorList.getInstance();

    private PublisherList publisherList = PublisherList.getInstance();

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public BookAction() {
    }

    @Override
    public void getAll() {

        System.out.println("---Список всех книг---");
        bookList.getBooks().forEach(x -> System.out.print("Название: " + x.getName() + "\tАвторы: " +
                x.getAuthors() + "\tИздатель:" + x.getPublisher().getName() + "\n"));

    }

    @Override
    public void addNew() throws IOException {

        System.out.println("---Введите название книги---");
        String nameOfBook = bufferedReader.readLine();

        if (bookList.getBooks().stream().anyMatch(x -> x.getName().equals(nameOfBook))) {
            System.out.println("Данная книга уже существует");
            return;
        }
        BookBuilder bookBuilder = new BookBuilder().setName(nameOfBook);

        System.out.println("---Введите имена авторов через пробел---");
        String authorsNotParsed = bufferedReader.readLine();

        List<Author> authors = new ArrayList<>();
        for (String author : authorsNotParsed.split(" ")) {
            if (!authorList.getAuthors().stream().anyMatch(x -> x.getName().equals(author))) {
                System.out.println("Автора " + author + " не существует. Добавляем...");
                AuthorBuilder authorBuilder = new AuthorBuilder().setName(author);
                Author result = authorBuilder.getResult();
                authors.add(result);
                authorList.addAuthor(result);
                System.out.println("Автор " + author + " добавлен");
            } else {
                authors.add(authorList.getAuthors().stream().filter(x -> x.getName().equals(author)).findAny().get());
            }
        }
        bookBuilder.setAuthors(authors);

        System.out.println("---Введите название издателя---");
        String publisher = bufferedReader.readLine();
        Publisher result;
        if (!publisherList.getPublishers().stream().anyMatch(x -> x.getName().equals(publisher))) {
            System.out.println("Издателя " + publisher + " не существует. Добавляем...");
            PublisherBuilder publisherBuilder = new PublisherBuilder().setName(publisher);
            result = publisherBuilder.getResult();
            publisherList.addPublisher(result);
            System.out.println("Издатель " + publisher + " добавлен");
        } else {
            result = publisherList.getPublishers().stream().filter(x -> x.getName().equals(publisher)).findAny().get();
        }

        bookBuilder.setPublisher(result);
        bookList.addBook(bookBuilder.getResult());

    }

    @Override
    public void delete() throws IOException {

        System.out.println("---Введите имя книги---");
        String input = bufferedReader.readLine();
        if (bookList.getBooks().stream().anyMatch(x -> x.getName().equals(input))) {
            System.out.println("Удаление...");
            Book book = bookList.getBooks().stream().filter(x -> x.getName().equals(input)).findAny().get();
            bookList.deleteBook(book);
            System.out.println("Книга удалена");
        } else {
            System.out.println("Книги с таким названием не существует");
        }

    }
}
