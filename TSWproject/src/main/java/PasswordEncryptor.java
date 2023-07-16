import java.security.SecureRandom;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class PasswordEncryptor {
    private static final String ALGORITHM = "DES";
    private static final byte[] KEY = generateRandomKey(); // Chiave segreta per la cifratura

    public static String encrypt(String password) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(KEY);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.encodeBase64String(encryptedBytes);
    }
    
    private static byte[] generateRandomKey() {
        byte[] key = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(key);
        return key;
    }
}