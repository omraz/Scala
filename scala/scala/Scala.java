/*
 *
 *
 */

package scala;

public class Scala	{
	
	public static void main(String[] args)	{

		if (args.length > 0)	{
			switch (args[0].toLowerCase())	{
				case "leasing":
					Leasing leasing = new Leasing();
					break;
				case "kilometry":
				case "km":
					Kilometry kilometry = new Kilometry();
					break;
				default:
					help();
			}
		}
		else	{
			help();
		}
	}
	
	public static void help()	{
		System.out.println("Usage: scala < Leasing | Kilometry >");
	}
}	