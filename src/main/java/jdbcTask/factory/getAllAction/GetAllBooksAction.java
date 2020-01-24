package jdbcTask.factory.getAllAction;

import jdbcTask.dao.BookDao;
import jdbcTask.entities.BookInfo;

import java.sql.SQLException;
import java.util.List;

public class GetAllBooksAction implements GetAllAction {

    private BookDao bookDao;

    public GetAllBooksAction() {
        bookDao = BookDao.getInstance();
    }

    @Override
    public void execute() {
        try {
            System.out.println("---Список всех книг---");
            List<BookInfo> books = bookDao.getAllMetaForView();
            books.forEach(x -> System.out.println("Книга: " + x.getBookName()
                    + "\tАвторы: " + x.getAuthors() + "\tИздатель: " + x.getPublisher()));
        } catch (SQLException exc) {
            System.out.println("Ошибка получения списка книг!");
            exc.printStackTrace();
        }
    }
}
