package com.weswu.gcpauth.config;

import com.weswu.gcpauth.GcpAuthApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Properties;

public final class IniReader {
	private static final Logger log = LoggerFactory.getLogger(GcpAuthApplication.class);

	private Properties properties = null;
	private static IniReader instance = null;
	
	/** Private constructor 
	 * @throws Exception */
	private IniReader (String confFile) {
	    this.properties = new Properties();
	    try{
	    	properties.load(new FileInputStream(confFile));

	    }catch(Exception e){
			log.error("Failed to load the configuration file: {}.", confFile);
	    }
	}   
	
	/** Creates the instance is synchronized to avoid multithreads problems 
	 * @return */
	public synchronized static IniReader getInstance (String confFile) {
	    if(instance == null){
            synchronized (IniReader.class) {
                if(instance == null){
                    instance = new IniReader(confFile);
               }
            }
        }
	    return instance;
	}
	
	/** Get a property of the property file, Uses singleton pattern */
	public String getProperty(String key){
	    String result = null;
	    if(key !=null && !key.trim().isEmpty()){
	        result = instance.properties.getProperty(key);
	    }
	    return result;
	}
	
	/** Override the clone method to ensure the "unique instance" requirement of this class */
	public Object clone() throws CloneNotSupportedException {
	    throw new CloneNotSupportedException();
	}
}
