package net.collabstack.common;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    @Test
    public void enc() {
        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("thxdkadkwadawdk");
        jasypt.setAlgorithm("PBEWithMD5AndTripleDES");

        String host = jasypt.encrypt("jdbc:mysql://picker.cfsfqqh1qepr.ap-northeast-2.rds.amazonaws.com:3306/collabstack?autoReconnect=true");
        String user = jasypt.encrypt("admin");
        String pw = jasypt.encrypt("dy050700");
        System.out.println(pw);
        System.out.println(host);
        System.out.println(user);
    }

}
