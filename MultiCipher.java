// Name: Vikram Murali 

import java.util.List;

// The MultiCipher class extends Cipher and allows the combination of multiple cipher
// instances to perform encryption and decryption. This class enables layered
// encryption by applying each cipher in the list for encryption, and the
// reverse order for decryption
public class MultiCipher extends Cipher {
    
    private List<Cipher> ciphers;

    // Constructs a MultiCipher objects with a list of Cipher instances.
    // Parameters:
    //  ciphers: the list of cipher objects to use for encryption and decryption
    // Throws IllegalArgumentException if the ciphers list is null or empty, ensuring 
    // that there is at least one Cipher left to apply
    public MultiCipher(List<Cipher> ciphers) {
        if (ciphers == null || ciphers.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.ciphers = ciphers;
    }

    // Encrypts a string by applying each Cipher in the list in order.
    // Encryption is done sequentially, starting with the first Cipher in the list and
    // passing the output of each Cipher as input to the next.
    // Parameters:
    //  input: the string to be encrypted
    // Return:
    //  the encrypted string after all ciphers have been applied
    public String encrypt(String input) {
        String result = input;
        for (Cipher cipher : ciphers) {
            result = cipher.encrypt(result);
        }
        return result;
    }

    // Decrypts a string by applying each Cipher in the list in reverse order
    // Decryption reverses the encryption process, starting with the last Cipher in the
    // list and passing the output of each Cipher as input to the previous one
    // Parameters:
    //  input: The string to be decrypted
    // Returns:
    //  The decrypted string after all ciphers have been applied in reverse order.
    public String decrypt(String input) {
        String result = input;
        for (int i = ciphers.size() - 1; i >= 0; i--) {
            result = ciphers.get(i).decrypt(result);
        }
        return result;
    }
}
