package consoleApp.builders;

import consoleApp.entities.Publisher;

public class PublisherBuilder implements Builder {

    private String name;

    @Override
    public PublisherBuilder setName(String name) {
        this.name = name;
        return this;
    }


    public Publisher getResult() {
        if (name == null) throw new IllegalArgumentException();

        return new Publisher(name);
    }
}
