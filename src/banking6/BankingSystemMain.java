package banking6;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BankingSystemMain {
	
	public static void main(String[] args) {
      
	MenuChoice menuChoice = new MenuChoice();
	menuChoice.menuChoice();
	
	AutoSaverDaemon dt = new AutoSaverDaemon();
	dt.setDaemon(true);
	dt.start();
	
	

	//참고
	Scanner scanner = new Scanner(System.in);
	while(true) {
		dt.saveOption();
		break;
	}
	
	AccountManager accWriter = new AccountManager();
	accWriter.accountWriter();

	
	
	
	
	
	
	
}
	

}
