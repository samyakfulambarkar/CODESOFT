import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 5;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static int roundNumber = 1;
    private static int totalScore = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain;
        do {
            System.out.println("\nRound " + roundNumber + ":");
            int randomNumber = generateRandomNumber(random);
            int attempts = 0;
            boolean guessedCorrectly = false;
            System.out.println("I have generated a random number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");
            while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
                int userGuess = getUserGuess(scanner);
                attempts++;
                if (userGuess == randomNumber) {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You've guessed the number in " + attempts + " attempts!");
                } else {
                    System.out.println(getFeedbackMessage(userGuess, randomNumber));
                }
            }
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + randomNumber + ".");
            }
            int roundScore = calculateScore(attempts);
            totalScore += roundScore;
            System.out.println("Your score for this round: " + roundScore + " points.");
            System.out.println("Total score: " + totalScore + " points.");
            roundNumber++;
            playAgain = shouldPlayAgain(scanner);
        } while (playAgain);
        System.out.println("\nThank you for playing! Goodbye!");
        scanner.close();
    }
    private static int generateRandomNumber(Random random) {
        return random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
    }
    private static int getUserGuess(Scanner scanner) {
        while (true) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            if (userGuess < MIN_NUMBER || userGuess > MAX_NUMBER) {
                System.out.println("Please enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
            } else {
                return userGuess;
            }
        }
    }
    private static String getFeedbackMessage(int userGuess, int randomNumber) {
        if (userGuess < randomNumber) {
            return "Your guess is too low. Try again!";
        } else {
            return "Your guess is too high. Try again!";
        }
    }
    private static int calculateScore(int attempts) {
        return Math.max(0, 10 - (attempts - 1)); 
    }
    private static boolean shouldPlayAgain(Scanner scanner) {
        while (true) {
            System.out.print("Would you like to play again? (yes/no): ");
            String response = scanner.next();
            if (response.equalsIgnoreCase("yes")) {
                return true;
            } else if (response.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }
}
