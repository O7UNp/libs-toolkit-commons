package dev.xethh.libs.toolkits.commons.encryption;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AesEncryptionTest {
    @Test
    public void test(){
        byte[] key = AesEncryption.secretKey().getEncoded();
        assertArrayEquals(key, AesEncryption.secretKey(key).getEncoded());
        byte[] iv = AesEncryption.iv().getIV();
        assertArrayEquals(iv, AesEncryption.iv(iv).getIV());
        assertEquals(
                AesEncryption.encrypt("abc",AesEncryption.secretKey(key),AesEncryption.iv(iv)),
                AesEncryption.encrypt("abc",AesEncryption.secretKey(key),AesEncryption.iv(iv))
                );
        assertEquals(
                "abc",
                AesEncryption.decrypt(AesEncryption.encrypt("abc",AesEncryption.secretKey(key),AesEncryption.iv(iv)),AesEncryption.secretKey(key),AesEncryption.iv(iv))
        );
        assertEquals(
                true,
                AesEncryption.verify(AesEncryption.secretKey(key),AesEncryption.iv(iv),"abc",AesEncryption.sign(AesEncryption.secretKey(key),AesEncryption.iv(iv),"abc"))
        );

    }

    @Test
    public void testOutputStream(){
        byte[] key = AesEncryption.secretKey().getEncoded();
        byte[] iv = AesEncryption.iv().getIV();

        byte[] bs = "Abc".getBytes();
        InputStream is = new InputStream() {
            int index = 0;
            @Override
            public int read() throws IOException {
                if(index>2)
                    return -1;
                return bs[index++];
            }
        };

        CipherInputStream iis = new CipherInputStream(is, AesEncryption.encryptionCipher(AesEncryption.secretKey(key), AesEncryption.iv(iv)));
        try {
            byte[] b = new byte[16];
            int i = iis.read(b);
            InputStream xis = new InputStream() {
                int index = 0;
                @Override
                public int read() throws IOException {
                    if(index>=i)
                        return -1;
                    return b[index++];
                }
            };
            CipherInputStream xiis = new CipherInputStream(xis, AesEncryption.decryptionCipher(AesEncryption.secretKey(key), AesEncryption.iv(iv)));
            byte[] bb = new byte[3];
            int bbi = xiis.read(bb);
            assertArrayEquals(bs, bb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testInputStream(){
        byte[] key = AesEncryption.secretKey().getEncoded();
        byte[] iv = AesEncryption.iv().getIV();

        final byte[] data = new byte[4096];
        Arrays.fill(data, (byte) -1);
        OutputStream os = new OutputStream() {
            int index = 0;
            @Override
            public void write(int b) throws IOException {
                data[index++] = (byte) b;
            }
        };
        InputStream is = new InputStream() {
            int index = 0;
            @Override
            public int read() throws IOException {
                return data[index++];
            }
        };

        Cipher cipher = AesEncryption.encryptionCipher(AesEncryption.secretKey(key), AesEncryption.iv(iv));
        CipherOutputStream oos = new CipherOutputStream(os, cipher);
        CipherInputStream iis = new CipherInputStream(is, AesEncryption.decryptionCipher(AesEncryption.secretKey(key), AesEncryption.iv(iv)));
        byte[] bs = "Abc".getBytes();
        try {
            oos.write(bs);
            oos.flush();
            oos.close();
            byte[] b = new byte[3];
            int i = iis.read(b);

            assertArrayEquals(bs, b);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testStream2() throws IOException {
        byte[] key = AesEncryption.secretKey().getEncoded();
        byte[] iv = AesEncryption.iv().getIV();
        String testString = "sjxywrwq@#$%$%^&XCVSDFHksadfgjklasdjf;ajfznmxcvlna;hdfa;ieur";

        String fileStr = "target/test.txt";
        if(new File(fileStr).exists()) new File(fileStr).delete();
        CipherOutputStream is = new CipherOutputStream(new FileOutputStream(new File(fileStr)), AesEncryption.encryptionCipher(AesEncryption.secretKey(key), AesEncryption.iv(iv)));
        is.write(testString.getBytes());
        is.flush();
        is.close();
        CipherInputStream decryptStream = new CipherInputStream(new FileInputStream(fileStr), AesEncryption.decryptionCipher(AesEncryption.secretKey(key),AesEncryption.iv(iv)));
        InputStreamReader reader = new InputStreamReader(decryptStream);
        String data = new BufferedReader(reader).readLine();
        assertEquals(testString, data);
        if(new File(fileStr).exists()){
            reader.close();
            new File(fileStr).delete();
        }
    }

    @Test
    public void testStream1() throws IOException {
        byte[] key = AesEncryption.secretKey().getEncoded();
        byte[] iv = AesEncryption.iv().getIV();
        String testString = "sjxywrwq@#$%$%^&XCVSDFHksadfgjklasdjf;ajfznmxcvlna;hdfa;ieur";
        CipherInputStream is = new CipherInputStream(new ByteArrayInputStream(testString.getBytes()), AesEncryption.encryptionCipher(AesEncryption.secretKey(key),AesEncryption.iv(iv)));
        CipherInputStream decryptStream = new CipherInputStream(is, AesEncryption.decryptionCipher(AesEncryption.secretKey(key),AesEncryption.iv(iv)));
        InputStreamReader reader = new InputStreamReader(decryptStream);
        String data = new BufferedReader(reader).readLine();
        assertEquals(testString, data);
    }

}
