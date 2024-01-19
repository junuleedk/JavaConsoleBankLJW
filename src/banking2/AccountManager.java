package banking2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class AccountManager {

	HashSet<Account> set = new HashSet<Account>();

	// 계좌개설
	public void makeAccount() {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("***신규계좌개설***");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택: ");
		
		int choice = input.nextInt();
		if(choice == 1) {
			
			System.out.print("계좌번호: ");
			String iaccountNum = input.nextLine();
			input.nextLine();
			System.out.print("고객이름: ");
			String iuserName = input.nextLine();
			
			System.out.print("잔고: ");
			int ibalance = input.nextInt();
			
			System.out.print("기본이자%(정수형태로입력):");
			int iinterest = input.nextInt();
			System.out.println("계좌개설이 완료되었습니다.");
			
			NormalAccount newAcc1 = new NormalAccount(iaccountNum, iuserName, 
					ibalance, iinterest);
			set.add(newAcc1);
		}
		else if(choice == 2) {
			
			System.out.print("계좌번호: ");
			String iaccountNum = input.nextLine();
			input.nextLine();
			System.out.print("고객이름: ");
			String iuserName = input.nextLine();
			
			System.out.print("잔고: ");
			int ibalance = input.nextInt();
			
			System.out.print("기본이자%(정수형태로입력): ");
			int iinterest = input.nextInt();
			
			System.out.print("신용등급(A,B,C등급): ");
			String igrade = input.nextLine();
			input.nextLine();
			System.out.println("계좌개설이 완료되었습니다.");
			
			HighCreditAccount newAcc2 = new HighCreditAccount(iaccountNum, 
					iuserName, ibalance, iinterest, igrade);
			set.add(newAcc2);
		}
		
	}

	// 입금
	public int depositMoney() {

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
			if (searchAcc.equals(saveAccount.accountNum)) {
				saveAccount.balance += depositM;
				System.out.println("입금이 완료되었습니다.");
				set.add(saveAccount);
			} else {
				System.out.println("입금이 완료되었습니다.");
			}
		}
		return depositM;
	}
	

	// 출금
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
			if (searchAcc.equals(saveAccount.accountNum)) {
				saveAccount.balance -= withdrawM;
				System.out.println("출금이 완료되었습니다.");
				set.add(saveAccount);
			} else {
				System.out.println("출금이 완료되었습니다.");
			}
		}
	}

	// 전체계좌정보출력
	public void showAccInfo() {
		
		for (Account saveAccount : set) {
			System.out.println(saveAccount);
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	//보통예금계좌 이자계산
	public int nomalCalcInterest(int bal, int inter, int depos) {
		int interest = 0;
		int result = bal + (bal * (inter / 100) + depos);
		return result;
		
	}
}
