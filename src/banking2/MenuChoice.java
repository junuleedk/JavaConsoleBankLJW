package banking2;

import java.util.Scanner;

public class MenuChoice {

	public void showMenu() {

		System.out.println("### Menu ###");
		System.out.print("1.계좌개설   ");
		System.out.println("2.입  금");
		System.out.print("3.출   금");
		System.out.println("   4.계좌정보출력");
		System.out.println("5.프로그램종료 ");
		System.out.print("메뉴선택>>>");
	}
	public interface Menu {
		int MAKE=1, DEPOSIT=2, WITHDRAW=3, INQUIRE=4, EXIT=5;
	}
	
	public void menuChoice() {

		Scanner input = new Scanner(System.in);
		Account account = new Account();

		while (true) {
			showMenu();
			int choice = input.nextInt();

			switch (choice) {
			case Menu.MAKE:
				account.makeAccount();
				break;
			case Menu.DEPOSIT:
				account.depositMoney();
				break;
			case Menu.WITHDRAW:
				account.withdrawMoney();
				break;
			case Menu.INQUIRE:
				account.showAccInfo();
				break;
			case Menu.EXIT:
				System.out.println("프로그램종료");
				return;
			}
		}
	}
}
