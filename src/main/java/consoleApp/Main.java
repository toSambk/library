package consoleApp;

import consoleApp.entities.Author;
import consoleApp.entities.Book;
import consoleApp.entities.Publisher;
import consoleApp.factory.ActionFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        boolean inProgress = true;
        while (inProgress) {
            main.menu();
            String input = main.bufferedReader.readLine();

            switch (input) {
                case "1":
                    new ActionFactory().getAction(Book.class).getAll();
                    break;

                case "2":
                    new ActionFactory().getAction(Author.class).getAll();
                    break;

                case "3":
                    new ActionFactory().getAction(Publisher.class).getAll();
                    break;

                case "4":
                    new ActionFactory().getAction(Book.class).addNew();
                    break;

                case "5":
                    new ActionFactory().getAction(Author.class).addNew();
                    break;

                case "6":
                    new ActionFactory().getAction(Publisher.class).addNew();
                    break;

                case "7":
                    new ActionFactory().getAction(Book.class).delete();
                    break;

                case "8":
                    new ActionFactory().getAction(Author.class).delete();
                    break;

                case "9":
                    new ActionFactory().getAction(Publisher.class).delete();
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
