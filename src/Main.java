private void run() {
    printBanner();
    boolean playAgain;
    do {
        // Player chooses their Challenger trim
        Trim chosenTrim = chooseTrim();
        Vehicle player = new Challenger(chosenTrim);

        // Computer randomly picks ANY Charger trim
        Trim[] trims = Trim.values();
        Trim computerTrim = trims[rng.nextInt(trims.length)];
        Vehicle computer = new Charger(computerTrim);

        System.out.println("\nYour car:     " + player.getDisplayName());
        System.out.println("Opponent car: " + computer.getDisplayName());

        double playerTime = runAndReport(player, "You");
        double computerTime = runAndReport(computer, "Computer");

        announceWinner(playerTime, computerTime);
        playAgain = askYesNo("\nRace again? (y/n): ");
    } while (playAgain);

    System.out.println("Thanks for racing!");
}