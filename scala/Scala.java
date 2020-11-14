/*
 *
 *
 */

package scala;

public class Scala	{
	
	public static void main(String[] args)	{
		
		System.out.println("Scala   version 0.0.2   \u00a9 OM   14Nov20");

		if (args.length > 0)	{
			switch (args[0].toLowerCase())	{
                case "le":
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
		System.out.println("Usage: scala < { leasing | le } | { kilometry | km } >");
	}
}	
