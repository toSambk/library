package stepOneConsoleApp;

import stepOneConsoleApp.builders.AuthorBuilder;
import stepOneConsoleApp.builders.BookBuilder;
import stepOneConsoleApp.builders.PublisherBuilder;
import stepOneConsoleApp.commands.AuthorAction;
import stepOneConsoleApp.commands.BookAction;
import stepOneConsoleApp.commands.PublisherAction;
import stepOneConsoleApp.dao.PublisherList;
import stepOneConsoleApp.entities.Author;
import stepOneConsoleApp.entities.Book;
import stepOneConsoleApp.entities.Publisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private List<Book> booksCollection = new ArrayList<>();

    private List<Author> authorsCollection = new ArrayList<>();

    private List<Publisher> publishersCollection = new ArrayList<>();

    private BookBuilder bookBuilder = new BookBuilder();

    private AuthorBuilder authorBuilder = new AuthorBuilder();

    private PublisherBuilder publisherBuilder = new PublisherBuilder();


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        boolean inProgress = true;
        while (inProgress) {
            main.menu();
            String input = main.bufferedReader.readLine();

            switch (input) {
                case "1":
                    new BookAction().getAll();
                    break;

                case "2":
                    new AuthorAction().getAll();
                    break;

                case "3":
                    new PublisherAction().getAll();
                    break;

                case "4":
                    new BookAction().addNew();
                    break;

                case "5":
                    new AuthorAction().addNew();
                    break;

                case "6":
                    new PublisherAction().addNew();
                    break;

                case "7":
                    new BookAction().delete();
                    break;

                case "8":
                    new AuthorAction().delete();
                    break;

                case "9":
                    new PublisherAction().delete();
                    break;

                case "e":
                    inProgress = false;
                    main.closeup();
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

    private void closeup() {
        System.out.println("----------------------------");
        System.out.println("Завершение работы...");
    }

}
