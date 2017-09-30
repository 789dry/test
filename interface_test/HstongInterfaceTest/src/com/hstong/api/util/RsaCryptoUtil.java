package com.hstong.api.util;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;
import org.apache.commons.codec.digest.DigestUtils;
/**
 * RSA 加密解密工具�? <p>
 * RSA算法对加密数据的长度有限�?: 密钥512 bit情况下不能超�?53 byte�? 1024情况下不能超�?117字节, 规则为：加密的明文长度不能超过RSA密钥的长度减�?11byte
 * RSA的秘药长度（512-65536），jdk默认1024 bit
 * <p/>
 * 加密后密文的长度为密钥的长度，如密钥长度�?1024bit(128Byte)，最后生成的密文固定�? 1024bit(128Byte), 密钥越长强度越大越安�? ，但性能越差
 * <p/>
 * 加密后密文长度等于私钥长度�??
 * <p/>
 * java JCE RSA 密钥格式默认是pkcs8, 如果是用其他工具产生密钥的话�?要注意格式问题， 如：
 * 使用openssl产生的密钥是pcks1, �?要转换成pkcs8
 * <p/>
 * Created by zhangli on 14-7-15.
 */
public class RsaCryptoUtil {
	private static final Logger log = LoggerFactory.getLogger(RsaCryptoUtil.class);
	/** 私钥  */
	public static String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAK6t7KMcfp5fkrCk"
			+ "YMwGoMmzIj0qK7Sa9GkNf4jdoPi+G0NOpDwfmvLv/MMhzgKN5I2XyEMlHfSdKOFG"
			+ "Q1mk4HdEBLfYiE5kRYGX4o8eMk+UmXU9IdVx7wOERZXerskNIlcEfsdTXt3Bp/Aj"
			+ "ecGORq3NdOrWKCizJEK5M7PqpEwPAgMBAAECgYAihjukDWi1MpySjxqWmKTE2MPm"
			+ "pOQYgRvXe5R+X5eGMkS/K9boDzMX2vjxNEg8VwEaPyqUosxl4X4H8FQ1bpHyjN9e"
			+ "/MMqeypbR8y+mbA0SCfMbnLD+Nyv7I7SSN0Q8JMeqFFwlBq2xQky1kl51cPCPhlI"
			+ "Pyq1QbTYyOoqrlNYMQJBAOCr17hiJ2aY+R59MSiyar9TERfzmU3cikT5GHO+zQCl"
			+ "69GITjcrYyHY1M1meWCXRbRCfh3glNJIW+CHXmijOYkCQQDHCYHKXwwgGwsR7qNh"
			+ "aeCs9I7AQ1CGUxXo5Yso5lf/NB5KJM0nmVgK6NJrCdorKdfOV1s4VHSLaw+Lb/a2"
			+ "CqrXAkAuquMzNIg0on/UmnKUnoLX3PxG+l2Us8Ow0gEHQdG5Wb2jF2oPwZ5k0HJb"
			+ "e/aoXqOm7szdjeG70DXtbJDqsUTpAkA5oi4cDHO1ZD9LXP+gOIlfa51eXN+XVb21"
			+ "Z6ppGHIIqu3OaGOjgMq2tf4gEoVDFGWyESeP4xywNjsVFEGrKuGXAkAnivbKxKyO"
			+ "YvcaGG2PPkMoNCU7kY7lF/OMEk5RVdXjHheiXhW2zQ858wZP5na3EN6LlexKiPCW"
			+ "sebLERppBUEg";
	
	/** 安卓公钥 */
	public static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCureyjHH6eX5KwpGDMBqDJsyI9"
			+ "Kiu0mvRpDX+I3aD4vhtDTqQ8H5ry7/zDIc4CjeSNl8hDJR30nSjhRkNZpOB3RAS3"
			+ "2IhOZEWBl+KPHjJPlJl1PSHVce8DhEWV3q7JDSJXBH7HU17dwafwI3nBjkatzXTq"
			+ "1igosyRCuTOz6qRMDwIDAQAB";

	/** IOS公钥 */
	public static String PUBLIC_KEY_IOS = "MIICuDCCAiGgAwIBAgIJAKUTZk1CVIjVMA0GCSqGSIb3DQEBCwUAMHUxCzAJBgNVBAYTAmNoMQswCQYDVQQIDAJnZDELMAkGA1UEBwwCc3oxDDAKBgNVBAoMA3p3YzENMAsGA1UECwwEc2luYTENMAsGA1UEAwwEc2luYTEgMB4GCSqGSIb3DQEJARYRampzaGFud2VpQDE2My5jb20wHhcNMTQwOTE4MDgzNTU5WhcNMTQxMDE4MDgzNTU5WjB1MQswCQYDVQQGEwJjaDELMAkGA1UECAwCZ2QxCzAJBgNVBAcMAnN6MQwwCgYDVQQKDAN6d2MxDTALBgNVBAsMBHNpbmExDTALBgNVBAMMBHNpbmExIDAeBgkqhkiG9w0BCQEWEWpqc2hhbndlaUAxNjMuY29tMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCureyjHH6eX5KwpGDMBqDJsyI9Kiu0mvRpDX+I3aD4vhtDTqQ8H5ry7/zDIc4CjeSNl8hDJR30nSjhRkNZpOB3RAS32IhOZEWBl+KPHjJPlJl1PSHVce8DhEWV3q7JDSJXBH7HU17dwafwI3nBjkatzXTq1igosyRCuTOz6qRMDwIDAQABo1AwTjAdBgNVHQ4EFgQUx+GvbNhm9wyrxizKxL+MbiS5P2UwHwYDVR0jBBgwFoAUx+GvbNhm9wyrxizKxL+MbiS5P2UwDAYDVR0TBAUwAwEB/zANBgkqhkiG9w0BAQsFAAOBgQBRTBnnc96JrPMX3H4DJrdzc1P6ZkXqLpCMpeGlWzTUbuDCnkvXyog10ufqy9GM35SBFB4SLXBe+ovNofTdK0TudDELTPzTeHpLdjrKHQLYr+7YWQdM5A2Q8VLJpsov1/uglRro71TblswYagLUHfdBu5/WugDRsYrHcMlWRc4rzA==";

	/** wp公钥 */
	public static String PUBLIC_KEY_WP = "<RSAKeyValue><Modulus>rq3soxx+nl+SsKRgzAagybMiPSortJr0aQ1/iN2g+L4bQ06kPB+a8u/8wyHOAo3kjZfIQyUd9J0o4UZDWaTgd0QEt9iITmRFgZfijx4yT5SZdT0h1XHvA4RFld6uyQ0iVwR+x1Ne3cGn8CN5wY5Grc106tYoKLMkQrkzs+qkTA8=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
	
	static Map<Integer, String> publicKeyMap = new HashMap<Integer, String>();
	static {
		publicKeyMap.put(1, PUBLIC_KEY);
		publicKeyMap.put(8, PUBLIC_KEY_IOS);
		publicKeyMap.put(9, PUBLIC_KEY_WP);
	}
	public static String publicKey(Integer clientType){
		return publicKeyMap.get(clientType);
	}
	
	/**
     * RSA私钥解密
     *
     * @param ciphertextByBase64 密文base64�?
     * @param privateKeyByBase64 密钥base64�?
     * @param charset            明文对应的字符编码， 用于构�?�明文字符串
     * @return 明文字符�?
     */
    public static String decryptByPrivateKey(String ciphertextByBase64, String privateKeyByBase64, Charset charset) {
        if (charset == null) {
            charset = Charset.forName("utf-8");
        }
        if (StringUtils.isBlank(ciphertextByBase64) || StringUtils.isBlank(privateKeyByBase64)) {
            throw new IllegalArgumentException("ciphertext or privateKey cann't empty!");
        }
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyByBase64));
        byte[] textBytes = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(spec);
            //RSAPrivateCrtKeyImpl
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            textBytes = decrypt(Base64.decodeBase64(ciphertextByBase64), ((RSAPrivateCrtKeyImpl) privateKey).getModulus().bitLength(), cipher);
        } catch (Throwable throwable) {
        	log.info("RSA 私钥解密异常：{}", throwable.getMessage() + "" +throwable);
            //throw new ExceptionHandler("RSA 私钥解密异常�?" + throwable.getMessage(), throwable);
        }
        return new String(textBytes, charset);
    }

    /**
     * RSA公钥解密
     *
     * @param ciphertextByBase64
     * @param publicKeyByBase64
     * @param charset
     * @return
     */
    public static String decryptByPublicKey(String ciphertextByBase64, String publicKeyByBase64, Charset charset) {
        if (charset == null) {
            charset = Charset.forName("utf-8");
        }
        if (StringUtils.isBlank(ciphertextByBase64) || StringUtils.isBlank(publicKeyByBase64)) {
            throw new IllegalArgumentException("ciphertext or publicKey cann't empty!");
        }
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyByBase64));
        byte[] textBytes = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(spec);
            //RSAPublicKeyImpl
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            textBytes = decrypt(Base64.decodeBase64(ciphertextByBase64), ((RSAPublicKeyImpl) publicKey).getModulus().bitLength(), cipher);
        } catch (Throwable throwable) {
        	log.info("RSA 公钥解密异常：{}", throwable.getMessage() + "" +throwable);
            //throw new CryptoException("RSA 公钥解密异常�?" + throwable.getMessage(), throwable);
        }
        return new String(textBytes, charset);
    }

    /**
     * RSA 私钥加密
     *
     * @param text
     * @param privateKeyByBase64
     * @param charset
     * @return
     */
    public static String encryptByPrivateKey(String text, String privateKeyByBase64, Charset charset) {
        if (charset == null) {
            charset = Charset.forName("utf-8");
        }
        if (StringUtils.isBlank(text) || StringUtils.isBlank(privateKeyByBase64)) {
            throw new IllegalArgumentException("text or privateKey cann't empty!");
        }
        PKCS8EncodedKeySpec pkcs8Spec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyByBase64));
        byte[] cipherBytes = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8Spec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] textBytes = text.getBytes(charset);
            cipherBytes = encrypt(textBytes, ((RSAPrivateCrtKeyImpl) privateKey).getModulus().bitLength(), cipher);
        } catch (Throwable throwable) {
        	log.info("RSA 私钥加密异常：{}", throwable.getMessage() + "" +throwable);
            //throw new ExceptionHandler("RSA 私钥加密异常�?" + throwable.getMessage(), throwable);
        }
        return Base64.encodeBase64String(cipherBytes);
    }

    /**
     * RSA公钥加密
     *
     * @param text              明文
     * @param publicKeyByBase64 公钥
     * @param charset
     * @return 密文的base64�?
     */
    public static String encryptByPublicKey(String text, String publicKeyByBase64, Charset charset) {
        if (charset == null) {
            charset = Charset.forName("utf-8");
        }
        if (StringUtils.isBlank(text) || StringUtils.isBlank(publicKeyByBase64)) {
            throw new IllegalArgumentException("text or publicKey cann't empty!");
        }
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyByBase64));
        byte[] cipherBytes = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509Spec);
            //Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] textBytes = text.getBytes(charset);
            cipherBytes = encrypt(textBytes, ((RSAPublicKeyImpl) publicKey).getModulus().bitLength(), cipher);
        } catch (Throwable throwable) {
        	log.info("RSA 公钥加密异常：{}", throwable.getMessage() + "" +throwable);
            //throw new ExceptionHandler("RSA 公钥加密异常�?" + throwable.getMessage(), throwable);
        }
        return Base64.encodeBase64String(cipherBytes);
    }

    private static void verifyKeySize(int keySize) {
        if (keySize < 512 || keySize > 65536 || keySize % 64 != 0) {
            throw new IllegalArgumentException("rsa keysize is between 512 and 65536, and must an integral multiple of 64");
        }
    }

    private static byte[] encrypt(byte[] inputBytes, int keySize, Cipher cipher) throws Exception {
        int maxTextSize = keySize / 8 - 11;
        //分片加密
        if (inputBytes.length > maxTextSize) {
            int index = 0;
            try (ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
                while (true) {
                    if (index + maxTextSize < inputBytes.length) {
                        bout.write(cipher.doFinal(inputBytes, index, maxTextSize));
                        index += maxTextSize;
                    } else {
                        bout.write(cipher.doFinal(inputBytes, index, inputBytes.length - index));
                        break;
                    }
                }
                return bout.toByteArray();
            }
        } else {
            return cipher.doFinal(inputBytes);
        }
    }

    private static byte[] decrypt(byte[] inputBytes, int keySize, Cipher cipher) throws Exception {
        int maxDecryptSize = keySize / 8;
        if (inputBytes.length > maxDecryptSize) {
            int index = 0;
            try (ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
                while (true) {
                    if (index + maxDecryptSize < inputBytes.length) {
                        bout.write(cipher.doFinal(inputBytes, index, maxDecryptSize));
                        index += maxDecryptSize;
                    } else {
                        bout.write(cipher.doFinal(inputBytes, index, inputBytes.length - index));
                        break;
                    }
                }
                return bout.toByteArray();
            }
        } else {
            return cipher.doFinal(inputBytes);
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
/*            byte[] priKey;
            byte[] pubKey;
            java.security.KeyPairGenerator keygen = java.security.KeyPairGenerator
              .getInstance("RSA");
            SecureRandom secrand = new SecureRandom();
            secrand.setSeed("gjs".getBytes()); // 初始化随机产生器
            keygen.initialize(1024, secrand);
            KeyPair keys = keygen.genKeyPair();
            PublicKey pubkey = keys.getPublic();
            PrivateKey prikey = keys.getPrivate();
            pubKey = Base64.encodeBase64(pubkey.getEncoded());
            priKey = Base64.encodeBase64(prikey.getEncoded());
            System.out.println("pubKey = " + new String(pubKey));
            System.out.println("priKey = " + new String(priKey));*/
           
            
            //System.out.println("public-key="+PUBLIC_KEY);
           //System.out.println( RsaCryptoUtil.decryptByPrivateKey("LEjsgqyiDaLt5x/EElJZ2Bcwskj+MAhTca9B4KY85Z7P8SBp+LUX64MwfN5/X21sB8sSHsNUuOnjiO3Wb/X2MJS35vtUNNiwMjWL+OW/VcWSsNgvljq/zPPS8/1Do49L7vNdlMPho/bO5pB3fthqIWAMySzXJvSIV5i7W58zy5w=".replaceAll(" ", "+"), RsaCryptoUtil.PRIVATE_KEY, Charset.forName("utf-8")));
           
           System.out.println( RsaCryptoUtil.encryptByPublicKey("333333", RsaCryptoUtil.PUBLIC_KEY, Charset.forName("utf-8")));
              
         
        } catch (java.lang.Exception e) {
            System.out.println("生成密钥对失�?");
            e.printStackTrace();
           }
    }
}
