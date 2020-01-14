package stepOneConsoleApp.entities;

import java.util.Objects;

public class Publisher {

    private String name;

    public Publisher() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return name.equals(publisher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
