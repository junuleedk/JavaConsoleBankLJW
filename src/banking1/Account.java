package banking1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Account {
	
	String accountNum;
	String userName;
	int balance;
	
	public Account() {}
	public Account(String accountNum, String userName, int balance) {

		this.accountNum = accountNum;
		this.userName = userName;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "계좌번호: " + accountNum + "\n" + "고객이름: " + userName + "\n" + "잔고: " + balance;
	}

	HashSet<Account> set = new HashSet<Account>();
	
	public Account makeAccount() {

		Scanner input = new Scanner(System.in);

		System.out.print("계좌번호: ");
		String iaccountNum = input.nextLine();

		System.out.print("고객이름: ");
		String iuserName = input.nextLine();

		System.out.print("잔고: ");
		int ibalance = input.nextInt();

		System.out.println("계좌개설이 완료되었습니다.");

		Account newAcc = new Account(iaccountNum, iuserName, ibalance);
		
		set.add(newAcc);
	    
	    return newAcc;
	}
	
	public void showAllAccounts() {
		for (Account saveAccount : set) {
			System.out.println(saveAccount);
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
}
