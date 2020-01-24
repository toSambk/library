package jdbcTask.dao;


import jdbcTask.App;
import jdbcTask.entities.Author;
import jdbcTask.entities.Book;
import jdbcTask.entities.BookInfo;
import jdbcTask.entities.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class BookDao {

    private static BookDao instance;

    private AuthorDao authorDao;

    private Connection connection;

    private AuthorBookDao authorBookDao;

    private PublisherDao publisherDao;

    private BookDao() {
        connection = App.getConnection();
        authorDao = AuthorDao.getInstance();
        authorBookDao = AuthorBookDao.getInstance();
        publisherDao = PublisherDao.getInstance();
    }

    public static BookDao getInstance() {
        if (instance == null) {
            instance = new BookDao();
        }
        return instance;
    }

    public Book addNew(String name) throws SQLException {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("insert into book(name) values(?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
        try (PreparedStatement result = connection
                .prepareStatement("select id from book where name = (?)")) {
            result.setString(1, name);
            ResultSet resultSet = result.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");
            return new Book(id, name);
        }
    }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select *from book ")) {
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

    private Book getBookByName(String name) throws SQLException {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("select id from book where name = (?)")) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Book(resultSet.getInt("id"), name);
        }
    }

    public List<BookInfo> getAllMetaForView() throws SQLException {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("select test.name, test.authors, publisher.name as publisher from\n" +
                        "(select name, authors, id from book left join (select book_author.book_id as book_id, group_concat(author.name) as authors\n" +
                        "from book_author left join author on book_author.author_id = author.id group by book_id) as magic\n" +
                        "on book.id = magic.book_id) as test left join publisher on test.id = publisher.book_id")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<BookInfo> bookInfos = new ArrayList<>();
            while (resultSet.next()) {
                String name = Optional.ofNullable(resultSet.getString("name")).orElse("<неизвестно>");
                String authors = Optional.ofNullable(resultSet.getString("authors")).orElse("<неизвестно>");
                String publisher = Optional.ofNullable(resultSet.getString("publisher")).orElse("<неизвестно>");
                bookInfos.add(new BookInfo(name, authors, publisher));
            }
            return bookInfos;
        }
    }

    public void deleteByName(String name) throws SQLException {
        Book book = getBookByName(name);
        try (PreparedStatement publisherDelSt = connection
                .prepareStatement("delete from publisher where book_id = (?)")) {
            publisherDelSt.setInt(1, book.getId());
            publisherDelSt.executeUpdate();
        }
        try (PreparedStatement preDel = connection
                .prepareStatement("delete from book_author where book_id = (?)")) {
            preDel.setInt(1, book.getId());
            preDel.executeUpdate();
        }
        try (PreparedStatement statement = connection
                .prepareStatement("delete from book where name = (?)")) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }

    public void updateBookName(String oldName, String newName) throws SQLException {
        try (PreparedStatement statement = connection
                .prepareStatement("update book set name = (?) where name = (?)")) {
            statement.setString(1, newName);
            statement.setString(2, oldName);
            statement.executeUpdate();
            System.out.println("Добавлено.");
        }
    }

    public void updateBookAuthor(String name, List<String> authorNames) throws SQLException {
        Book book = getBookByName(name);
        for (String curAuthorName : authorNames) {
            Author author;
            try {
                author = authorDao.getAuthorByName(curAuthorName);
            } catch (SQLException exc) {
                System.out.println("Автора " + curAuthorName + " не существует! Прекращение операции...");
                return;
            }
            if (authorBookDao.bookContainsAuthor(book, author)) {
                System.out.println("Книга " + book.getName() + " уже имеет автора " + author.getName());
            } else {
                try (PreparedStatement authorSt = connection
                        .prepareStatement("insert book_author(book_id, author_id) values (?, ?)")) {
                    authorSt.setInt(1, book.getId());
                    authorSt.setInt(2, author.getId());
                    authorSt.executeUpdate();
                    System.out.println("Добавлено.");
                }
            }
        }
    }

    public void updateBookPublisher(String bookName, String publisherName) throws SQLException {
        Book book = getBookByName(bookName);
        Publisher publisher;
        try {
            publisher = publisherDao.getPublisherByName(publisherName);
        } catch (SQLException exc) {
            System.out.println("Указанного издателя не существует! Прекращение операции...");
            return;
        }
        try (PreparedStatement statement = connection
                .prepareStatement("update publisher set book_id = (?) where id = (?)")) {
            statement.setInt(1, book.getId());
            statement.setInt(2, publisher.getId());
            statement.executeUpdate();
            System.out.println("Добавлено.");
        }
    }


}
