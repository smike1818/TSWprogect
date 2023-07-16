import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateEncryptedPassword {
    
    public static void main(String[] args) {
        String password = "Satellite18.";
        try {
            String encryptedPassword = encryptSHA256(password);
            System.out.println("Encrypted password: " + encryptedPassword);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error encrypting password: " + e.getMessage());
        }
    }

    private static String encryptSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashedBytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}
