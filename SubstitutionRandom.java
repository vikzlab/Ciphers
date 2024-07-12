// Name: Vikram Murali 

import java.util.*;

// Implements a substitution cipher with a random shifter based on a seed.
// This cipher can encrypt and decrypt messages using randomly shuffled alphabets.
public class SubstitutionRandom extends Cipher { 

    private static final int MAX_DIGITS = (int) (Math.floor(Math.log10(Integer.MAX_VALUE)));
    private List<Character> shifter;
    private int seed;

    // Constructor to initialize the cipher with a specific number of digits for the seed.
    // The seed is randomly generated within the range determined by the number of digits.
    // Throws IllegalArgumentException if the digits are outside the allowable range.
    // Parameters:
    //  digits: The number of digits for the seed. must be a valid count within the predefined 
    //  range of 1 and infinity
    public SubstitutionRandom(int digits) {
        if (digits <= 0 || digits > MAX_DIGITS) {
            throw new IllegalArgumentException("Digit must be between 1 and " + MAX_DIGITS);
        }
        this.seed = generateSeed(digits);
        this.shifter = generateShifter(seed);
    }

    // Sets a new seed and updates the shifter accordingly.
    // Parameters:
    //  seed: The new seed to use for generating the shifter.
     public void setSeed(int seed) {
        this.seed = seed;
        this.shifter = generateShifter(this.seed);
    }

    // Generates a random seed based on the specified number of digits.
    // Ensures the seed is within the specified range.
    // Parameters:
    //  digits: The number of digits to determine the range of possible seed values.
    // returns a randomly generated seed within the specified range
    private int generateSeed(int digits) {
        Random random = new Random();
        int seedUpperLimit = (int) Math.pow(10, digits);
        return random.nextInt(seedUpperLimit - 1) + 1; 
    }

    // Generates a shifter list of characters based on the provided seed.
    // Parameters:
    //  seed: The seed used to generate a random sequence of the alphabet for the shifter.
    // returns a list of characters representing the shifter used for encryption and decryption
    private List<Character> generateShifter(int seed) {
        List<Character> shifter = new ArrayList<>(26); 
        for (char c = 'a'; c <= 'z'; c++) {
            shifter.add(c);
        }
        Collections.shuffle(shifter, new Random(seed));
        return shifter;
    }

    // Encrypts a message using the substitution cipher with the current shifter.
    // Parameters:
    //  message: the message to be encrypted 
    // returns the encrypted message as a string
    public String encrypt(String message) {
        char[] encryptedChars = new char[message.length()];
        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            if (character >= 'a' && character <= 'z') {
                int originalPosition = character - 'a';
                encryptedChars[i] = shifter.get(originalPosition);
            } else if (character >= 'A' && character <= 'Z') {
                int originalPosition = character - 'A';
                encryptedChars[i] = Character.toUpperCase(shifter.get(originalPosition));
            } else {
                encryptedChars[i] = character;
            }
        }
        return new String(encryptedChars);
    }

    // Decrypts a message encrypted with the substitution cipher using the current shifter.
    // Parameters:
    //  message: the message to be decrypted 
    // returns the decrypted message as a string
    public String decrypt(String message) {
        char[] decryptedChars = new char[message.length()];
        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            if (character >= 'a' && character <= 'z') {
                int shifterPosition = shifter.indexOf(character);
                decryptedChars[i] = (char) ('a' + shifterPosition);
            } else if (character >= 'A' && character <= 'Z') {
                int shifterPosition = shifter.indexOf(Character.toLowerCase(character));
                decryptedChars[i] = (char) ('A' + shifterPosition);
            } else {
                decryptedChars[i] = character;
            }
        }
        return new String(decryptedChars);
    }
}