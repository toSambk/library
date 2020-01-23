package jdbcTask.factory.getAllAction;

import jdbcTask.dao.PublisherDao;

public class GetAllPublishersAction implements GetAllAction {

    private PublisherDao publisherDao;

    public GetAllPublishersAction() {
        publisherDao = PublisherDao.getInstance();
    }

    @Override
    public void execute() {
        System.out.println("---Список всех издательств---");
        publisherDao.getAll().forEach(x -> System.out.println("Имя издательства: " + x.getName()));
    }
}
