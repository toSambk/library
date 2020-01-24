package jdbcTask.factory.addNewAction;

import jdbcTask.dao.AuthorDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class AddNewAuthorAction implements AddNewAction {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private AuthorDao authorDao;

    public AddNewAuthorAction() {
        authorDao = AuthorDao.getInstance();
    }

    @Override
    public void execute() throws IOException {

        System.out.println("Введите имя автора для добавления: ");
        String nameAuthor = bufferedReader.readLine();
        try {
            System.out.println("Добавление автора...");
            authorDao.addNew(nameAuthor);
            System.out.println("Добавлено.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка добавления!!!");
        }

    }
}
