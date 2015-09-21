package HelpFunc;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class G_main {
	
	//logger
	public static Logger logger = Logger.getRootLogger();
	
	public G_main()
	{
		//¸ü¤J³]©wÀÉ
		PropertyConfigurator.configure("log4j.properites");
	}
}
