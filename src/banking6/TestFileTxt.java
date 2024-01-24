package banking6;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

public class TestFileTxt {
	
	public static void main(String[] args) throws IOException {
	
		HashSet<Account> set = new HashSet<Account>();
		
		PrintWriter out = new PrintWriter(new FileWriter("src/banking6/test.txt"));
		
		set.add(new NormalAccount("adf1", "asdf", 123,123));
		set.add(new NormalAccount("adf2", "asdf", 123,123));
		set.add(new NormalAccount("adf3", "asdf", 123,123));
		out.println("이건되는dddddddd데");	
		set.add(new NormalAccount("adf4", "asdf", 123,123));

		for(Account ac : set) {
			
			out.println("제발돼라 좀" + ac.accountNum );
			out.println("for문 안에 출력문");	
		}
		out.close();
	
		
			
//			if(writeSave instanceof NormalAccount) {
//				out.print("[보통계좌] = ");
//				out.printf("");
//			}
//			else if(writeSave instanceof HighCreditAccount) {
//				out.print("[신용계좌] = ");
//				out.printf("");
//			}
		
	
		System.out.println("파일생성완료");
		
	}
	
	
}
