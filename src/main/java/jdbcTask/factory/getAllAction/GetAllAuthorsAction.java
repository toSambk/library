package jdbcTask.factory.getAllAction;

import jdbcTask.dao.AuthorDao;

public class GetAllAuthorsAction implements GetAllAction {

    private AuthorDao authorDao;

    public GetAllAuthorsAction() {
        authorDao = AuthorDao.getInstance();
    }

    @Override
    public void execute() {
        System.out.println("---Список всех авторов---");
        authorDao.getAll().forEach(x -> System.out.println("Имя автора: " + x.getName()));
    }
}
