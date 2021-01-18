/*
 *
 *
 */

package scala;

import java.util.Calendar;
import java.util.Date;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Kilometry	{
	
	final static int kmPerYear = 25000;
	
	Kilometry()	{
		
		SimpleDateFormat formatOut = new SimpleDateFormat("dd.MM.YYYY");
		SimpleDateFormat formatDB = new SimpleDateFormat("yyyy-MM-dd");
		String connectionString = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		double kmPerDay = kmPerYear / 365.0;
		
		Calendar begin = Calendar.getInstance();
		begin.set(2020, 5, 22, 0, 0, 0);
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		int km = 0;
		int fromKm = 0;
		int toKm = 0;
		long days = 0;
		System.out.println("--------------------------------------------------");
		System.out.println("    od           do      dni   skut/plan     pct  ");
		System.out.println("--------------------------------------------------");
				
		connectionString = new String(
		    "jdbc:mysql://" + Config.dbHost + 
		    ":"             + Config.dbPort +
		    "/scala?user="  + Config.dbUser + 
		    "&password="    + Config.dbPassword);
		try	{
			//con = DriverManager.getConnection("jdbc:mysql://localhost/scala?user=scala&password=scala");
			con = DriverManager.getConnection(connectionString);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM scala.km");
			while (rs.next())	{
				if (rs.isFirst())	{
					fromDate.setTime(formatDB.parse(rs.getString(2)));
					fromKm = rs.getInt(3);
					continue;
				}
				toDate.setTime(formatDB.parse(rs.getString(2)));
				toKm = rs.getInt(3);
				days = Duration.between(fromDate.toInstant(), toDate.toInstant()).toDays();
				km = toKm - fromKm;
				System.out.printf("%s - %s %4d %6d/%-6d  %5.1f%%\n", 
					formatOut.format(fromDate.getTime()), 
					formatOut.format(toDate.getTime()),
					days,
					km,
					Math.round(days * kmPerDay),
					100.0 * km / (days * kmPerDay));
				
				fromDate.setTime(toDate.getTime());
				fromKm = toKm;
			}
			rs.close();
			con.close();
		
		}	catch (SQLException ex)	{
            System.out.println(connectionString);
			System.out.println("SQLException: " + ex.getMessage());
			//System.out.println("SQLState: " + ex.getSQLState());
			//System.out.println("VendorError: " + ex.getErrorCode());
			return;
		}	catch (ParseException ex)	{
			System.out.println("ParseException: " + ex.getMessage());			
		}
				
		System.out.println("--------------------------------------------------");
		days = Duration.between(begin.toInstant(), toDate.toInstant()).toDays();
		System.out.printf("%s - %s %4d %6d/%-6d  %5.1f%%\n", 
			formatOut.format(begin.getTime()), 
			formatOut.format(toDate.getTime()),
			days,
			toKm,
			Math.round(days * kmPerDay),
			100.0 * toKm / (days * kmPerDay));
		System.out.println("--------------------------------------------------");
		
 	}
}
