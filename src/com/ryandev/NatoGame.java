package com.ryandev;

import java.util.List;
import java.util.Scanner;

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
        boolean playTest = false;
        System.out.println("Do you want to run the test? (All letters) (y/[N])");
        String playInput = input.nextLine();
        if (playInput.equalsIgnoreCase("y") || playInput.equalsIgnoreCase("yes")) {
            playTest = true;
            rounds = 26;
        }
        System.out.println("Guess the NATO Phonetic word for the letter shown.");
        for (int i = 0; i < rounds; i++) {
            natoWord.getNewNatoWord(playTest);
            System.out.println("The letter is: " + natoWord.getCurrentLetter());
            long timeStart = System.currentTimeMillis();
            String guess = input.nextLine();
            long timeStop = System.currentTimeMillis();
            boolean result = natoWord.checkGuess(guess);
            if (result) {
                // A guess within 2.5 seconds is worth full points
                long pointsForRound = MAX_POINTS - (timeStop - timeStart) + 2500;
                if (pointsForRound > 0) {
                    if (pointsForRound > MAX_POINTS) {
                        pointsForRound = MAX_POINTS;
                    }
                    points += pointsForRound;
                } else {
                    pointsForRound = 0;
                }
                System.out.println("Your guess was CORRECT! You got " + pointsForRound + " points.\n");
            } else {
                System.out.println("Your guess was INCORRECT!");
                System.out.println("The correct answer was " + natoWord.getCurrentNatoWord() + "\n");
            }
        }

        System.out.println(String.format("Your score for the round was %d/%d", points, rounds * MAX_POINTS));
        scoreboard.addScore(points, rounds * MAX_POINTS);
    }

    public List<Score> endGame() {
        return scoreboard.getAllScores();
    }
}
