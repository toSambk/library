package jdbcTask.factory.updateAction;

import jdbcTask.dao.AuthorDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class UpdateAuthorAction implements UpdateAction {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private AuthorDao authorDao;

    public UpdateAuthorAction() {
        authorDao = AuthorDao.getInstance();
    }

    @Override
    public void execute() throws IOException {

        System.out.println("Поменять можно только имя. Введите имя автора для изменения: ");
        String authorOldName = bufferedReader.readLine();

        try {
            authorDao.getAuthorByName(authorOldName);
        } catch (SQLException e) {
            System.out.println("Указанного автора не сущетсвует!");
            e.printStackTrace();
            return;
        }

        System.out.println("Введите новое имя: ");
        String authorNewName = bufferedReader.readLine();
        try {
            System.out.println("Добавление...");
            authorDao.updateAuthorName(authorOldName, authorNewName);
            System.out.println("Добавлено.");
        } catch (SQLException e) {
            System.out.println("Неудачная операция!");
            e.printStackTrace();
        }

    }
}
