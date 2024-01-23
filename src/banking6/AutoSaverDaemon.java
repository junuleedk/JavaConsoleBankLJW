package banking6;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AutoSaverDaemon extends Thread {
	
	Scanner input = new Scanner(System.in);
	
	boolean autoS = false;
	public void saveOption() {
		
		System.out.println("1번을 누르면 자동진행이 시작됩니다.	");
		System.out.println("(자동저장 중 종료하고 싶으면 2번을 누르세요.)");
		System.out.print("선택: ");
		int choiceOptionN = input.nextInt();
		
		//참고함
		if(choiceOptionN == 1) {
			if(!autoS) {
				autoS = true;
				input.nextLine();
				System.out.println("자동저장을 진행합니다.");
			}
			else {
				System.out.println("이미 자동저장이 진행중입니다." + isAlive());
			}
			
		}
		else if(choiceOptionN == 2 ) {
			try {
				if(!autoS) {
					autoS = false;
					System.out.println("자동저장을 중단합니다.");
					interrupt();
				}
				else {
					System.out.println("잘못 입력하였습니다.");
				}
				
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("잘못 입력하였습니다.");
		}
	}
	
	
	
	@Override
	public void run() {
		while(true) {
			try {
				sleep(5000);
				System.out.println("5초마다 자동저장중입니다. ");
				
			}
			catch (InterruptedException e) {
			e.printStackTrace();
			}
		}
	}
}
