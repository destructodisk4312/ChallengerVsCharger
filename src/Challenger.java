public class Challenger extends DodgeCar {
    public Challenger(Trim trim) {
        super("Challenger", trim);
    }

    @Override
    protected double getModelBias() {
        return -0.2; // Slightly faster
    }

    @Override
    public String getDisplayName() {
        return "🏁 " + super.getDisplayName();
    }
}