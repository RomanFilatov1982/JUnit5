package guru.qa.date;

public enum Languages {
    //Русский, English (UK),
    Deutsch("Ihr Mietwagen für jede Reise"), Español("Alquiler de coches para cualquier tipo de viaje");

    public final String description;

    Languages(String description) {
        this.description = description;
    }
}
