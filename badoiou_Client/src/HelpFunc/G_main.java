package HelpFunc;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class G_main {
	
	//logger
	public static Logger logger = Logger.getRootLogger();
	
	public G_main() {
		//載入設定檔
		PropertyConfigurator.configure("log4j.properites");
	}

	public G_main(String log4j_pro_path) {

		// 載入設定檔
		if (log4j_pro_path.equals("") || log4j_pro_path==null){
			// 載入設定檔
			PropertyConfigurator.configure("log4j.properites");
		}else{
			System.out.println("my log4j p:"+log4j_pro_path);
			PropertyConfigurator.configure(log4j_pro_path);	
		}
	
	}
}
