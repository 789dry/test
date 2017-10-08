package com.hstong.AutomationTest.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.json.JSONObject; 
/**
 * 读取属性文件
 * @author renyou.deng
 *
 */
public class PropertyUtil {
	String fileName;
	
	public PropertyUtil(String fileName) {
		super();
		this.fileName = fileName;
	}
    public JSONObject getBaseParams() {
    	JSONObject base = new JSONObject();
    	Properties prop = new Properties();
    	String baseParamsFile = "BaseParams.properties";
        try{
            //读取属性文件
            InputStream in = new BufferedInputStream (new FileInputStream(baseParamsFile));
            prop.load(in);     ///加载属性列表
            /*
            Iterator<String> it=prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                base.put(key, value);
            }*/
            
            in.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return base;
    }
	public static void main(String[] args) {
		Properties prop = new Properties();     
        try{
            //读取属性文件a.properties
            InputStream in = new BufferedInputStream (new FileInputStream("BaseParams.properties"));
            prop.load(in);     ///加载属性列表
            Iterator<String> it=prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                System.out.println(key+":"+prop.getProperty(key));
            }
            in.close();
            
            ///保存属性到b.properties文件
            FileOutputStream oFile = new FileOutputStream("b.properties", true);//true表示追加打开
            prop.setProperty("phone", "10086");
            prop.store(oFile, "The New properties file");
            oFile.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
	}

}
