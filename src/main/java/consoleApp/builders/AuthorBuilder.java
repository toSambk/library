package consoleApp.builders;

import consoleApp.entities.Author;

public class AuthorBuilder implements Builder {

    private String name;

    @Override
    public AuthorBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Author getResult() {
        if (name == null) throw new IllegalArgumentException();

        return new Author(name);
    }
}
