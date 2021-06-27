/*
 *
 *
 */

package scala;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class MyFrame	
	extends JFrame 
	implements ActionListener	{

	private static final long serialVersionUID = 123456789; 
	private static final int left = 50;
	private static final int fontSize = 12;
	private static final int width = 200;
	private static int top = 100;
	private static final String dateFormat = "yyyy-MM-dd";
	
	private Container c;
	private JLabel title;
	private JLabel lblDate;
	private JLabel errDate;
	private JTextField txtDate; 
	private JLabel lblKm;
	private JLabel errKm;
	private JTextField txtKm; 
	private JButton save; 

	public MyFrame()
	{
		setTitle("Scala");
		setBounds(300, 90, 300, 380);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
		c = getContentPane();
		c.setLayout(null);
		
		title = new JLabel("Insert data", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setSize(width, 30);
		title.setLocation(left, 30);
		//title.setBorder(border);
		c.add(title); 
		
		lblDate = new JLabel("Datum (" + dateFormat.toUpperCase() + "):");
		lblDate.setFont(new Font("Arial", Font.PLAIN, fontSize));
		lblDate.setSize(width, 24);
		lblDate.setLocation(left, top);
		//lblDate.setBorder(border);
		c.add(lblDate);
		
		top += 24;
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Calendar cal = Calendar.getInstance();
		txtDate = new JTextField(formatter.format(cal.getTime()));
		txtDate.setFont(new Font("Arial", Font.PLAIN, fontSize));
		txtDate.setSize(width, 24);
		txtDate.setLocation(left, top);
		c.add(txtDate);
		
		top += 24;
		errDate = new JLabel("");
		errDate.setFont(new Font("Arial", Font.PLAIN, fontSize));
		errDate.setSize(width, 24);
		errDate.setLocation(left, top);
		errDate.setForeground(Color.RED);
		//errDate.setBorder(border);
		c.add(errDate);
		
		top += 30;
		lblKm = new JLabel("Stav tachometru:");
		lblKm.setFont(new Font("Arial", Font.PLAIN, fontSize));
		lblKm.setSize(width, 24);
		lblKm.setLocation(left, top);
		//lblKm.setBorder(border);
		c.add(lblKm);
		
		top += 24;
		txtKm = new JTextField();
		txtKm.setFont(new Font("Arial", Font.PLAIN, fontSize));
		txtKm.setSize(width, 24);
		txtKm.setLocation(left, top);
		c.add(txtKm);
		
		top += 24;
		errKm = new JLabel("");
		errKm.setFont(new Font("Arial", Font.PLAIN, fontSize));
		errKm.setSize(width, 24);
		errKm.setLocation(left, top);
		errKm.setForeground(Color.RED);
		//errKm.setBorder(border);
		c.add(errKm);

		top += 50;
		save = new JButton("Save");
		save.setFont(new Font("Arial", Font.PLAIN, 15));
		save.setSize(100, 40);
		save.setLocation(100, top);
		save.addActionListener(this);
		c.add(save); 

		setVisible(true); 
	}
	
	public void actionPerformed(ActionEvent e)	{
		boolean ok = true;
		if(e.getSource() == save)	{
			String data1 = txtDate.getText(); 
			if (data1.isEmpty())	{
				errDate.setText("Date must be set!");
				ok = false;
			}
			else	{
				errDate.setText("");
			}
			String errMessage = checkDate(data1);
			if (!errMessage.isEmpty())	{
				errDate.setText(errMessage);
				ok = false;
			}
			else	{
				errDate.setText("");
			}

			String data2 = txtKm.getText();
			if (data2.isEmpty())	{
				errKm.setText("Kilometers must be set!");
				ok = false;
			}
			else	{
				errKm.setText("");
			}
			int km = checkKm(data2);
			if (km < 0)	{
				errKm.setText("Unparseable int: " + data2);
				ok = false;
			}
			else{
				errKm.setText("");
			}

			if (ok)	{
				saveIt(data1, km);
			}
		}
	} 

	public String checkDate(String strDate)	{
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		try	{
			date = formatter.parse(strDate);
			return "";
		}
		catch (ParseException e)	{
			return e.getLocalizedMessage();
		}
	}

	public int checkKm(String strKm)	{
		int km;
		try {
			km = Integer.parseInt(strKm);
			return km;
		}
		catch (NumberFormatException e)	{
			return -1;
		}
	}

	public void saveIt(String date, int km)	{
		
		System.out.println("Save " + date + "  " + km); 

		String connectionString = null;
		Connection con = null;
		Statement stmt = null;

		connectionString = new String(
		    "jdbc:mysql://" + Config.dbHost + 
		    ":"             + Config.dbPort +
		    "/scala?user="  + Config.dbUser + 
		    "&password="    + Config.dbPassword);// +
			//"&serverTimezone=Europe/Prague");
		//System.out.println(connectionString);
		try {
			con = DriverManager.getConnection(connectionString);
			stmt = con.createStatement();
			String sql = "INSERT INTO km (datum, tachometr) VALUES('" + date + "'," + km + ")";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
		}
		catch (SQLException ex)	{
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
}
