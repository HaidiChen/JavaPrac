package javaprac.security;

import java.io.*;
import java.security.*;
import javax.crypto.*;

import javaprac.Prac;


public class AESCryptPrac implements Prac {

    private static final String ORIGINAL_FILE_PATH = "src/main/resources/aes_crypt_prac_origin";
    private static final String ENCRYPTED_FILE_PATH = "src/main/resources/aes_crypt_prac_encrypted";
    private static final String DECRYPTED_FILE_PATH = "src/main/resources/aes_crypt_prac_decrypted";

    @Override
    public void runPrac() {
        Key key = null;
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            SecureRandom random = new SecureRandom();
            keygen.init(random);
            key = keygen.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (InputStream in = new FileInputStream(ORIGINAL_FILE_PATH);
                OutputStream out = new FileOutputStream(ENCRYPTED_FILE_PATH)) {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            Util.crypt(in, out, cipher);
            System.out.println("file encrypted.");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (InputStream in = new FileInputStream(ENCRYPTED_FILE_PATH);
                OutputStream out = new FileOutputStream(DECRYPTED_FILE_PATH)) {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            Util.crypt(in, out, cipher);
            System.out.println("file decrypted.");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Learn security - Cipher usage.";
    }
}

class Util {
    public static void crypt(InputStream in, OutputStream out, Cipher cipher)
            throws IOException, GeneralSecurityException {
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(blockSize);
        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];

        int inLength = 0;
        boolean more = true;
        while (more) {
            inLength = in.read(inBytes);
            if (inLength == blockSize) {
                int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
                out.write(outBytes, 0, outLength);
            } else {
                more = false;
            }
        }

        if (inLength > 0) {
            outBytes = cipher.doFinal(inBytes, 0, inLength);
        } else {
            outBytes = cipher.doFinal();
        }
        out.write(outBytes);
    }
}
