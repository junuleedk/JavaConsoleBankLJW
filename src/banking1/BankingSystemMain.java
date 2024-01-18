package banking1;

import java.util.Scanner;


public class BankingSystemMain {
	
	public static void showMenu() {
		System.out.println("### Menu ###");
		System.out.print("1.계좌개설   ");
		System.out.println("2.입  금");
		System.out.print("3.출   금");
		System.out.println("   4.계좌정보출력");
		System.out.println("5.프로그램종료 ");
		System.out.print("메뉴선택>>>");
	}
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		Account account = new Account();
		while(true) {
			
			showMenu();
			int choice = input.nextInt();
			
			switch (choice) {
			case 1:
				account.makeAccount();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				account.showAllAccounts();
				break;
			case 5:
				System.out.println("프로그램종료");
				return;
			}
		}
	}
}
