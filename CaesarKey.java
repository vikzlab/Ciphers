// Name: Vikram Murali 
 

import java.util.*;

// Implements a Caesar cipher with a custom key for encryption and decryption
// This cipher substitutes each character in the input string based on a key that maps 
// characters to their encrypted equivalents. The class supports both encryption and 
// decryption operations.
public class CaesarKey extends Substitution {

    // Constructor that initializes a new CaesarKey with a specific key
    // Parameters:
    //  key: A string representing the cipher key for character substitution
    // throws IllegalArgumentException if key is invalid 
    public CaesarKey(String key) throws IllegalArgumentException {
        super(createShifterFromKey(key));  
    }

    // Generates a shifter string from the provided key The shifter string is a 
    // combination of all valid characters, starting with the unique characters 
    // in the key followed by other characters in their default order.
    // Parameters:
    //  key: The key to create the shifter from
    // Returns shifter: A string representing the shifter.
    // throws IllegalArgumentException if the key is null, empty, contains duplicate 
    // characters, or invalid characters.
    private static String createShifterFromKey(String key) {
        if (key == null || key.isEmpty() || !hasUniqueCharacters(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        String shifter = "";
        Set<Character> used = new HashSet<>();
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!isValidCharacter(ch) || !used.add(ch)) {
                throw new IllegalArgumentException("Invalid character in key");
            }
            shifter += ch;
        }
        for (int ch = Cipher.MIN_CHAR; ch <= Cipher.MAX_CHAR; ch++) {
            char currentChar = (char) ch;  
            if (!used.contains(currentChar)) {
                shifter += currentChar;
            }
        }

        return shifter;
    }

    // Checks if a character is valid based on predefined character range in Cipher class
    // Parameters:
    //  ch: the character to validate
    // Returns:
    //  true if the character is within the range, false otherwise
    private static boolean isValidCharacter(char ch) {
        return ch >= Cipher.MIN_CHAR && ch <= Cipher.MAX_CHAR;
    }

    // Determines if a string has all unique characters.
    // Parameters:
    //  s: The string to check for uniqueness
    // Returns true if all characters in the string are unique, 
    // false if any character is duplicated
    private static boolean hasUniqueCharacters(String s) {
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!chars.add(c)) {
                return false; 
            }
        }
        return true;
    }
}
