package jdbcTask.factory.updateAction;

import jdbcTask.dao.PublisherDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class UpdatePublisherAction implements UpdateAction {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private PublisherDao publisherDao;

    public UpdatePublisherAction() {
        publisherDao = PublisherDao.getInstance();
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Поменять можно только название. Введите название издателя для изменения: ");
        String publisherOldName = bufferedReader.readLine();

        try {
            publisherDao.getPublisherByName(publisherOldName);
        } catch (SQLException e) {
            System.out.println("Указанного издателя не сущетсвует!");
            e.printStackTrace();
            return;
        }

        System.out.println("Введите новое название: ");
        String publisherNewName = bufferedReader.readLine();
        try {
            System.out.println("Добавление...");
            publisherDao.updatePublisherName(publisherOldName, publisherNewName);
        } catch (SQLException e) {
            System.out.println("Неудачная операция!");
            e.printStackTrace();
        }
    }
}
