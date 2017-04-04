package com.ryandev;

import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;

/**
 * Created by Ryan on 2017-03-22.
 */
public class NatoGame {

    private static final int MAX_POINTS = 3000;

    private Scoreboard scoreboard;

    public NatoGame() {
        this.scoreboard = new Scoreboard();
    }

    public void play(int gameMode) {
        Scanner input = new Scanner(System.in);
        boolean isTest = false;
        int rounds = 0;
        if (gameMode == 1) {
            // info mode
            runPractice();
            return;
        } else if (gameMode == 2) {
            // 5 round practice mode with random letters
            rounds = 5;
        } else if (gameMode == 3) {
            // 26 round test of all letters
            rounds = 26;
            isTest = true;
        } else {
            System.out.println("ERROR: Invalid game mode");
        }
        System.out.println("Guess the NATO Phonetic word for the letter shown.");
        NatoAlphabet natoAlphabet = new NatoAlphabet();
        int points = 0;
        for (int i = 0; i < rounds; i++) {
            natoAlphabet.getNewNatoWord(isTest);
            System.out.println("The letter is: " + natoAlphabet.getCurrentLetter());
            long timeStart = System.currentTimeMillis();
            String guess = input.nextLine();
            long timeStop = System.currentTimeMillis();
            int spellingCorrectness = levenshteinDistance(guess, natoAlphabet.getCurrentNatoWord());
            // A guess within 2.5 seconds is worth full points
            long pointsForRound = MAX_POINTS - (timeStop - timeStart) + 2500;
            if (pointsForRound > MAX_POINTS) {
                pointsForRound = MAX_POINTS;
            }
            // lose 1000 points per spelling error
            pointsForRound -= spellingCorrectness * 1000;
            if (pointsForRound < 0) {
                pointsForRound = 0;
            }
            points += pointsForRound;
            if (spellingCorrectness > 0) {
                System.out.println("The correct answer is " + natoAlphabet.getCurrentNatoWord());
            } else {
                System.out.println("Correct!");
            }
            System.out.println("You got " + pointsForRound + " points.\n");
        }

        System.out.println(String.format("Your score for the round was %d/%d", points, rounds * MAX_POINTS));
        scoreboard.addScore(points, rounds * MAX_POINTS);
    }

    private void runPractice() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a letter to see what the corresponding Nato Phonetic Word is. Type 'exit' to quit");
        NatoAlphabet natoAlphabet = new NatoAlphabet();
        boolean playAgain = true;
        while (playAgain) {
            String command = input.nextLine().toUpperCase();
            if (command.length() == 1) {
                String natoWord = natoAlphabet.getWordForLetter(command.charAt(0));
                if (natoWord != null) {
                    System.out.println(natoWord);
                } else {
                    System.out.println("Not a valid letter");
                }
            } else if (command.equalsIgnoreCase("exit")) {
                playAgain = false;
            } else {
                System.out.println("Not a valid letter");
            }

        }
    }

    public List<Score> endGame() {
        return scoreboard.getAllScores();
    }

    private int levenshteinDistance(String strA, String strB) {
        String strALowCase = strA.toLowerCase();
        String strBLowCase = strB.toLowerCase();
        int lenA = strALowCase.length();
        int lenB = strBLowCase.length();
        if (lenA == 0) {
            return lenB;
        }
        if (lenB == 0) {
            return lenA;
        }
        int[][] costMatrix = new int[lenA+1][lenB+1];
        for (int i = 0; i <= lenA; i++) {
            costMatrix[i][0] = i;
        }
        for (int i = 0; i <= lenB; i++) {
            costMatrix[0][i] = i;
        }
        for (int i = 1; i <= lenA; i++) {
            char charA = strALowCase.charAt(i-1);
            for (int j = 1; j <= lenB; j++) {
                char charB = strBLowCase.charAt(j-1);
                int cost = 0;
                if (charA != charB) {
                    cost = 1;
                }
                costMatrix[i][j] = min(min(costMatrix[i-1][j] + 1, costMatrix[i][j-1] + 1), costMatrix[i-1][j-1] + cost);
            }
        }
        return costMatrix[lenA][lenB];
    }
}
