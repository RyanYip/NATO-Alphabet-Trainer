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
        int rounds = 5;
        int points = 0;
        System.out.println("Guess the NATO Phonetic word for the letter shown.");
        NatoAlphabet natoWord = new NatoAlphabet();
        for (int i = 0; i < rounds; i++) {
            natoWord.getNewNatoWord();
            System.out.println("The letter is: " + natoWord.getCurrentLetter());
            long timeStart = System.currentTimeMillis();
            String guess = input.nextLine();
            long timeStop = System.currentTimeMillis();
            boolean result = natoWord.checkGuess(guess);
            if (result) {
                // TODO: figure out how to make more accurate time based scoring
                // points added for buffer time before and after input.nextLine()
                // Unsure of how to measure time accurately for an input
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

        System.out.println("Your score for the round was " + points);
        scoreboard.addScore(points, rounds * MAX_POINTS);
    }

    public List<Score> endGame() {
        return scoreboard.getAllScores();
    }
}
