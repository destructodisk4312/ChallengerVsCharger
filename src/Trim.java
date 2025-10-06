// --- Attribute: Enum used for organizing vehicle trims ---
// Encapsulates constants with performance offsets for realism
public enum Trim {
    SXT("SXT", +3.0, "Entry trim, heavier and least powerful"),
    RT("R/T", +1.5, "Middle trim, a bit quicker"),
    HELLCAT("Hellcat", -2.0, "Supercharged beast, very fast"),
    DEMON("Demon", -4.0, "Street-legal drag car, ultimate gamble");

    public final String label;
    public final double perfOffsetSeconds; // --- Attribute: Adds variability (performance tuning)
    public final String description;

    Trim(String label, double perfOffsetSeconds, String description) {
        this.label = label;
        this.perfOffsetSeconds = perfOffsetSeconds;
        this.description = description;
    }
}