package stepOneConsoleApp.entities;

public class Publisher {

    private String name;

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
