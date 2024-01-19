package banking1;

import java.util.Scanner;

public class MenuChoice implements Interface {

	public void showMenu() {

		System.out.println("### Menu ###");
		System.out.print("1.계좌개설   ");
		System.out.println("2.입  금");
		System.out.print("3.출   금");
		System.out.println("   4.계좌정보출력");
		System.out.println("5.프로그램종료 ");
		System.out.print("메뉴선택>>>");
	}
	
	public void menuChoice() {

		Scanner input = new Scanner(System.in);
		Account account = new Account();
		
		while (true) {
			showMenu();
			int choice = input.nextInt();

			switch (choice) {
			case MAKE:
				account.makeAccount();
				break;
			case DEPOSIT:
				account.depositMoney();
				break;
			case WITHDRAW:
				account.withdrawMoney();
				break;
			case INQUIRE:
				account.showAccInfo();
				break;
			case EXIT:
				System.out.println("프로그램종료");
				return;
			}
		}
	}
}
