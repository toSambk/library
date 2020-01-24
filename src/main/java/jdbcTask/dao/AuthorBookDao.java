package jdbcTask.dao;

import jdbcTask.App;
import jdbcTask.entities.Author;
import jdbcTask.entities.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void addDependency(Book book, Author author) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into book_author(book_id, author_id) VALUES (?, ?)");
        preparedStatement.setInt(1, book.getId());
        preparedStatement.setInt(2, author.getId());
        preparedStatement.executeUpdate();
    }

    public boolean bookContainsAuthor(Book book, Author author) throws SQLException {

        PreparedStatement statement = connection
                .prepareStatement("select *from book_author where book_id = (?) and author_id = (?)");
        statement.setInt(1, book.getId());
        statement.setInt(2, author.getId());
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();

    }

}
