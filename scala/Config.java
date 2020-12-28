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

    public static String cnfFile = "scala.config";
    public static String dbHost = "localhost";
    public static String dbPort = "3306";
    public static String dbUser = "scala";
    public static String dbPassword = "scala";
    
    public static void parseConfig() {

        System.out.println("Config file: " + cnfFile);
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        
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
			dbHost = prop.getProperty("dbhost");
			dbPort = prop.getProperty("dbport");
			dbUser = prop.getProperty("dbuser");
			dbPassword = prop.getProperty("dbpassword");
			System.out.println("dbhost     = " + dbHost);
			System.out.println("dbport     = " + dbPort);
			System.out.println("dbuser     = " + dbUser);
			System.out.println("dbpassword = " + dbPassword);

			is.close();
		}
		catch (IOException ex)	{
			System.out.println(ex.getMessage());
			return;
		}


    }
    
    Config(String filepath) {
        cnfFile = filepath;
        parseConfig();
    }
    
    Config()    {
        parseConfig();
    }
    
}
