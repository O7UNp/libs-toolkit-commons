package dev.xethh.libs.toolkits.commons.encryption;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Sha3HashTest {
    @Test
    public void test(){
        assertEquals(Sha3Hash.hashBase64("abc".getBytes()),Sha3Hash.hashBase64("abc".getBytes()));
        assertEquals(Sha3Hash.hashBase64("xxx".getBytes()),Sha3Hash.hashBase64("xxx".getBytes()));

        assertEquals(Sha3Hash.hashBase64("xxx".getBytes(),"CCC".getBytes()),Sha3Hash.hashBase64("xxx".getBytes(),"CCC".getBytes()));

        assertEquals(
                Sha3Hash.hashBase64(
                "xxxjdakfdj!@##@%$#^%#$@^$#%^jsdfgkljdsflgjsld;kfgjgsjJSKALFDJALSHDF;LAKSJD;FLKAJLSKD;JFLK;ASJDFL;KAJSLD;FJASL;HFXNCZ,VHNASDKLDHFQKLHJEFJASDXLFJCASLDFKJASKJDFLKASJDFLKASJKDF;LJASL;DHFJLASDFJZXCJA;LUREOPIU5RKLjfalsjkdflkasjdfkjsdlkhfa;hsdrujoqiweurqwiefjzxcvjzl;jxdgfahfasdlkjfaksldjf;ahsdkfljaskdfjas;ldhfaslxjc.,zxnvashdfjaf;/wesl1yh43kjhopfasuyd9u8f712345128734y1928347".getBytes())
                ,
                Sha3Hash.hashBase64(
                        "xxxjdakfdj!@##@%$#^%#$@^$#%^jsdfgkljdsflgjsld;kfgjgsjJSKALFDJALSHDF;LAKSJD;FLKAJLSKD;JFLK;ASJDFL;KAJSLD;FJASL;HFXNCZ,VHNASDKLDHFQKLHJEFJASDXLFJCASLDFKJASKJDFLKASJDFLKASJKDF;LJASL;DHFJLASDFJZXCJA;LUREOPIU5RKLjfalsjkdflkasjdfkjsdlkhfa;hsdrujoqiweurqwiefjzxcvjzl;jxdgfahfasdlkjfaksldjf;ahsdkfljaskdfjas;ldhfaslxjc.,zxnvashdfjaf;/wesl1yh43kjhopfasuyd9u8f712345128734y1928347".getBytes())
        );
    }

}
