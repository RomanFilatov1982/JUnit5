package guru.qa.date;


public enum Languages {

    DEUTSCH("Günstigere Flüge nur für Mitglieder", "Deutsch"),
    ENGLISH("Unlock Flights savings with member-only deals", "English (UK)"),
    POLSKI("Oszczędności dzięki ofertom dla członków", "Polski");

    public final String description;
    public final String name;

    Languages(String description, String name) {
        this.description = description;
        this.name = name;
    }
}
