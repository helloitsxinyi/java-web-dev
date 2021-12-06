package club;

public class Facility {

    private String name;
    private String description;

    public Facility (String name) {
        this (name, null);
    }

    public Facility (String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName () {
        return name;
    }

    public String getDescription () {
        return description;
    }

    public void show() {
        String fullName = name;
        if (description != null) {
            fullName += " (" + description + ")";
        }
        System.out.println (fullName);
    }
}
