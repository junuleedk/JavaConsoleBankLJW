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
	AccountManager ac = new AccountManager();
	
	

	public AutoSaver() {
		super();
		this.set = set;
		this.ac = ac;
	}
	
	public AutoSaver(HashSet<Account> set) {
		this.set = set;
	}

	//참고함
	@Override
	public void run() {
		
		try {
			
			while(true) {
				
				ac.printWriter();
				
				sleep(5000);
				System.out.println("5초마다 자동저장중입니다.");
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
