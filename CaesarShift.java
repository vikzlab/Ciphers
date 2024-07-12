// Name: Vikram Murali 

import java.util.*;

// Implements a Caesar Cipher for encryption and decryption by shifting characters 
// by a specified number. This class extends Substitution, utilizing a generated shifter
// string that represents the shifted alphabet based on the provided shift parameter. 
public class CaesarShift extends Substitution {

    // Constructor for CaesarShift that initializes the cipher with a specific shift value.
    // Parameters:
    //  shift: The number of characters to shift in the alphabet for encryption and 
    //  decryption. Must be a positive integer.
    // throws IllegalArgumentException if the shift is less than 1, as shifting by zero 
    // or negative values is not supported.
    public CaesarShift(int shift) {
        super(generateShifter(shift));
    }

    // Generates a shifter string used for character substitution based on the shift value.
    // This method constructs the shifted alphabet by moving each character by the specified shift
    // amount and wraps around the alphabet if the shift extends beyond the alphabet's end.
    // Parameters:
    //  shift The number of positions each character in the alphabet is shifted.
    //  Returns shifter: A string representing the shifted alphabet used for substitution.
    // throws IllegalArgumentException if the shift parameter is less than 1.
    private static String generateShifter(int shift) {
        if (shift < 1)
            throw new IllegalArgumentException("Shift must be a positive integer");
        String shifter = "";
        for (int i = 0; i < TOTAL_CHARS; i++) {
            int shiftedIndex = (i + shift) % TOTAL_CHARS;
            shifter += (char) (MIN_CHAR + shiftedIndex);
        }
        return shifter;
    }
}
