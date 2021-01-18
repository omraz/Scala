/*
 *
 *
 *
 */

package scala;

import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class Config {

    public static String cnfFile = null;
    public static String dbHost = "localhost";
    public static String dbPort = "3306";
    public static String dbUser = "scala";
    public static String dbPassword = "scala";
    
    public static void parseConfig() {

        //System.out.println("Config file: " + cnfFile);
        
        String value = null;
        
		Properties prop = new Properties();
		InputStream is = null;
		
		try	{
			is = new FileInputStream(cnfFile);
		}
		catch (FileNotFoundException ex)	{
			System.out.println(ex.getMessage());
			return;
		}
		
		try	{
			prop.load(is);
			value = prop.getProperty("dbhost");
			if (value != null && !value.isEmpty())
    			dbHost = value;
    		value = prop.getProperty("dbport");
			if (value != null && !value.isEmpty())
    			dbPort = value;
    		value =  prop.getProperty("dbuser");
			if (value != null && !value.isEmpty())
    			dbUser = value;
    		value = prop.getProperty("dbpassword");
			if (value != null && !value.isEmpty())
    			dbPassword = value;

			is.close();
		}
		catch (IOException ex)	{
			System.out.println(ex.getMessage());
		}

	    //System.out.println("dbhost     = " + dbHost);
	    //System.out.println("dbport     = " + dbPort);
	    //System.out.println("dbuser     = " + dbUser);
	    //System.out.println("dbpassword = " + dbPassword);

    }
    
    Config(String filepath) {
        cnfFile = filepath;
        if (cnfFile != null)
            parseConfig();
    }
    
    Config()    {
        // 1. create class Config
        // 2. set .cnfFile manually
        // 3. call .parseConfig();
    }
    
}
