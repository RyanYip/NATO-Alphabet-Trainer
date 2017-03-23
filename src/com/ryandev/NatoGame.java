package com.ryandev;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Ryan on 2017-03-22.
 */
public class NatoGame {

    private Scoreboard scoreboard;

    public NatoGame() {
        this.scoreboard = new Scoreboard();
    }

    public void play(Scanner input) {
        int points = 0;
        System.out.println("Welcome to the NATO Phonetic Alphabet Trainer.");
        System.out.println("How many rounds do you want to play?");
        int rounds = 0;
        while (rounds <= 0) {
            try {
                rounds = Integer.parseInt(input.nextLine());
                if (rounds <= 0) {
                    System.out.println("Please input a number greater than 0.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a number greater than 0.");
            }
        }

        System.out.println("Guess the NATO Phonetic word for the letter shown.");
        NatoAlphabet natoWord = new NatoAlphabet();
        for (int i = 0; i < rounds; i++) {
            natoWord.getNewNatoWord();
            System.out.println("The letter is: " + natoWord.getCurrentLetter());
            String guess = input.nextLine();
            boolean result = natoWord.checkGuess(guess);
            if (result) {
                points++;
                System.out.println("Your guess was CORRECT!");
            } else {
                System.out.println("Your guess was INCORRECT!");
                System.out.println("The correct answer was " + natoWord.getCurrentNatoWord());
            }
        }

        System.out.println("Your score for the round was " + points);
        scoreboard.addScore(points, rounds);
    }

    public List<Score> endGame() {
        return scoreboard.getAllScores();
    }
}
