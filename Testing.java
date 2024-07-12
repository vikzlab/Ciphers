// Name: Vikram Murali 

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assume.assumeTrue;
import java.util.*;


public class Testing {

    private SubstitutionRandom substitutionRandom;

    @BeforeEach
    public void setUp() {
        int seed = 12345; 
        substitutionRandom = new SubstitutionRandom(4);
        substitutionRandom.setSeed(seed); 
    }

    @Test
    @DisplayName("Encryption with A-Z Shifter")
    public void encryptionTest() {
        String input = "HELLO";
        String encrypted = substitutionRandom.encrypt(input);
        assertNotEquals(input, encrypted);
        assertNotEquals("WORLD", encrypted);
    }

    @Test
    @DisplayName("Decryption with A-Z Shifter")
    public void decryptionTest() {
        String input = "HELLO";
        String encrypted = substitutionRandom.encrypt(input);
        String decrypted = substitutionRandom.decrypt(encrypted);
        assertEquals(input, decrypted);
        assertNotEquals("WORLD", decrypted);
    }

}