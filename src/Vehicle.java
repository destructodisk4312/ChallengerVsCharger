import java.util.Random;

// --- Attribute: Abstract class ---
// Provides base logic for all vehicles and implements Draggable interface
public abstract class Vehicle implements Draggable {
    protected final String brand;
    protected final String model;
    protected final Trim trim; // --- Attribute: Association (Vehicle has a Trim)

    protected Vehicle(String brand, String model, Trim trim) {
        this.brand = brand;
        this.model = model;
        this.trim = trim;
    }

    // --- Attribute: Randomness (dice rolls simulate racing time) ---
    protected int rollDie(Random rng) {
        return rng.nextInt(6) + 1;
    }

    protected int dieToSeconds(int die) {
        return die * 10; // maps die roll to seconds
    }

    protected double reactionJitter(Random rng) {
        return 0.1 + rng.nextDouble() * 0.4; // small random delay
    }

    // --- Attribute: Polymorphism (overridden in subclasses) ---
    protected double getModelBias() {
        return 0.0;
    }

    @Override
    public String getDisplayName() {
        return brand + " " + model + " " + trim.label;
    }

    @Override
    public double runQuarterMile(Random rng) {
        // --- Attribute: Randomness (dice rolls + jitter) ---
        int roll1 = rollDie(rng);
        int roll2 = rollDie(rng);
        int baseTime = dieToSeconds(roll1) + dieToSeconds(roll2);

        // --- Attribute: Polymorphism (subclass may modify getModelBias()) ---
        double time = baseTime
                + trim.perfOffsetSeconds
                + reactionJitter(rng)
                + getModelBias();

        return Math.max(time, 0.0);
    }
}