package jdbcTask.dao;

import jdbcTask.App;
import jdbcTask.entities.Author;
import jdbcTask.entities.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorBookDao {

    private static AuthorBookDao instance;
    private Connection connection;

    private AuthorBookDao() {
        connection = App.getConnection();
    }

    public static AuthorBookDao getInstance() {
        if (instance == null) {
            instance = new AuthorBookDao();
        }

        return instance;

    }

    public void addDependency(int bookId, int authorId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into book_author(book_id, author_id) VALUES (?, ?)");
        preparedStatement.setInt(1, bookId);
        preparedStatement.setInt(2, authorId);
        preparedStatement.executeUpdate();
    }

}
