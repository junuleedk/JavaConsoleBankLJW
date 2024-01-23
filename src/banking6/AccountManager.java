package banking6;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class AccountManager {

	// 구동, 컬렉션
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

	// 중복계좌(검색용) 확인
	public boolean iAccountDuplicate(String dAccount) {
		for (Account saveAccount : set) {
			if (dAccount.equals(saveAccount.accountNum)) {
				return true;
			}
		}
		return false;
	}// 중복계좌 확인 끝

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
		
		if (iAccountDuplicate(iaccountNum)) {
			System.out.print("중복계좌를 발견하였습니다. ( y / n): ");
			char choiceYesNo = input.next().charAt(0);
			
			if (choiceYesNo == 'n' || choiceYesNo == 'N') {
				System.out.println("기존계좌유지");
				return;
			} else if (choiceYesNo == 'y' || choiceYesNo == 'Y') {
				System.out.println("덮어씀");
//				set.remove(iaccountNum);
//				set.add(iaccountNum);
				
				for (Account saveAccount : set) {
					if (saveAccount.accountNum.equals(iaccountNum)) {
						set.remove(saveAccount);
						break;
					}
				}
				input.nextLine();
			}
			input.nextLine();
		}

		System.out.print("고객이름: ");
		String iuserName = input.nextLine();
		System.out.print("잔고: ");
		int ibalance = input.nextInt();
		input.nextLine();
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
	}// makeAccount 메서드 끝

	// 입금 및 이자 계산
	public void depositMoney(int choiceNum, double addInterest) {

		Scanner input = new Scanner(System.in);
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAcc = input.nextLine();

		while (true) {

			System.out.print("입금액: ");
			int depositM = input.nextInt();
			// 입금 음수, 문자 예외처리 if문

			if (depositM > 0 && depositM % 500 == 0) {
				Iterator<Account> itr = set.iterator();
				boolean isFind = false;

				while (itr.hasNext()) {
					Account saveAccount = itr.next();

					if (iAccountDuplicate(saveAccount.accountNum)) {
						// 보통계좌
						if (choiceNum == 1 && iAccountDuplicate(((NormalAccount) saveAccount).accountNum)) {

							double baseInterest = ((NormalAccount) saveAccount).interest;
							double result = saveAccount.balance + ((saveAccount.balance * baseInterest) / 100)
									+ depositM;

							saveAccount.balance = (int) Math.round(result);

							itr.remove();

							set.add(saveAccount);

							System.out.println("입금이 완료되었습니다.");
							isFind = true;
							break;
						}
						// 신용계좌
						else if (choiceNum == 2 && iAccountDuplicate(((HighCreditAccount) saveAccount).accountNum)) {

							double baseInterest = ((HighCreditAccount) saveAccount).interest;
							double result = saveAccount.balance + ((saveAccount.balance * baseInterest) / 100)
									+ ((saveAccount.balance * (addInterest)) / 100) + depositM;

							saveAccount.balance = (int) Math.round(result);

							itr.remove();

							set.add(saveAccount);

							System.out.println("입금이 완료되었습니다.");
							isFind = true;
							break;
						}
					}
				}

				if (!isFind) {
					System.out.println("계좌를 찾지 못했습니다.");
				}
				break;
			} else {
				System.out.println("금액을 잘못 입금하셨습니다.");
			}
		}
	}// depositMoney 메서드 끝

	// 출금
	public void withdrawMoney() {
		boolean isFind = false;
		Scanner input = new Scanner(System.in);
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAcc = input.nextLine();

		while (true) {
			System.out.print("출금액: ");
			int withdrawM = input.nextInt();
			if (withdrawM > 0 && withdrawM % 1000 == 0) {
				Iterator<Account> itr = set.iterator();

				while (itr.hasNext()) {
					Account saveAccount = itr.next();
					if (iAccountDuplicate(saveAccount.accountNum)) {

						if (saveAccount.balance > withdrawM) {
							saveAccount.balance -= withdrawM;
							System.out.println("출금이 완료되었습니다.");
							set.add(saveAccount);
						} else {
							System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
							System.out.println("1. 금액전체 출금처리  2. 출금요청취소");
							System.out.print("선택: ");
							int choiceYesNo = input.nextInt();

							switch (choiceYesNo) {
							case 1:
								System.out.println("금액전체 출금처리를 진행합니다.");
								saveAccount.balance = saveAccount.balance - saveAccount.balance;
								System.out.println("완료되었습니다.");
								set.add(saveAccount);
								break;
							case 2:
								System.out.println("출금요청취소를 진행합니다.");
								break;
							default:
								System.out.println("번호를 잘못 입력하셨습니다.");
							}
						}
					} else {
						if (!isFind) {
							System.out.println("계좌를 찾지 못했습니다.");
						}
					}
				}
			} else {
				System.out.println("금액을 잘못 입금하셨습니다.");
			}
			break;
		}
	}// withdrawMoney 끝

	// 전체계좌정보출력
	public void showAccInfo() {

		for (Account saveAccount : set) {
			System.out.println(saveAccount);
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

	// 계좌정보삭제
	public void deleteAccountInfo() {

		System.out.print("삭제 할 계좌번호를 입력하세요: ");
		Scanner input = new Scanner(System.in);
		String deleteAcc = input.nextLine();

		Iterator<Account> itr = set.iterator();

		while (itr.hasNext()) {
			Account saveAccount = itr.next();
			if (iAccountDuplicate(deleteAcc)) {
				set.remove(deleteAcc);
				System.out.println("삭제가 완료되었습니다.");
				set.add(saveAccount);
			} else {
				System.out.println("계좌를 찾지 못했습니다.");
			}
			break;
		}

	}

	// IO 직렬화
	public void saveAccountInfo() {

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/banking6/AccountInfo.obj"));

			for (Account ac : set) {
				out.writeObject(ac);
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readAccountInfo() {
	    ObjectInputStream in = null;

	    try {
	        in = new ObjectInputStream(new FileInputStream("src/banking6/AccountInfo.obj"));

	        while (true) {
	            try {
	                Account ac = (Account) in.readObject();
	                set.add(ac);
	            } catch (EOFException e) {
	                // 파일의 끝에 도달함
	                break;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (in != null) {
	                in.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	// 직렬화 끝

	// 프로그램 종료시 저장
	public void saveFileExit() {
		saveAccountInfo();
	}
}
