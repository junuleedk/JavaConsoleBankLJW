package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuChoice implements IcustomDefine {

	public void showMenu() {

		System.out.println("### Menu ###");
		System.out.print("1.계좌개설   ");
		System.out.println("2.입  금");
		System.out.print("3.출  금");
		System.out.println("   4.계좌정보출력");
		System.out.println("5.프로그램종료 ");
		System.out.print("선택: ");
	}
	
	public void menuChoice() {

		Scanner input = new Scanner(System.in);
		AccountManager accM = new AccountManager();
		
		while (true) {
			try {
			showMenu();
			int choice = input.nextInt();
		
			switch (choice) {
			case MAKE:
				accM.makeAccount();
				break;
			case DEPOSIT:
				accM.depositMoney(accM.getChoice(), accM.getAddInterest());
				break;
			case WITHDRAW:
				accM.withdrawMoney();
				break;
			case INQUIRE:
				accM.showAccInfo();
				break;
			case EXIT:
				System.out.println("프로그램종료");
				return;
			default:
			}
			}
			catch (InputMismatchException e) {
				System.out.println("숫자를 입력해야합니다.");
				input.nextLine();
			}
		}
	}
}
