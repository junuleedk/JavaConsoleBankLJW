package banking6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuChoice implements IcustomDefine {

	public void showMenu() {

		System.out.println("### Menu ###");
		System.out.print("1.계좌개설      ");
		System.out.println("2.입   금");
		System.out.print("3.출   금");
		System.out.println("      4.계좌정보출력");
		System.out.print("5.계정정보삭제 ");
		System.out.println("  6.저장옵션");
		System.out.println("7.프로그램 종료");
		System.out.print("선택: ");
	}

	public void menuChoice() {
		Scanner input = new Scanner(System.in);
		AccountManager accountManager = new AccountManager();
		accountManager.readAccountInfo();
		//무한루프
		while (true) {
		
				showMenu();
				
			try {
				int choice = input.nextInt();
				
				switch (choice) {
				case MAKE:
					accountManager.makeAccount();
					break;
				case DEPOSIT:
					accountManager.depositMoney(accountManager.addInterest);
					break;
				case WITHDRAW:
					accountManager.withdrawMoney();
					break;
				case INQUIRE:
					accountManager.showAccInfo();
					break;
				case DELETE:
//					accountManager.printWriter();
					accountManager.deleteAccountInfo();
					break;
				case SAVE:
					accountManager.saveOption();
					break;
				case EXIT:
					accountManager.saveAccountInfo();
					System.out.println("AccountInfo.obj 파일로 저장되었습니다.");
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
