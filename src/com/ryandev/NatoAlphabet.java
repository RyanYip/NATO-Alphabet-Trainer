package com.ryandev;

import java.util.*;

/**
 * Created by Ryan on 2017-03-21.
 */
public class NatoAlphabet {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Map<Character, String> NATO_ALPHABET = new HashMap<Character, String>() {{
        put('A', "Alfa");
        put('B', "Bravo");
        put('C', "Charlie");
        put('D', "Delta");
        put('E', "Echo");
        put('F', "Foxtrot");
        put('G', "Golf");
        put('H', "Hotel");
        put('I', "India");
        put('J', "Juliett");
        put('K', "Kilo");
        put('L', "Lima");
        put('M', "Mike");
        put('N', "November");
        put('O', "Oscar");
        put('P', "Papa");
        put('Q', "Quebec");
        put('R', "Romeo");
        put('S', "Sierra");
        put('T', "Tango");
        put('U', "Uniform");
        put('V', "Victor");
        put('W', "Whiskey");
        put('X', "Xray");
        put('Y', "Yankee");
        put('Z', "Zulu");
    }};
    private ArrayList<Character> alphabetTestArray = new ArrayList<>();
    private char currentLetter;
    private String currentNatoWord;

    public NatoAlphabet() {
        for (int i = 0; i < ALPHABET.length(); i++) {
            alphabetTestArray.add(ALPHABET.charAt(i));
        }
    }

    public void getNewNatoWord(boolean test){
        Random r = new Random();
        if (test == true) {
            int indextToRemove = r.nextInt(alphabetTestArray.size());
            setCurrentLetter(alphabetTestArray.get(indextToRemove));
            alphabetTestArray.remove(indextToRemove);
        } else {
            setCurrentLetter(ALPHABET.charAt(r.nextInt(ALPHABET.length())));
        }
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
}
