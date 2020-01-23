package jdbcTask.entities;

public class Publisher {

    private int id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Publisher() {
    }

    public Publisher(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;

}
