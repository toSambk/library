package stepOneConsoleApp.factory;

import stepOneConsoleApp.builders.PublisherBuilder;
import stepOneConsoleApp.dao.PublisherList;
import stepOneConsoleApp.entities.Publisher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PublisherActionImpl implements Action {

    private PublisherList publisherList = PublisherList.getInstance();

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public PublisherActionImpl(){}

    @Override
    public void getAll() {
        System.out.println("---Список всех издательств---");
        publisherList.getPublishers().forEach(x -> System.out.print("Название: " + x.getName() + "\n"));
    }

    @Override
    public void addNew() throws IOException {
        System.out.println("---Введите название издательства---");
        String input = bufferedReader.readLine();
        if (publisherList.getPublishers().stream().noneMatch(x -> x.getName().equals(input))) {
            System.out.println("Добавление...");
            PublisherBuilder publisherBuilder = new PublisherBuilder().setName(input);
            publisherList.getPublishers().add(publisherBuilder.getResult());
            System.out.println("Издатель добавлен");
        } else {
            System.out.println("Данное издательство уже добавлено");
        }
    }

    @Override
    public void delete() throws IOException {

        System.out.println("---Введите название издательства---");
        String input = bufferedReader.readLine();
        if (publisherList.getPublishers().stream().anyMatch(x -> x.getName().equals(input))) {
            System.out.println("Удаление...");
            Publisher publisher = publisherList.getPublishers().stream().filter(x -> x.getName().equals(input)).findAny().get();
            publisherList.deletePublisher(publisher);
            System.out.println("Издательство удалено");
        } else {
            System.out.println("Издателя с таким названием не существует");
        }

    }
}
