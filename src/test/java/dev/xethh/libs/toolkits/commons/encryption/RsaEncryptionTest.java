package dev.xethh.libs.toolkits.commons.encryption;

import org.junit.Test;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import java.io.*;
import java.security.KeyPair;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RsaEncryptionTest {
    @Test
    public void test(){
        KeyPair kp = RsaEncryption.keyPair();
        byte[] kpu = kp.getPublic().getEncoded();
        byte[] kpr = kp.getPrivate().getEncoded();
        assertArrayEquals(kpu, RsaEncryption.getPublicKey(kpu).getEncoded());
        assertArrayEquals(kpr, RsaEncryption.getPrivateKey(kpr).getEncoded());
        String encrypted = RsaEncryption.encrypt("abc", RsaEncryption.getPublicKey(kpu));
//        assertEquals( encrypted, RsaEncryption.encrypt("abc",RsaEncryption.getPublicKey(kpu)));
        String decrypted = RsaEncryption.decrypt(encrypted, RsaEncryption.getPrivateKey(kpr));
        assertEquals(
                "abc",
                decrypted
        );
        String sign = RsaEncryption.sign("abc", RsaEncryption.getPrivateKey(kpr));
        assertEquals(
                sign,
                RsaEncryption.sign("abc", RsaEncryption.getPrivateKey(kpr))
        );

        assertEquals(
                true,
                RsaEncryption.verify("abc",sign, RsaEncryption.getPublicKey(kpu))
        );
    }

    @Test
    public void testStream2() throws IOException {
        KeyPair kp = RsaEncryption.keyPair();
        byte[] kpu = kp.getPublic().getEncoded();
        byte[] kpr = kp.getPrivate().getEncoded();
        String testString = "sjxywrwq@#$%$%^&XCVSDFHksadfgjklasdjf;ajfznmxcvlna;hdfa;ieur";
        String fileStr = "target/test1.txt";
        if(new File(fileStr).exists()) new File(fileStr).delete();
        CipherOutputStream is = new CipherOutputStream(new FileOutputStream(new File(fileStr)), RsaEncryption.encryptionCipher(RsaEncryption.getPublicKey(kpu)));
        is.write(testString.getBytes());
        is.flush();
        is.close();
        CipherInputStream decryptStream = new CipherInputStream(new FileInputStream(fileStr), RsaEncryption.decryptionCipher(RsaEncryption.getPrivateKey(kpr)));
        InputStreamReader reader = new InputStreamReader(decryptStream);
        String data = new BufferedReader(reader).readLine();
        reader.close();
        assertEquals(testString, data);
        if(new File(fileStr).exists()) new File(fileStr).delete();
    }

    @Test
    public void testStream1() throws IOException {
        KeyPair kp = RsaEncryption.keyPair();
        byte[] kpu = kp.getPublic().getEncoded();
        byte[] kpr = kp.getPrivate().getEncoded();
        String testString = "sjxywrwq@#$%$%^&XCVSDFHksadfgjklasdjf;ajfznmxcvlna;hdfa;ieur";
        CipherInputStream is = new CipherInputStream(new ByteArrayInputStream(testString.getBytes()), RsaEncryption.encryptionCipher(RsaEncryption.getPublicKey(kpu)));
        CipherInputStream decryptStream = new CipherInputStream(is, RsaEncryption.decryptionCipher(RsaEncryption.getPrivateKey(kpr)));
        InputStreamReader reader = new InputStreamReader(decryptStream);
        String data = new BufferedReader(reader).readLine();
        assertEquals(testString, data);
    }
}
