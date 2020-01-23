package jdbcTask.factory.getAllAction;

import jdbcTask.dao.BookDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAllBooksAction implements GetAllAction {

    private BookDao bookDao;

    public GetAllBooksAction() {
        bookDao = BookDao.getInstance();
    }

    @Override
    public void execute() throws SQLException {

        System.out.println("---Список всех книг---");
        ResultSet metaInfo = bookDao.getMetaInfo();


    }
}
