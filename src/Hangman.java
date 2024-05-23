import java.io.*;
import java.util.*;

public class Hangman {
    private static final String[] HANGMAN_PICS = {
            "+---+\n    |\n    |\n    |\n   ===",
            "+---+\n O  |\n    |\n    |\n   ===",
            "+---+\n O  |\n |  |\n    |\n   ===",
            "+---+\n O  |\n/|  |\n    |\n   ===",
            "+---+\n O  |\n/|\\ |\n    |\n   ===",
            "+---+\n O  |\n/|\\ |\n/   |\n   ===",
            "+---+\n O  |\n/|\\ |\n/ \\ |\n   ==="
    };

    private static List<String> words = new ArrayList<>();
    private static int wins = 0;
    private static int losses = 0;
    private static int totalMistakes = 0;
    private static int gamesPlayed = 0;

    public static void main(String[] args) {
        loadWords();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Hangman Menu:");
            System.out.println("1. Play Game");
            System.out.println("2. Edit Word Database");
            System.out.println("3. View Statistics");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    playGame(scanner);
                    break;
                case 2:
                    editWordDatabase(scanner);
                    break;
                case 3:
                    viewStatistics();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void loadWords() {
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void saveWords() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("words.txt"))) {
            for (String word : words) {
                bw.write(word);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void playGame(Scanner scanner) {
        System.out.println("Choose difficulty (easy, medium, hard): ");
        String difficulty = scanner.nextLine().toLowerCase();
        int minLength = 0;
        int maxLength = Integer.MAX_VALUE; // Default max length

        switch (difficulty) {
            case "easy":
                minLength = 0;
                maxLength = 5;
                break;
            case "medium":
                minLength = 6;
                maxLength = 8;
                break;
            case "hard":
                minLength = 8;
                maxLength = Integer.MAX_VALUE; // No upper limit
                break;
            default:
                System.out.println("Invalid difficulty. Defaulting to easy.");
                minLength = 0;
                maxLength = 5;
        }

        List<String> filteredWords = new ArrayList<>();
        for (String word : words) {
            if (word.length() >= minLength && word.length() <= maxLength) {
                filteredWords.add(word);
            }
        }

        if (filteredWords.isEmpty()) {
            System.out.println("No words available for the selected difficulty.");
            return;
        }

        Random random = new Random();
        String word = filteredWords.get(random.nextInt(filteredWords.size()));
        char[] guessedWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLetter(c)) {
                guessedWord[i] = '_';
            } else {
                guessedWord[i] = c;
            }
        }

        int lives = 6;
        boolean wordGuessed = false;
        ArrayList<Character> guessedLetters = new ArrayList<>();
        int mistakes = 0;

        System.out.println("Welcome to Hangman!");

        while (lives > 0 && !wordGuessed) {
            System.out.println(HANGMAN_PICS[6 - lives]);
            System.out.println("Current word: " + String.valueOf(guessedWord));
            System.out.println("Lives left: " + lives);
            System.out.print("Guessed letters: ");
            for (char c : guessedLetters) {
                System.out.print(c + " ");
            }
            System.out.println();
            System.out.print("Guess a letter: ");
            char guess = scanner.nextLine().toLowerCase().charAt(0);

            if (guessedLetters.contains(guess)) {
                System.out.println("You've already guessed that letter.");
                continue;
            }

            guessedLetters.add(guess);

            boolean correctGuess = false;
            for (int i = 0; i < word.length(); i++) {
                if (Character.toLowerCase(word.charAt(i)) == guess) {
                    guessedWord[i] = word.charAt(i);
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                lives--;
                mistakes++;
                System.out.println("Incorrect guess.");
            } else {
                System.out.println("Correct guess!");
            }

            wordGuessed = true;
            for (int i = 0; i < guessedWord.length; i++) {
                if (guessedWord[i] == '_') {
                    wordGuessed = false;
                    break;
                }
            }
        }

        gamesPlayed++;

        if (wordGuessed) {
            System.out.println("Congratulations! You guessed the word: " + word);
            wins++;
            totalMistakes += mistakes;
        } else {
            System.out.println(HANGMAN_PICS[6]);
            System.out.println("Game over. The word was: " + word);
            losses++;
        }
    }

    private static void editWordDatabase(Scanner scanner) {
        boolean editing = true;

        while (editing) {
            System.out.println("Word Database Menu:");
            System.out.println("1. Add Word");
            System.out.println("2. Remove Word");
            System.out.println("3. View Words");
            System.out.println("4. Save and Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter a word to add: ");
                    String newWord = scanner.nextLine().toLowerCase();
                    words.add(newWord);
                    System.out.println("Word added.");
                    break;
                case 2:
                    System.out.print("Enter a word to remove: ");
                    String removeWord = scanner.nextLine().toLowerCase();
                    if (words.remove(removeWord)) {
                        System.out.println("Word removed.");
                    } else {
                        System.out.println("Word not found.");
                    }
                    break;
                case 3:
                    System.out.println("Current words in database:");
                    for (String word : words) {
                        System.out.println(word);
                    }
                    break;
                case 4:
                    saveWords();
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void viewStatistics() {
        System.out.println("Game Statistics:");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.printf("Win Percentage: %.2f%%\n", (wins * 100.0) / gamesPlayed);
        System.out.printf("Average Mistakes per Win: %.2f\n", wins == 0 ? 0 : (totalMistakes * 1.0) / wins);
    }
}
