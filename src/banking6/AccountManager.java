package banking6;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
		input.nextLine();
		System.out.print("기본이자%(정수형태로입력):");
		double iinterest = input.nextDouble();
		System.out.println("계좌개설이 완료되었습니다.");
		if (choice == 1) {
			NormalAccount newAcc1 = new NormalAccount(iaccountNum, iuserName, ibalance, iinterest);

			if (set.contains(newAcc1)) {
//				input.nextLine();
				System.out.print("중복계좌를 발견하였습니다. 덮어쓸까요? ( y / n): ");
				char choiceYesNo = input.next().charAt(0);
				if (choiceYesNo == 'y' || choiceYesNo == 'Y') {
					for (Account ac : set) {
						if (newAcc1.accountNum.equals(iaccountNum)) {
							set.remove(newAcc1);
						}
					}
					System.out.println("성공적으로 계좌가 덮어쓰어졌습니다.");
					set.add(newAcc1);
				} else {
					set.add(newAcc1);
				}
			}
			set.add(newAcc1);

		} else if (choice == 2) {
			input.nextLine();
			System.out.print("신용등급(A,B,C등급): ");
			String igrade = input.nextLine();

			if (igrade.equals("A") || igrade.equals("a")) {
				addInterest = 7;
			} else if (igrade.equals("B") || igrade.equals("b")) {
				addInterest = 4;
			} else if (igrade.equals("C") || igrade.equals("c")) {
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
		System.out.println("입금시[500원단위] ");
		System.out.print("계좌번호: ");
		String searchAcc = input.nextLine();
		
		boolean isFind = false;

		while (true) {

			System.out.print("입금액: ");
			int depositM = input.nextInt();
			
			// 입금 음수, 문자 예외처리 if문
			if (depositM > 0 && depositM % 500 == 0) {
				Iterator<Account> itr = set.iterator();

				while (itr.hasNext()) {
					Account saveAccount = itr.next();

					if (saveAccount.accountNum.equals(searchAcc)) {
						// 보통계좌
						if (choiceNum == 1) {

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
						else if (choiceNum == 2) {

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
					
					if (!isFind) {
						System.out.println("계좌를 찾지 못했습니다.");
					}
				}

				break;
			}
			else {
				System.out.println("금액을 잘못 입금하셨습니다.");
			}
		}
	}// depositMoney 메서드 끝

	// 출금
	public void withdrawMoney() {
		boolean isFind = false;
		Scanner input = new Scanner(System.in);
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.println("출금시[1000단위]");
		System.out.print("계좌번호: ");
		String searchAcc = input.nextLine();

		while (true) {
			System.out.print("출금액: ");
			int withdrawM = input.nextInt();
			if (withdrawM > 0 && withdrawM % 1000 == 0) {
				Iterator<Account> itr = set.iterator();

				while (itr.hasNext()) {
					Account saveAccount = itr.next();
					if (saveAccount.accountNum.equals(searchAcc)) {

						if (saveAccount.balance > withdrawM) {
							saveAccount.balance -= withdrawM;
							System.out.println("출금이 완료되었습니다.");
							set.add(saveAccount);
						} 
						else {
							
							System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
							System.out.println("1. 금액전체 출금처리  2. 출금요청취소");
							System.out.print("선택: ");
							int choiceYesNo = input.nextInt();
							
							if(choiceYesNo == 1) {
								System.out.println("금액전체 출금처리를 진행합니다.");
								saveAccount.balance = saveAccount.balance - saveAccount.balance;
								System.out.println("완료되었습니다.");
								isFind = true;
								set.add(saveAccount);
								break;
							}
							else if(choiceYesNo == 2) {
								System.out.println("출금요청취소를 진행합니다.");
								isFind = false;
								break;
							}
							else {
								System.out.println("번호를 잘못 입력하였습니다.");
							}
							
							
							
//							switch (choiceYesNo) {
//							
//							case 1:
//								System.out.println("금액전체 출금처리를 진행합니다.");
//								saveAccount.balance = saveAccount.balance - saveAccount.balance;
//								System.out.println("완료되었습니다.");
//								isFind = true;
//								set.add(saveAccount);
//								break;
//							case 2:
//								System.out.println("출금요청취소를 진행합니다.");
//								isFind = false;
//								break;
//							default:
//								System.out.println("번호를 잘못 입력하셨습니다.");
//								
//							}
						}
					} 
					else {
						System.out.println("계좌를 찾지 못했습니다.");
					}
				} 
				break;
			} 
			else {
				System.out.println("금액을 잘못 입금하셨습니다.");
			}
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
		boolean isFind = false;

		Iterator<Account> itr = set.iterator();
		//참고
		while (itr.hasNext()) {
			Account saveAccount = itr.next();
			if (saveAccount.accountNum.equals(deleteAcc)) {
				itr.remove();
				System.out.println("삭제가 완료되었습니다.");
				isFind = true;
				break;
			} 
		}
		if(!isFind) {
			System.out.println("계좌를 찾지 못했습니다.");
		}
	}

	// IO 직렬화
	public void saveAccountInfo() {

		try {
				ObjectOutputStream out = new ObjectOutputStream
						(new FileOutputStream("src/banking6/AccountInfo.obj"));
	
				for (Account ac : set) {
					
					out.writeObject(ac);
					
				}

				out.close();

		} catch (FileNotFoundException e) {
			System.out.println("AccountInfo.obj 파일이 없습니다.");
			
		} catch (IOException e) {
			System.out.println("오류가 있습니다. saveAccountInfo");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public void readAccountInfo() {
		ObjectInputStream in = null;

		try {
				in = new ObjectInputStream(new FileInputStream("src/banking6/AccountInfo.obj"));
				while (true) {
					Account ac = (Account)in.readObject();
					set.add(ac);
					}
		} 
		catch (FileNotFoundException e) {
					System.out.println("AccountInfo.obj 파일이 없습니다.");
					
		} 
		catch (IOException e) {
					System.out.println("AccountInfo.obj 복원되었습니다.");
					
		} 
		catch (Exception e) {
					e.printStackTrace();
					System.out.println("복원 중 알 수 없는 예외발생");
		} 
		finally {
				try {
					if(in != null) {
						in.close();
					}
				} 
				catch (IOException e) {
					e.printStackTrace();
				} 
				catch (NullPointerException e) {
					System.out.println("널포인트");
				}
			}
	}
	// 직렬화 끝
	
	// 자동저장중 옵션
	AutoSaver as = null;
	
	public void saveOption() {
		
		Scanner input = new Scanner(System.in);
		boolean autoS = false;
		
		System.out.println("자동저장 : 1. On  /  2. Off");
		System.out.println("1번을 누르면 자동진행이 시작됩니다.");
		int choiceOptionN = input.nextInt();
		
		//참고함
		try {
			
			if(choiceOptionN == 1) {
				
				if(!as.isAlive()) {
					
					System.out.println("자동저장을 진행합니다.");
					as = new AutoSaver(set);
					as.setDaemon(true);
					as.start();
					
					autoS = true;
					
				}
				else {
					System.out.println("이미 자동저장이 진행중입니다.");
				}
				
			}
			else if(choiceOptionN == 2 ) {
				
				if(as.isAlive()) {
					
					System.out.println("자동저장을 중단합니다.");
					as.interrupt();
					
					autoS = false;
				}
				
			}
			else {
				System.out.println("잘못 입력하였습니다.");
			}
		}
		catch (Exception e) {
			System.out.println("오류>??");
			as = new AutoSaver(set);
			as.setDaemon(true);
			as.start();
		}
	}
}




//참고용
//// 중복계좌(검색용) 확인
//public boolean iAccountDuplicate(String dAccount) {
//	for (Account saveAccount : set) {
//		if (dAccount.equals(saveAccount.accountNum)) {
//			return true;
//		}
//	}
//	return false;
//}// 중복계좌 확인 끝

//계좌번호에서 중복체크하기
//if (iAccountDuplicate(iaccountNum)) {
//	System.out.print("중복계좌를 발견하였습니다. ( y / n): ");
//	char choiceYesNo = input.next().charAt(0);
//	
//	if (choiceYesNo == 'n' || choiceYesNo == 'N') {
//		System.out.println("기존계좌유지");
//		return;
//	} else if (choiceYesNo == 'y' || choiceYesNo == 'Y') {
//		System.out.println("덮어씀");
//		set.remove(iaccountNum);
//		set.add(iaccountNum);
//		
//		for (Account saveAccount : set) {
//			if (saveAccount.accountNum.equals(iaccountNum)) {
//				set.remove(saveAccount);
//				break;
//			}
//		}
//		input.nextLine();
//	}
//	input.nextLine();
//}
