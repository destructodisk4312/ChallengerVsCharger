// --- Attribute: Multilevel Inheritance ---
// DodgeCar inherits from Vehicle and is extended by Challenger/Charger
public abstract class DodgeCar extends Vehicle {
    protected DodgeCar(String model, Trim trim) {
        super("Dodge", model, trim);
    }
}