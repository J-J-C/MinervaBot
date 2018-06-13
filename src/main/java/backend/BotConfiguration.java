package backend;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration Class, contains all necessary info like email, password, crns, etc.
 * This also like a model in the MVC pattern
 */

public class BotConfiguration {
    // final constant variable
    public static final String MINERVA_URL = "https://horizon.mcgill.ca/pban1/twbkwbis.P_WWWLogin";
    public static final String APPLICATION_TITLE = "Minerva Bot";
    
    // UI Configuration
    public static final int MARGIN_OUTER = 10;
    
    // User defined variable
    private static String McGill_Email = "";
    private static String McGill_Password = "";
    private static List<String> CRN_List = new ArrayList<>();
	
    public static String getEmail() {
		return McGill_Email;
	}
	
    public static void setEmail(String mcGill_Email) {
		McGill_Email = mcGill_Email;
	}
	
	public static String getPassword() {
		return McGill_Password;
	}
	
	public static void setPassword(String mcGill_Password) {
		McGill_Password = mcGill_Password;
	}
	
	public static List<String> getCRNs() {
		return CRN_List;
	}
	
	public static void addCRN(String crn) {
		CRN_List.add(crn);
	}
	
	public static boolean infoIsComplete() {
		return McGill_Email.length() > 2 && McGill_Password.length() > 8 && CRN_List.size() > 0;
	}
}
