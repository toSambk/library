package stepOneConsoleApp.factory;

import stepOneConsoleApp.builders.AuthorBuilder;
import stepOneConsoleApp.dao.AuthorList;
import stepOneConsoleApp.entities.Author;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AuthorActionImpl implements IAction {

    private AuthorList authorList = AuthorList.getInstance();

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public AuthorActionImpl(){}

    @Override
    public void getAll() {
        System.out.println("---Список всех авторов---");
        authorList.getAuthors().forEach(x -> System.out.print("Имя: " + x.getName() + "\n"));
    }

    @Override
    public void addNew() throws IOException {

        System.out.println("---Введите имя автора---");
        String input = bufferedReader.readLine();
        if (authorList.getAuthors().stream().noneMatch(x -> x.getName().equals(input))) {
            System.out.println("Добавление...");
            AuthorBuilder authorBuilder = new AuthorBuilder().setName(input);
            authorList.addAuthor(authorBuilder.getResult());
            System.out.println("Автор добавлен");
        } else {
            System.out.println("Данный автор уже добавлен");
        }

    }

    @Override
    public void delete() throws IOException {
        System.out.println("---Введите имя автора---");
        String input = bufferedReader.readLine();
        if (authorList.getAuthors().stream().anyMatch(x -> x.getName().equals(input))) {
            System.out.println("Удаление...");
            Author author = authorList.getAuthors().stream().filter(x -> x.getName().equals(input)).findAny().get();
            authorList.getAuthors().remove(author);
            System.out.println("Автор удалён");
        } else {
            System.out.println("Автора с таким именем не существует");
        }
    }
}
