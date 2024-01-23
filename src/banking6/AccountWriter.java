package banking6;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AccountWriter {

	AccountManager aM = new AccountManager();

	public void accountWriter() {
		
		try {
			
			BufferedWriter out = new BufferedWriter
					(new FileWriter("src/banking6/AutoSaveAccount.txt"));
	
				out.write("여길 어떻게 해야하나.");
				out.newLine();
			
			out.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
		}
		catch (IOException e) {
			System.out.println("오류가 발생하였습니다.");
		}
	}
}
