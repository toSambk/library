package stepOneConsoleApp.commands;

import stepOneConsoleApp.Main;

import java.io.IOException;

public interface Action {

    void getAll();

    void addNew() throws IOException;

    void delete() throws IOException;

}
