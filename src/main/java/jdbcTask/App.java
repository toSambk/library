package jdbcTask;

import jdbcTask.entities.Author;
import jdbcTask.entities.Book;
import jdbcTask.entities.Publisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    private static Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/library";
    private final String user = "root";
    private final String password = "password";
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException, SQLException {

        App app = new App();
        AppConfig config = new AppConfig();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(app.url, app.user, app.password);
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение с базой. Выключение...");
            return;
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        boolean inProgress = true;
        while (inProgress) {
            app.menu();
            String input = app.bufferedReader.readLine();

            switch (input) {
                case "1":
                    config.getAction(Book.class, ActionType.GET).execute();
                    break;

                case "2":
                    config.getAction(Author.class, ActionType.GET).execute();
                    break;

                case "3":
                    config.getAction(Publisher.class, ActionType.GET).execute();
                    break;

                case "4":
                    config.getAction(Book.class, ActionType.ADD).execute();
                    break;

                case "5":
                    config.getAction(Author.class, ActionType.ADD).execute();
                    break;

                case "6":
                    config.getAction(Publisher.class, ActionType.ADD).execute();
                    break;

                case "7":
                    config.getAction(Book.class, ActionType.DELETE).execute();
                    break;

                case "8":
                    config.getAction(Author.class, ActionType.DELETE).execute();
                    break;

                case "9":
                    config.getAction(Publisher.class, ActionType.DELETE).execute();
                    break;

                case "10":
                    config.getAction(Book.class, ActionType.UPDATE).execute();
                    break;

                case "11":
                    config.getAction(Author.class, ActionType.UPDATE).execute();
                    break;

                case "12":
                    config.getAction(Publisher.class, ActionType.UPDATE).execute();
                    break;

                case "e":
                    inProgress = false;
                    app.closeup();
                    break;

                default:
                    System.out.println("Неопределённая операция!");
                    break;
            }

        }

    }

    public static Connection getConnection() {
        return connection;
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
        System.out.println("10. Изменить книгу по имени");
        System.out.println("11. Изменить автора по имени");
        System.out.println("12. Изменить издателя по имени");
        System.out.println("--------------------------");
        System.out.println("Введите цифру от 1 до 9");
    }

    private void closeup() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("----------------------------");
        System.out.println("Завершение работы...");
    }


}
