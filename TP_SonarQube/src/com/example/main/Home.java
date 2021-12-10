package com.example.main;

import com.example.model.City;
import com.example.service.DatabaseConnection;
import com.example.service.DatabaseService;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Home {

    private static final Logger LOGGER = Logger.getLogger( Home.class.getName() );

    public static final String USER = "root";
    public static final String ENCRYPTION = "oRdQEUueATu/8rqxIHPYzw==:Wt9CTF0p2Ylf+RlTTsntIA==";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/citiesdb";

    public static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }

    public static String encrypt(String dataToEncrypt, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException {
        Cipher pbeCipher = Cipher.getInstance("AES/GCM/NoPadding");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters parameters = pbeCipher.getParameters();
        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
        byte[] cryptoText = pbeCipher.doFinal(dataToEncrypt.getBytes("UTF-8"));
        byte[] iv = ivParameterSpec.getIV();
        return base64Encode(iv) + ":" + base64Encode(cryptoText);
    }
     private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
     }


    public static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        Cipher pbeCipher = Cipher.getInstance("AES/GCM/NoPadding");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }

    private static byte[] base64Decode(String property) {
        return Base64.getDecoder().decode(property);
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException, SQLException {

        byte[] salt = "12345678".getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = createSecretKey("key2186".toCharArray(), salt, iterationCount, keyLength);
        String decryptedPassword = decrypt(ENCRYPTION, key);
        DatabaseConnection db= new DatabaseConnection(USER, decryptedPassword, JDBC_DRIVER, DB_URL);
        Connection conn = db.connect();
        try {

            if (args.length > 0) {
                int id = Integer.parseInt(args[0]);
                String name = args[1];
                int numTourist = Integer.parseInt(args[2]);
                String desc = args[3];
                City city1 = new City(id, name, numTourist, desc);
                DatabaseService.addCity(conn, city1);
            }
        }
        catch (NumberFormatException e) {

            LOGGER.log(Level.FINE, "Arguments" + args[0] + args[2] + " must be an integer.");

        }
        finally {
            if (!conn.isClosed()) conn.close();
        }
    }
}
