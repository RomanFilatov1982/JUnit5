package guru.qa.date;


public enum Languages {

    DEUTSCH("Finden Sie Ihre nächste Unterkunft", "Deutsch"),
    ENGLISH("Find your next stay", "English (UK)"),
    POLSKI("Znajdź miejsce na kolejny pobyt", "Polski");

    public final String description;
    public final String name;

    Languages(String description, String name) {
        this.description = description;
        this.name = name;
    }
}
