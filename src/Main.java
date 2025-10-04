import java.util.*;

public class Main {
    private final Scanner in = new Scanner(System.in);
    private final Random rng = new Random();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        printBanner();
        boolean playAgain;
        int playerWins = 0;
        int computerWins = 0;
        int ties = 0;

        do {
            // Player chooses Challenger trim
            Trim chosenTrim = chooseTrim();
            Vehicle player = new Challenger(chosenTrim);

            // Computer randomly picks a Charger trim
            Trim[] trims = Trim.values();
            Trim computerTrim = trims[rng.nextInt(trims.length)];
            Vehicle computer = new Charger(computerTrim);

            System.out.println("\nYour car:     " + player.getDisplayName());
            System.out.println("Opponent car: " + computer.getDisplayName());

            double playerTime = runAndReport(player, "You");
            double computerTime = runAndReport(computer, "Computer");

            int result = announceWinner(playerTime, computerTime);
            if (result == 0) ties++;
            else if (result == 1) playerWins++;
            else computerWins++;

            // Show running stats
            System.out.println("\n===== SCOREBOARD =====");
            System.out.println("You: " + playerWins + " wins");
            System.out.println("Computer: " + computerWins + " wins");
            System.out.println("Ties: " + ties);

            playAgain = askYesNo("\nRace again? (y/n): ");
        } while (playAgain);

        System.out.println("\nFinal Results:");
        System.out.println("You: " + playerWins + " wins");
        System.out.println("Computer: " + computerWins + " wins");
        System.out.println("Ties: " + ties);
        System.out.println("Thanks for racing!");
    }

    private void printBanner() {
        System.out.println("==============================================");
        System.out.println("      D O D G E   D R A G   R A C E R");
        System.out.println("==============================================");
        System.out.println("Rules:");
        System.out.println(" • Each racer rolls a 6-sided die twice.");
        System.out.println(" • Roll of 1 = 10 sec, ..., Roll of 6 = 60 sec.");
        System.out.println(" • Small trim offsets & reaction time apply.");
        System.out.println(" • Lowest total time wins!");
        System.out.println("----------------------------------------------");
    }

    private Trim chooseTrim() {
        System.out.println("\nChoose your Challenger trim:");
        Trim[] trims = Trim.values();
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

    private double runAndReport(Vehicle racer, String label) {
        double officialTime = racer.runQuarterMile(rng);
        System.out.printf("%s — %s%n", label, racer.getDisplayName());
        System.out.printf("   Time: %.3f sec%n", officialTime);
        return officialTime;
    }

    /**
     * @return 0 for tie, 1 for player win, -1 for computer win
     */
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