import java.util.Random;
import java.util.Scanner;
public class GuessingGame {
    public static int startRound(int maxTries) {
        Random random = new Random();
        int targetNumber = random.nextInt(100) + 1; 
        Scanner input = new Scanner(System.in);
        int attemptsMade = 0;
        System.out.println("Guess the number I've chosen between 1 and 100!");
        while (attemptsMade < maxTries) {
            System.out.print("Enter your guess: ");
            int userGuess;
            try {
                userGuess = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            attemptsMade++;
            if (userGuess == targetNumber) {
                System.out.println("Well done! You found the number in " + attemptsMade + " attempt(s).");
                return maxTries - attemptsMade + 1; 
            } else if (userGuess < targetNumber) {
                System.out.println("Your guess is too low. Try again!");
            } else {
                System.out.println("Your guess is too high. Try again!");
            }
        }
        System.out.println("Out of attempts! The correct number was " + targetNumber + ".");
        return 0;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int maxTries = 7; 
        int overallScore = 0;
        int roundCounter = 1;
        while (true) {
            System.out.println("\n--- Starting Round " + roundCounter + " ---");
            int roundScore = startRound(maxTries);
            overallScore += roundScore;
            System.out.println("Score this round: " + roundScore);
            System.out.println("Total score: " + overallScore);
            System.out.print("Would you like to play another round? (yes/no): ");
            String response = input.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
            roundCounter++;
        }
        System.out.println("\nThanks for playing!");
        System.out.println("Rounds completed: " + roundCounter);
        System.out.println("Final score: " + overallScore);
    }
}