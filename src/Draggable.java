import java.util.Random;

// --- Attribute: Must make use of an interface ---
// Defines a contract that all raceable vehicles must follow
public interface Draggable {
    double runQuarterMile(Random rng); // --- Attribute: Polymorphism (different vehicles implement differently)
    String getDisplayName();           // --- Attribute: Polymorphism (Challenger/Charger override formatting)
}
