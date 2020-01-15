package stepOneConsoleApp.dao;

import stepOneConsoleApp.entities.Author;
import stepOneConsoleApp.entities.Publisher;

import java.util.ArrayList;
import java.util.List;

public class PublisherList {

    private static PublisherList publisherList = new PublisherList();

    private List<Publisher> publishers = new ArrayList<>();

    private PublisherList(){}

    public void deletePublisher(Publisher publisher) {
        publishers.remove(publisher);
    }

    public void addPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    public List<Publisher> getPublishers(){
        return publishers;
    }

    public static PublisherList getInstance(){
        return publisherList;
    }

}
