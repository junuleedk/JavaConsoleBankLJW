package banking1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Account {

	String accountNum;
	String userName;
	int balance;

	public Account() {
	}

	public Account(String accountNum, String userName, int balance) {

		this.accountNum = accountNum;
		this.userName = userName;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "계좌번호: " + accountNum + "\n" + "고객이름: " + userName + "\n" + "잔고: " + balance + "\n" + "----------------";

	}

	HashSet<Account> set = new HashSet<Account>();
	
	//계좌개설
	public void makeAccount() {

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
		
	}
	
	//전체계좌정보출력
	public void showAccInfo() {
		for (Account saveAccount : set) {
			System.out.println(saveAccount);
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	//입금
	public void depositMoney() {
		
		boolean isFind = false;
		Scanner input = new Scanner(System.in);
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAcc = input.nextLine();
		System.out.print("입금액: ");
		int depositM = input.nextInt();
		
		Iterator<Account> itr = set.iterator();
		
		while (itr.hasNext()) {
			Account saveAccount = itr.next();
			if(searchAcc.equals(saveAccount.accountNum)) {
				saveAccount.balance += depositM;
				System.out.println("입금이 완료되었습니다.");
				set.add(saveAccount);
			}
			else {
				System.out.println("입금이 완료되었습니다.");
			}
		}
	}
	
	//출금
	public void withdrawMoney() {
		boolean isFind = false;
		Scanner input = new Scanner(System.in);
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAcc = input.nextLine();
		System.out.print("출금액: ");
		int withdrawM = input.nextInt();
		
		Iterator<Account> itr = set.iterator();
		
		while (itr.hasNext()) {
			Account saveAccount = itr.next();
			if(searchAcc.equals(saveAccount.accountNum)) {
				saveAccount.balance -= withdrawM;
				System.out.println("출금이 완료되었습니다.");
				set.add(saveAccount);
			}
			else {
				System.out.println("출금이 완료되었습니다.");
			}
		}
	}
}