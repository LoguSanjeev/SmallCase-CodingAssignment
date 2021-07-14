package Utilities;
import com.sun.jna.platform.*;


public class Prerequisites {
	  public void setDriverPath() {
	        if (Platform.isMac()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver");
	        }
	        if (Platform.isWindows()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	        }
	        if (Platform.isLinux()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
	        }
	    }
}
