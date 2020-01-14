import org.w3c.dom.ls.LSOutput;
import stepOneConsoleApp.builders.AuthorBuilder;
import stepOneConsoleApp.builders.BookBuilder;
import stepOneConsoleApp.builders.PublisherBuilder;
import stepOneConsoleApp.entities.Author;
import stepOneConsoleApp.entities.Book;
import stepOneConsoleApp.entities.Publisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static List<Book> booksCollection = new ArrayList<>();

    static List<Author> authorsCollection = new ArrayList<>();

    static List<Publisher> publishersCollection = new ArrayList<>();

    private BookBuilder bookBuilder = new BookBuilder();

    private AuthorBuilder authorBuilder = new AuthorBuilder();

    private PublisherBuilder publisherBuilder = new PublisherBuilder();


    public static void main(String[] args) throws IOException {
        Main main = new Main();

        while (true) {
            main.menu();
            String input = bufferedReader.readLine();

            switch (input) {
                case "1":
                    main.getAllBooks();
                    break;

                case "2":
                    main.getAllAuthors();
                    break;

                case "3":
                    main.getAllPublishers();
                    break;

                case "4":
                    main.addNewBook();
                    break;

                case "5":
                    main.addNewAuthor();
                    break;

                case "6":
                    main.addNewPublisher();
                    break;

                case "7":
                    main.deleteBook();
                    break;

                case "8":
                    main.deleteAuthor();
                    break;

                case "9":
                    main.deletePublisher();
                    break;



                default:
                    System.out.println("Неопределённая операция!");
                    break;

            }
        }


    }

    private void menu() {
        System.out.println("--------------------------");
        System.out.println("1. Получить список всех книг");
        System.out.println("2. Получить список всех авторов");
        System.out.println("3. Получить список всех издателей");
        System.out.println("4. Добавить новую книгу");
        System.out.println("5. Добавить нового автора");
        System.out.println("6. Добавить нового издателя");
        System.out.println("7. Удалить книгу по имени");
        System.out.println("8. Удалить автора по имени");
        System.out.println("9. Удалить издателя по имени");
        System.out.println("--------------------------");
        System.out.println("Введите цифру от 1 до 9");
    }

    private void getAllBooks() {
        System.out.println("---Список всех книг---");
        booksCollection.forEach(x -> System.out.print("Название: " + x.getName() + "\tАвторы: " +
                x.getAuthors() + "\tИздатель:" + x.getPublisher().getName() + "\n"));
    }

    private void getAllPublishers() {
        System.out.println("---Список всех издательств---");
        publishersCollection.forEach(x -> System.out.print("Название: " + x.getName() + "\n"));
    }

    private void getAllAuthors() {
        System.out.println("---Список всех авторов---");
        authorsCollection.forEach(x -> System.out.print("Имя: " + x.getName() + "\n"));
    }

    private void addNewBook() throws IOException {
        System.out.println("---Введите название книги---");
        String nameOfBook = bufferedReader.readLine();

        if (booksCollection.stream().anyMatch(x -> x.getName().equals(nameOfBook))) {
            System.out.println("Данная книга уже существует");
            return;
        }
        bookBuilder = new BookBuilder().setName(nameOfBook);

        System.out.println("---Введите имена авторов через пробел---");
        String authorsNotParsed = bufferedReader.readLine();

        List<Author> authors = new ArrayList<>();
        for (String author : authorsNotParsed.split(" ")) {
            if (!authorsCollection.stream().anyMatch(x -> x.getName().equals(author))) {
                System.out.println("Автора " + author + " не существует. Добавляем...");
                authorBuilder = new AuthorBuilder().setName(author);
                Author result = authorBuilder.getResult();
                authors.add(result);
                authorsCollection.add(result);
                System.out.println("Автор " + author + " добавлен");
            } else {
                authors.add(authorsCollection.stream().filter(x -> x.getName().equals(author)).findAny().get());
            }
        }
        bookBuilder.setAuthors(authors);

        System.out.println("---Введите название издателя---");
        String publisher = bufferedReader.readLine();
        Publisher result;
        if (!publishersCollection.stream().anyMatch(x -> x.getName().equals(publisher))) {
            System.out.println("Издателя " + publisher + " не существует. Добавляем...");
            publisherBuilder = new PublisherBuilder().setName(publisher);
            result = publisherBuilder.getResult();
            publishersCollection.add(result);
            System.out.println("Издатель " + publisher + " добавлен");
        } else {
            result = publishersCollection.stream().filter(x -> x.getName().equals(publisher)).findAny().get();
        }

        bookBuilder.setPublisher(result);
        booksCollection.add(bookBuilder.getResult());

    }

    private void addNewPublisher() throws IOException {
        System.out.println("---Введите название издательства---");
        String input = bufferedReader.readLine();
        if (!publishersCollection.stream().anyMatch(x -> x.getName().equals(input))) {
            System.out.println("Добавление...");
            PublisherBuilder publisherBuilder = new PublisherBuilder().setName(input);
            publishersCollection.add(publisherBuilder.getResult());
            System.out.println("Издатель добавлен");
        } else {
            System.out.println("Данное издательство уже добавлено");
        }
    }

    private void addNewAuthor() throws IOException {
        System.out.println("---Введите имя автора---");
        String input = bufferedReader.readLine();
        if (!authorsCollection.stream().anyMatch(x -> x.getName().equals(input))) {
            System.out.println("Добавление...");
            AuthorBuilder authorBuilder = new AuthorBuilder().setName(input);
            authorsCollection.add(authorBuilder.getResult());
            System.out.println("Автор добавлен");
        } else {
            System.out.println("Данный автор уже добавлен");
        }
    }

    private void deleteBook() throws IOException {
        System.out.println("---Введите имя книги---");
        String input = bufferedReader.readLine();
        if (booksCollection.stream().anyMatch(x -> x.getName().equals(input))) {
            System.out.println("Удаление...");
            Book book = booksCollection.stream().filter(x -> x.getName().equals(input)).findAny().get();
            booksCollection.remove(book);
            System.out.println("Книга удалена");
        } else {
            System.out.println("Книги с таким названием не существует");
        }
    }

    private void deleteAuthor() throws IOException {
        System.out.println("---Введите имя автора---");
        String input = bufferedReader.readLine();
        if (authorsCollection.stream().anyMatch(x -> x.getName().equals(input))) {
            System.out.println("Удаление...");
            Author author = authorsCollection.stream().filter(x -> x.getName().equals(input)).findAny().get();
            authorsCollection.remove(author);
            System.out.println("Автор удалён");
        } else {
            System.out.println("Автора с таким именем не существует");
        }
    }

    private void deletePublisher() throws IOException {
        System.out.println("---Введите название издательства---");
        String input = bufferedReader.readLine();
        if (publishersCollection.stream().anyMatch(x -> x.getName().equals(input))) {
            System.out.println("Удаление...");
            Publisher publisher = publishersCollection.stream().filter(x -> x.getName().equals(input)).findAny().get();
            publishersCollection.remove(publisher);
            System.out.println("Издательство удалено");
        } else {
            System.out.println("Издателя с таким названием не существует");
        }
    }


}
