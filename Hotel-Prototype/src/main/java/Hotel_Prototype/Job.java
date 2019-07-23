package Hotel_Prototype;

public class Job {
    int id;
    String name;

    public Job(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Job() {
        this.id = 0;
        this.name = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + id +"\n"+
                "name: " + name + "\n";
    }
}
