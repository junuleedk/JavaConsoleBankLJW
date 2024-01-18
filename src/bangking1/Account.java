package bangking1;

import java.util.Scanner;

class Account {
	String accountNum;
	String userName;
	int balance;
	
	public Account(String accountNum, String userName, int balance) {
		super();
		this.accountNum = accountNum;
		this.userName = userName;
		this.balance = balance;
	}
	
	public void makeAccount() {
		
		Scanner input = new Scanner(System.in);
		String iaccountNum, iuserName, ibalance;
		System.out.print("계좌번호: ");
		iaccountNum = input.nextLine();
		System.out.print("고객이름: ");
		iuserName = input.nextLine();
		System.out.print("잔고: ");
		ibalance = input.nextLine();
		System.out.println("계좌개설이 완료되었습니다.");
	}
	public void showAccInfo() {
		System.out.println("계좌번호: " + accountNum);
		System.out.println("고객이름: " + userName);
		System.out.println("잔고: " + balance);
		System.out.println("--------------------");
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	dddddddd
	

}
