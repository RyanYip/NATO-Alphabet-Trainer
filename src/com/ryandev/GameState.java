package com.ryandev;

import java.util.List;
import java.util.Scanner;

public class GameState {

    public static void main(String[] args) {
        NatoGame game = new NatoGame();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the NATO Phonetic Alphabet Trainer.\n");
        boolean playAgain = true;
        while (playAgain) {
            game.play();
            System.out.println("Do you want to play again? ([Y]/n)");
            String playInput = input.nextLine();
            if (playInput.equalsIgnoreCase("n") || playInput.equalsIgnoreCase("no")) {
                playAgain = false;
            }
        }
        List<Score> allScores = game.endGame();
        for (int i = 0; i < allScores.size(); i++) {
            System.out.println(String.format("Round %d Score %d/%d", i+1, allScores.get(i).getPoints(), allScores.get(i).getMaxPoints()));
        }
        input.close();
    }

}
