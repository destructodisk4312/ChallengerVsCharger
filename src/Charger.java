public class Charger extends DodgeCar {
    public Charger(Trim trim) {
        super("Charger", trim);
    }

    @Override
    protected double getModelBias() {
        return +0.2; // Charger slightly slower on average
    }

    @Override
    public String getDisplayName() {
        return "🚦 " + super.getDisplayName();
    }
}