package com.ryandev;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameState {

    public static void main(String[] args) {
        NatoGame game = new NatoGame();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the NATO Phonetic Alphabet Trainer.\n");
        boolean playAgain = true;
        while (playAgain) {
            boolean validGameMode = false;
            int numberOfGameModes = 3;
            String gameMode = "1";
            List<String> gameModeArray = new ArrayList<>();
            for (int i = 1; i <= numberOfGameModes; i++) {
                gameModeArray.add(String.valueOf(i));
            }
            while (!validGameMode) {
                System.out.println("Choose game mode (1, 2, 3):");
                System.out.println("1. Input a letter and the corresponding answer will be shown");
                System.out.println("2. Practice 5 rounds with random letters");
                System.out.println("3. Play all of the letters and try to score the highest");
                gameMode = input.nextLine();
                if (gameModeArray.contains(gameMode)) {
                    validGameMode = true;
                } else {
                    System.out.println("Please input a valid game mode\n");
                }
            }
            game.play(Integer.parseInt(gameMode));
            System.out.println("Would you like to restart the game? ([Y]/n)");
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
