package banking2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class AccountManager {

	// 구동
	HashSet<Account> set = new HashSet<Account>();
	int choice;
	double addInterest;

	public double getAddInterest() {
		return addInterest;
	}

	public void setAddInterest(double addInterest) {
		this.addInterest = addInterest;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	// 계좌개설
	public void makeAccount() {

		Scanner input = new Scanner(System.in);

		System.out.println("***신규계좌개설***");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택: ");

		choice = input.nextInt();

		input.nextLine();

		System.out.print("계좌번호: ");
		String iaccountNum = input.nextLine();

		System.out.print("고객이름: ");
		String iuserName = input.nextLine();

		System.out.print("잔고: ");
		int ibalance = input.nextInt();

		System.out.print("기본이자%(정수형태로입력):");
		double iinterest = input.nextDouble();

		if (choice == 1) {
			System.out.println("계좌개설이 완료되었습니다.");
			NormalAccount newAcc1 = new NormalAccount(iaccountNum, iuserName, ibalance, iinterest);
			set.add(newAcc1);

		} else if (choice == 2) {
			input.nextLine();
			System.out.print("신용등급(A,B,C등급): ");
			String igrade = input.nextLine();

			if (igrade.equals("A")) {
				addInterest = 7;
			} else if (igrade.equals("B")) {
				addInterest = 4;
			} else if (igrade.equals("C")) {
				addInterest = 2;
			} else {
				addInterest = 0;
			}

			System.out.println("계좌개설이 완료되었습니다.");

			HighCreditAccount newAcc2 = new HighCreditAccount(iaccountNum, iuserName, ibalance, iinterest, igrade);
			set.add(newAcc2);
		}

	}

	// 입금 및 이자 계산
		public void depositMoney(int choiceNum, double addInterest) {

		Scanner input = new Scanner(System.in);
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAcc = input.nextLine();
		System.out.print("입금액: ");
		int depositM = input.nextInt();

		Iterator<Account> itr = set.iterator();

		boolean isFind = false;
		while (itr.hasNext()) {
			Account saveAccount = itr.next();

			if (searchAcc.equals(saveAccount.accountNum)) {
				// 보통계좌
				if (choiceNum == 1 && saveAccount.accountNum.equals(((NormalAccount) saveAccount).accountNum)) {

					double baseInterest = ((NormalAccount) saveAccount).interest;
					double result = saveAccount.balance + ((saveAccount.balance * baseInterest) / 100)
							+ depositM;

					saveAccount.balance = (int) Math.round(result);

					itr.remove();

					set.add(saveAccount);

					System.out.println("입금이 완료되었습니다. 현재 잔고(이자 포함): " + result);
					isFind = true;
					break;
				}
				// 신용계좌
				else if (choiceNum == 2
						&& saveAccount.accountNum.equals(((HighCreditAccount) saveAccount).accountNum)) {

					double baseInterest = ((HighCreditAccount) saveAccount).interest;
					System.out.println(">>>>>>>>" + baseInterest);
					double result = saveAccount.balance + ((saveAccount.balance * baseInterest) / 100)
							+ ((saveAccount.balance * (addInterest)) / 100) + depositM;

					saveAccount.balance = (int) Math.round(result);

					itr.remove();

					set.add(saveAccount);

					System.out.println("입금이 완료되었습니다. 현재 잔고(이자 포함): " + result);
					isFind = true;
					break;
				}

			}
		}

		if (!isFind) {
			System.out.println("계좌를 찾지 못했습니다.");
		}
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

}
