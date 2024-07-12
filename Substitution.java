// Name: Vikram Murali 

import java.util.*;
import java.io.*;

// The Substitution class extends Cipher and implements a Substitution cipher.
// It maps characters of the alphabet to different characters for encryption and
// decryption.
public class Substitution extends Cipher{
    private Map<Character, Character> encryptMap;
    private Map<Character, Character> decryptMap;

    // Default Constructor
    // Initializes the cipher without a specific substitution pattern     
    public Substitution(){
        this.encryptMap = new HashMap<>();
        this.decryptMap = new HashMap<>();
    }

    // Constructor with shifter string
    // Parameters:
    //  shifter: the string used to create the encryption and decryption maps
    // throws IllegalArgumentException if the shifter string is invalid, such as containing
    // duplicate characters, not covering all necessary characters, or being empty.
    public Substitution(String shifter) throws IllegalArgumentException{
        this.encryptMap = new HashMap<>();
        this.decryptMap = new HashMap<>();
        setShifter(shifter);
    }

    // Sets the shifter string for the substitution cipher
    // Parameters:
    //  shifter: string used to set the maps.
    // throws IllegalArgumentException if the shifter string's length does not match
    // TOTAL_CHARS or contains duplicate characters
    public void setShifter(String shifter) {
        if (shifter.length() != Cipher.TOTAL_CHARS || !hasUniqueCharacters(shifter)) {
            throw new IllegalArgumentException();
        }
        encryptMap.clear();
        decryptMap.clear();
        Set<Character> seenCharacters = new HashSet<>();
        for (int i = 0; i < shifter.length(); i++) {
            char plainChar = (char) (Cipher.MIN_CHAR + i);
            char cipherChar = shifter.charAt(i);
            if (cipherChar < Cipher.MIN_CHAR || cipherChar > Cipher.MAX_CHAR || !seenCharacters.add(cipherChar)) {
                throw new IllegalArgumentException();
            }
            encryptMap.put(plainChar, cipherChar);
            decryptMap.put(cipherChar, plainChar);
        }
    }

    // Checks if a string has all unique characters
    // Parameters:
    //  shifter: string to check
    // Returns:
    //  true if all characters are unique, false otherwise
    private boolean hasUniqueCharacters(String shifter) {
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < shifter.length(); i++) {
            char c = shifter.charAt(i);
            if (!seen.add(c)) {
                return false;
            }
        }
        return true;
    }

    // Encrypts a string based on the current cipher settings
    // Parameters:
    //  input: string to encrypt
    // Returns:
    //  the encrypted string
    // throws IllegalArgumentException if the ecryption map has not been 
    // initialized
    public String encrypt(String input) {
        if (encryptMap.isEmpty() || decryptMap.isEmpty()) {
            throw new IllegalStateException();
        }
        return transform(input, true);
    }

    // Decrypts a string based on the current cipher settings
    // Parameters:
    //  input: string to decrypt
    // Returns:
    //  the decrypted string
    // throws IllegalArgumentException if the decryption map has not been 
    // initialized
    public String decrypt(String input) {
        if (encryptMap.isEmpty() || decryptMap.isEmpty()) {
            throw new IllegalStateException();
        }
        return transform(input, false);
    }

    // Transforms a string based on the specified map (encryption or decryption).
    // Parameters:
    //  input: string to transform
    //  encrypt: encryption map = true and decryption map = false
    // Return:
    //  the transformed string
    private String transform(String input, boolean encrypt) {
        String result = "";
        Map<Character, Character> currentMap;
        if (encrypt) {
            currentMap = encryptMap;
        } else {
            currentMap = decryptMap;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (currentMap.containsKey(c)) {
                result += currentMap.get(c);
            } else {
                result += c;
            }
        }
        return result;
    }
}



