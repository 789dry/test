package com.hstong.AutomationTest.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3DES 加解�?
 *
 */
public class ThreeDesUtil {
	
	/**
	 * 3des对称加密算法
	 */
	private static final String Algorithm = "DESede";  
	
	private static final String UTF8 = "UTF-8";  
	
	/**
	 * 3DES+Base64 加密
	 * 
	 * @param key 秘钥�?
	 * @param plainStr
	 * @return
	 */
	public static String encode(String key, String plainStr){
		 try {
			 byte[] enk = hex(key); //秘钥
			 byte[] encoded = encryptMode(enk, plainStr.getBytes(UTF8));  
			 return Base64.encodeBase64String(encoded);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return null;
	}
	
	/**
	 * 3DES+Base64 解密
	 * 
	 * @param key 秘钥�?
	 * @param destStr
	 * @return
	 */
	public static String decode(String key, String destStr){
		 try {
			 byte[] enk = hex(key); //秘钥
			 byte[] destBytes = Base64.decodeBase64(destStr);  
		     byte[] srcBytes = decryptMode(enk, destBytes); 
		     if(srcBytes != null){
				 return new String(srcBytes, UTF8);
		     }
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return null;
	}
	
    //keybyte为加密密钥，长度�?24字节      
    //src为被加密的数据缓冲区（源�?  
    private static byte[] encryptMode(byte[] keybyte,byte[] src){  
         try {  
            //生成密钥  
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);  
            //加密  
            Cipher c1 = Cipher.getInstance(Algorithm+"/ECB/PKCS5Padding");  
            c1.init(Cipher.ENCRYPT_MODE, deskey);  
            return c1.doFinal(src);//在单�?方面的加密或解密  
        }catch(Exception e3){
            e3.printStackTrace();
        }
        return null;

    }

    //keybyte为加密密钥，长度�?24字节
    //src为加密后的缓冲区
    private static byte[] decryptMode(byte[] keybyte,byte[] src){
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //解密
            Cipher c1 = Cipher.getInstance(Algorithm+"/ECB/PKCS5Padding");
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        }catch(Exception e3){
            e3.printStackTrace();  
        }  
        return null;  
    }  
      
    
    /**
     * 24字节秘钥  MD5后截取前24�?
     * 
     * @param username
     * @return
     */
    private static byte[] hex(String appkey){  
        String f = DigestUtils.md5Hex(appkey);  
        byte[] bkeys = new String(f).getBytes();  
        byte[] enk = new byte[24];  
        for (int i=0;i<24;i++){  
            enk[i] = bkeys[i];  
        }  
        return enk;  
    }  
    
    
    public static void main(String[] args) {  
      String secret = "appkey1";
      
      System.out.println(encode(secret, "18310639574"));
      
      System.out.println(decode(secret, "V5QEcnRM0UO0S+V5MqJ97g=="));
      
    }  

}
