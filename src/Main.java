import java.util.*;

// --- Attribute: Must use classes ---
// Main class controls the game loop, betting system, and interactions
public class Main {
    private final Scanner in = new Scanner(System.in);
    private final Random rng = new Random();

    // --- Attribute: Must be interactive + use of variables to track state ---
    private int playerMoney = 5000;      // starting balance for player
    private int computerMoney = 5000;    // starting balance for computer
    private boolean playerHasCar = true; // tracks if player still has Challenger

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        printBanner();

        boolean playAgain;
        do {
            // --- Attribute: Must use loops (game loop keeps running until quit) ---
            if (playerMoney <= 0 && playerHasCar) {
                // All-or-nothing triggered
                playAgain = allOrNothingRace();
            } else if (playerMoney <= 0 && !playerHasCar) {
                // Ultimate LOSER condition
                System.out.println("\n💀 You have no money and no car... You are the ULTIMATE LOSER! 💀");
                break;
            } else {
                playAgain = normalRace();
            }
        } while (playAgain); // --- Attribute: loop (replay loop) ---

        System.out.println("\nGame Over. Thanks for racing!");
    }

    // --- Attribute: Readability + User Guidance ---
    // Print a banner explaining rules and setup
    private void printBanner() {
        System.out.println("==============================================");
        System.out.println("      D O D G E   D R A G   R A C E R");
        System.out.println("==============================================");
        System.out.println("Rules:");
        System.out.println(" • Start with $5000 each.");
        System.out.println(" • Place a bet before each race.");
        System.out.println(" • Winner takes the pot.");
        System.out.println(" • Lose all money? All-or-Nothing race for your car!");
        System.out.println(" • Win all-or-nothing = $10,000 + car.");
        System.out.println(" • Lose all-or-nothing = $0 and no car. ULTIMATE LOSER!");
        System.out.println("----------------------------------------------");
    }

    private boolean normalRace() {
        System.out.printf("\n💵 You: $%d | Computer: $%d%n", playerMoney, computerMoney);

        // --- Attribute: Hierarchical inheritance ---
        // Player chooses Challenger (subclass of DodgeCar, which extends Vehicle)
        Trim chosenTrim = chooseTrim();
        Vehicle player = new Challenger(chosenTrim);

        // --- Attribute: Randomness ---
        // Computer randomly picks any Charger trim
        Trim[] trims = Trim.values();
        Trim computerTrim = trims[rng.nextInt(trims.length)];
        Vehicle computer = new Charger(computerTrim);

        System.out.println("\nYour car:     " + player.getDisplayName());
        System.out.println("Opponent car: " + computer.getDisplayName());

        // --- Attribute: Interactivity (player inputs bet) ---
        int bet = getBetAmount();
        System.out.println("Bet placed: $" + bet);

        // --- Attribute: Polymorphism ---
        // runQuarterMile() behaves differently depending on Challenger vs Charger
        double playerTime = runAndReport(player, "You");
        double computerTime = runAndReport(computer, "Computer");

        int result = announceWinner(playerTime, computerTime);
        if (result == 1) {
            playerMoney += bet;
            computerMoney -= bet;
            System.out.println("💰 You won $" + bet + "! New balance: $" + playerMoney);
        } else if (result == -1) {
            playerMoney -= bet;
            computerMoney += bet;
            System.out.println("❌ You lost $" + bet + "! New balance: $" + playerMoney);
        } else {
            System.out.println("Tie — no money exchanged.");
        }

        if (playerMoney <= 0) {
            System.out.println("\n⚠️ You’re broke! Next race will be ALL OR NOTHING with your car!");
        }

        // --- Attribute: Loop (asks player to continue) ---
        return askYesNo("\nRace again? (y/n): ");
    }

    private boolean allOrNothingRace() {
        System.out.println("\n🚨 ALL OR NOTHING RACE! 🚨");
        System.out.println("If you win: You get back your car and $10,000!");
        System.out.println("If you lose: You lose your car and have NOTHING... the Ultimate LOSER.");

        // Player chooses Challenger trim again
        Trim chosenTrim = chooseTrim();
        Vehicle player = new Challenger(chosenTrim);

        // Computer randomly picks Charger trim
        Trim[] trims = Trim.values();
        Trim computerTrim = trims[rng.nextInt(trims.length)];
        Vehicle computer = new Charger(computerTrim);

        System.out.println("\nYour car:     " + player.getDisplayName());
        System.out.println("Opponent car: " + computer.getDisplayName());

        // Race outcome
        double playerTime = runAndReport(player, "You");
        double computerTime = runAndReport(computer, "Computer");

        int result = announceWinner(playerTime, computerTime);
        if (result == 1) {
            // --- Attribute: Interactivity (all-or-nothing reset mechanic) ---
            playerMoney = 10000;
            playerHasCar = true;
            System.out.println("🔥 YOU WON THE ALL OR NOTHING RACE! You now have your car and $10,000!");
        } else {
            playerMoney = 0;
            playerHasCar = false;
            System.out.println("💀 You lost everything. No money, no car. Ultimate LOSER!");
            return false;
        }

        return askYesNo("\nDo you want to keep racing? (y/n): ");
    }

    private Trim chooseTrim() {
        System.out.println("\nChoose your Challenger trim:");
        Trim[] trims = Trim.values();

        // --- Attribute: Loop (forces valid input) ---
        while (true) {
            for (int i = 0; i < trims.length; i++) {
                System.out.printf(" %d) %-10s (%s)%n", i + 1, trims[i].label, trims[i].description);
            }
            System.out.print("Enter choice: ");
            String line = in.nextLine().trim();
            try {
                int idx = Integer.parseInt(line) - 1;
                if (idx >= 0 && idx < trims.length) {
                    return trims[idx];
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid selection.");
        }
    }

    private int getBetAmount() {
        while (true) {
            System.out.print("Enter your bet amount: $");
            try {
                int bet = Integer.parseInt(in.nextLine().trim());
                if (bet > 0 && bet <= playerMoney && bet <= computerMoney) {
                    return bet;
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid bet. Must be positive and <= both balances.");
        }
    }

    private double runAndReport(Vehicle racer, String label) {
        // --- Attribute: Polymorphism ---
        // Different subclasses may adjust model bias (Challenger faster, Charger slower)
        double officialTime = racer.runQuarterMile(rng);
        System.out.printf("%s — %s%n", label, racer.getDisplayName());
        System.out.printf("   Time: %.3f sec%n", officialTime);
        return officialTime;
    }

    private int announceWinner(double playerTime, double computerTime) {
        System.out.println("\n===== RESULTS =====");
        System.out.printf("You:      %.3f sec%n", playerTime);
        System.out.printf("Computer: %.3f sec%n", computerTime);

        if (Math.abs(playerTime - computerTime) < 1e-9) {
            System.out.println("It’s a TIE! 🔥");
            return 0;
        } else if (playerTime < computerTime) {
            System.out.println("You WIN! 🏆");
            return 1;
        } else {
            System.out.println("Computer wins. Try again!");
            return -1;
        }
    }

    private boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim().toLowerCase();
            if (s.equals("y") || s.equals("yes")) return true;
            if (s.equals("n") || s.equals("no")) return false;
            System.out.println("Please enter y/n.");
        }
    }
}