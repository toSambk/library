package jdbcTask.dao;


import jdbcTask.App;
import jdbcTask.entities.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookDao {

    private static BookDao instance;

    private AuthorBookDao authorBookDao;

    private Connection connection;

    private BookDao() {
        connection = App.getConnection();
        authorBookDao = AuthorBookDao.getInstance();
    }

    public static BookDao getInstance() {
        if (instance == null) {
            instance = new BookDao();
        }
        return instance;
    }

    public void addNew(String name) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("insert into book(name) values(?)");
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
    }

    public List<Book> getAll() {

        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *from book ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String bookName = resultSet.getString("name");
                books.add(new Book(bookId, bookName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public int getIdByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("select id from book where name = (?)");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }

    public ResultSet getMetaInfo() throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("select name, authors from book left join (select book_author.book_id as book_id, group_concat(author.name) as authors\n" +
                        "from book_author left join author on book_author.author_id = author.id group by book_id) as magic\n" +
                        "on book.id = magic.book_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;

    }


}
