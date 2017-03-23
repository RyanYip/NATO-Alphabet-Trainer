package com.ryandev;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Ryan on 2017-03-21.
 */
public class NatoAlphabet {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Map<Character, String> NATO_ALPHABET = new HashMap<>();
    private char currentLetter;
    private String currentNatoWord;

    public NatoAlphabet() {
        NATO_ALPHABET.put('A', "Alfa");
        NATO_ALPHABET.put('B', "Bravo");
        NATO_ALPHABET.put('C', "Charlie");
        NATO_ALPHABET.put('D', "Delta");
        NATO_ALPHABET.put('E', "Echo");
        NATO_ALPHABET.put('F', "Foxtrot");
        NATO_ALPHABET.put('G', "Golf");
        NATO_ALPHABET.put('H', "Hotel");
        NATO_ALPHABET.put('I', "India");
        NATO_ALPHABET.put('J', "Juliett");
        NATO_ALPHABET.put('K', "Kilo");
        NATO_ALPHABET.put('L', "Lima");
        NATO_ALPHABET.put('M', "Mike");
        NATO_ALPHABET.put('N', "November");
        NATO_ALPHABET.put('O', "Oscar");
        NATO_ALPHABET.put('P', "Papa");
        NATO_ALPHABET.put('Q', "Quebec");
        NATO_ALPHABET.put('R', "Romeo");
        NATO_ALPHABET.put('S', "Sierra");
        NATO_ALPHABET.put('T', "Tango");
        NATO_ALPHABET.put('U', "Uniform");
        NATO_ALPHABET.put('V', "Victor");
        NATO_ALPHABET.put('W', "Whiskey");
        NATO_ALPHABET.put('X', "Xray");
        NATO_ALPHABET.put('Y', "Yankee");
        NATO_ALPHABET.put('Z', "Zulu");
    }

    public void getNewNatoWord(){
        Random r = new Random();
        setCurrentLetter(ALPHABET.charAt(r.nextInt(ALPHABET.length())));
        setCurrentNatoWord(NATO_ALPHABET.get(currentLetter));
    }

    public String getCurrentLetter(){
        return Character.toString(currentLetter);
    }

    public String getCurrentNatoWord(){
        return currentNatoWord;
    }

    private void setCurrentLetter(char letter) {
        currentLetter = letter;
    }

    private void setCurrentNatoWord(String natoWord) {
        currentNatoWord = natoWord;
    }

    public boolean checkGuess(String guess) {
        return currentNatoWord.equalsIgnoreCase(guess);
    }
}
