package stepOneConsoleApp.factory;

import java.io.IOException;

public interface IAction {

    void getAll();

    void addNew() throws IOException;

    void delete() throws IOException;

}
