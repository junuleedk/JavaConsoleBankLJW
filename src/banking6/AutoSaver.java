package banking6;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class AutoSaver extends Thread {
	
	HashSet<Account> set = new HashSet<Account>();
	
	public AutoSaver(HashSet<Account> set) {
		super();
		this.set = set;
	}

	//참고함
	@Override
	public void run() {
		
		try {
			
			while(true) {
				
				PrintWriter out = new PrintWriter(new FileWriter("src/banking6/AccountInfo.txt"));
				out.println("adsf");
				Iterator<Account> itr = set.iterator();
			
				while (itr.hasNext()) {
					Account writeSave = itr.next();
					
					
					if(writeSave instanceof NormalAccount) {
						out.print("[보통계좌] = ");
						out.printf("");
					}
					else if(writeSave instanceof HighCreditAccount) {
						out.print("[신용계좌] = ");
						out.printf("");
					}
						
					out.printf("계좌번호: %s  ", "고객이름: %s ", "잔고: %d ", 
								"기본이자(%): %d ", writeSave.accountNum, writeSave.userName, 
									writeSave.balance);
				}
				sleep(5000);
				System.out.println("5초마다 자동저장중입니다.");
				out.close();
			}	
		}
		catch (InterruptedException e) {
		e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
