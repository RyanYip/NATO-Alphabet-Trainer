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

    public void play() {
        Scanner input = new Scanner(System.in);
        NatoAlphabet natoWord = new NatoAlphabet();
        int points = 0;
        int rounds = 5;
        boolean isTest = false;
        System.out.println("Do you want to run the test? (All letters) (y/[N])");
        String testInput = input.nextLine();
        if (testInput.equalsIgnoreCase("y") || testInput.equalsIgnoreCase("yes")) {
            isTest = true;
            rounds = 26;
        }
        System.out.println("Guess the NATO Phonetic word for the letter shown.");
        for (int i = 0; i < rounds; i++) {
            natoWord.getNewNatoWord(isTest);
            System.out.println("The letter is: " + natoWord.getCurrentLetter());
            long timeStart = System.currentTimeMillis();
            String guess = input.nextLine();
            long timeStop = System.currentTimeMillis();
            int spellingCorrectness = levenshteinDistance(guess, natoWord.getCurrentNatoWord());
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
                System.out.println("The correct answer is " + natoWord.getCurrentNatoWord());
            } else {
                System.out.println("Correct!");
            }
            System.out.println("You got " + pointsForRound + " points.\n");
        }

        System.out.println(String.format("Your score for the round was %d/%d", points, rounds * MAX_POINTS));
        scoreboard.addScore(points, rounds * MAX_POINTS);
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
