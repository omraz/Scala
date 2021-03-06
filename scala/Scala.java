/*
 *  0.0.4   18Jan21 amended config file
 *  0.0.6	04Jul21	amended the "Add" dialog
 *
 */

package scala;

public class Scala	{

    public static final int ACTION_HELP = 0;
    public static final int ACTION_LEASING = 1;
    public static final int ACTION_KM = 2;
    public static final int ACTION_ADD = 3;
    
	public static void main(String[] args)	{
		
		System.out.println("Scala   version 0.0.6   \u00a9 OM   04Jul21");
		
		int action = ACTION_HELP;
		String configFile = null;

		for (int i = 0; i < args.length; i++)    {
			switch (args[i].toLowerCase())	{
                case "le":
				case "leasing":
				    action = ACTION_LEASING;
					break;
				case "kilometry":
				case "km":
				    action = ACTION_KM;
					break;
				case "add":
					action = ACTION_ADD;
					break;
				case "-f":
				    if (i + 1 <= args.length)   {
				        configFile = args[i+1];
				    }
				    else
				    {
				        action = ACTION_HELP;
				    }
				    break;
			}
		
		}
		//System.out.printf("Action: %d\nConfig file: %s\n", action, configFile);
		Config config = new Config(configFile);
		
		switch (action) {
		    case ACTION_HELP:
		        help();
		        break;
		    case ACTION_LEASING:
    			Leasing leasing = new Leasing();
    			break;
    		case ACTION_KM:
				Kilometry kilometry = new Kilometry();
				break;
			case ACTION_ADD:
				MyFrame f = new MyFrame();
				break;
		}
		
	}
	
	public static void help()	{
		System.out.println("Usage: scala { -f <config-file> } { [ leasing | le ] | [ kilometry | km ] [ add ] }");
	}
}	
