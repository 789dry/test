package com.hstong.api.test;

import java.nio.charset.Charset;

import com.hstong.api.ResponseDataValidator;
//import org.apache.commons.codec.binary.Base64;
import com.hstong.api.test.TestRsaCryptoUtil;
import com.hstong.api.util.RsaCryptoUtil;

public class TestRsaCryptoUtil {
	 public static void main(String[] args) {
		 //System.out.println(Base64.class.getProtectionDomain().getCodeSource().getLocation());
		 
		 	String p = RsaCryptoUtil.encryptByPublicKey("123456", RsaCryptoUtil.PUBLIC_KEY, Charset.forName("utf-8"));
	    	System.out.println(p);
	    	String pwd = RsaCryptoUtil.decryptByPrivateKey(p.replaceAll(" ", "+"), RsaCryptoUtil.PRIVATE_KEY,
	    	Charset.forName("utf-8"));
	    	System.out.println(pwd);
	    	//String test="UHnRWtdwjipBJ58LqN/AomfllVmtiejugfUplrUSVkvbn1S/xrq43SZMfnCHADkBNtcS2uA8pxDI\nteC7RVvR76K/eEQqAhcTXrbXhm7qZi5a4w/A7bOVWbTmEiI1YRve+HFuoZALhKA477J71p4Jfer5\npf0ca3+tPVL/bnYSenA=\n";
	    	String test="H8H2v/loQqzsRQNG/XW1jiekl4YA8CNAGth tU5hVvNLAPBPgDBXRLCPChga4DkfX8S8 B7WCpPdPY0J3LqBhRSbhyaQ3AmBg7/Yqp59I8fJpLSHRyX9IQi2QTMOSK982Zyqel8Gu3iRckogGIWycrDHjgp8/rb1Z2Buz3W/UYk=";
	    	//VUaiQxkfIxnLZ5X1EJa49v8q1dHxx5jwVl/thhzH55UV3DiLsezvdfDMXIlZUN0NCyiEbRDaACUd\n8P7k9N1H8xb03IilLnOwXQBYbyG4zzqT0OrUVwGfrW7p9Ntl8F4lnQXNwE6CzHB4eDN8niyi43bz\n4v79mIXSOJgv5hMnwQ0=\n
	    	
	    	String pwd2 = RsaCryptoUtil.decryptByPrivateKey(test.replaceAll(" ", "+"), RsaCryptoUtil.PRIVATE_KEY,
	    	Charset.forName("utf-8"));
	    	System.out.println(pwd2);
	    	
	    	
	    	
	}
}
