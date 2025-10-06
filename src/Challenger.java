// --- Attribute: Hierarchical Inheritance ---
// Challenger extends DodgeCar, sibling to Charger
public class Challenger extends DodgeCar {
    public Challenger(Trim trim) {
        super("Challenger", trim);
    }

    @Override
    protected double getModelBias() {
        return -0.2; // --- Attribute: Polymorphism (Challenger slightly faster)
    }

    @Override
    public String getDisplayName() {
        return "🏁 " + super.getDisplayName(); // --- Attribute: Polymorphism (adds emoji)
    }
}