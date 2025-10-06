// --- Attribute: Hierarchical Inheritance ---
// Charger extends DodgeCar, sibling to Challenger
public class Charger extends DodgeCar {
    public Charger(Trim trim) {
        super("Charger", trim);
    }

    @Override
    protected double getModelBias() {
        return +0.2; // --- Attribute: Polymorphism (Charger slightly slower)
    }

    @Override
    public String getDisplayName() {
        return "🚦 " + super.getDisplayName(); // --- Attribute: Polymorphism (adds emoji)
    }
}