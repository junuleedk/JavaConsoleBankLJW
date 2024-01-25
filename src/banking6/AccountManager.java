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
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class AccountManager {

	// HasSet을 선언. Account객체 컬렉션 생성
	HashSet<Account> set = new HashSet<Account>();
	// 추가 이자 변수
	int addInterest;
	
	//추가이자값을 가져오고 설정하는 게터세터 메서드
	public int getAddInterest() {
		return addInterest;
	}
	
	public void setAddInterest(int addInterest) {
		this.addInterest = addInterest;
	}
	
	//생성자 초기화
	public AccountManager() {
		super();
		this.set = set;
	}

	// 계좌개설과 중복계정처리
	public void makeAccount() {
		
		Scanner input = new Scanner(System.in);

		System.out.println("***신규계좌개설***");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		int choice;
		while(true) {
			
			System.out.print("선택: ");
			choice = input.nextInt();
			if(!(choice == 1 || choice ==2)) {
				System.out.println("1 or 2만 선택하실 수 있습니다.");
			}
			else {
				break;
			}
		}

		input.nextLine();
		System.out.print("계좌번호: ");
		String iaccountNum = input.nextLine();
		System.out.print("고객이름: ");
		String iuserName = input.nextLine();
		System.out.print("잔고: ");
		int ibalance = input.nextInt();
		input.nextLine();
		System.out.print("기본이자%(정수형태로입력):");
		int iinterest = input.nextInt();
		
		try {
			//보통계좌
			if(choice == 1) {
				NormalAccount newAcc1 = new NormalAccount(iaccountNum, iuserName, ibalance, iinterest);
				
				if(set.contains(newAcc1)) {
					
					input.nextLine();
					System.out.print("중복계좌가 발견되었습니다. 덮어쓸까요? (Yes: y, No: n)");
					
					char choiceYesNo = input.next().charAt(0);
					
					if (choiceYesNo == 'y' || choiceYesNo == 'Y') {
						
						set.remove(newAcc1);
						set.add(newAcc1);
						System.out.println("성공적으로 계좌가 덮어쓰어졌습니다.");
						
					}
					//유지하기
					else if (choiceYesNo == 'n' || choiceYesNo == 'N') {
						set.add(newAcc1);
						System.out.println("기존 계좌를 유지합니다.");
					}
					//어느 것도 해당 안될때
					else {
						System.out.println("y(Y) 또는 n(N)만 선택하실 수 있습니다.");
					}
					
				}
				else {
					set.add(newAcc1);
					System.out.println("계좌개설이 완료되었습니다.");
				}
			}
			//신용계좌
			else if(choice == 2) {
				//추가로 등급을 받아서 추가 이자계산에 필요한 값을 정함.
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
				
				HighCreditAccount newAcc2 = new HighCreditAccount
						(iaccountNum, iuserName, ibalance, iinterest, igrade);
				
				if(set.contains(newAcc2)) {
					
					System.out.print("중복계좌가 발견되었습니다. 덮어쓸까요? (Yes: y, No: n)");
					char choiceYesNo = input.next().charAt(0);
					
					if (choiceYesNo == 'y' || choiceYesNo == 'Y') {
						
						set.remove(newAcc2);
						set.add(newAcc2);
						System.out.println("성공적으로 계좌가 덮어쓰어졌습니다.");
					}
					else if (choiceYesNo == 'n' || choiceYesNo == 'N'){
						set.add(newAcc2);
						System.out.println("기존 계좌를 유지합니다.");
					}
					else {
						System.out.println("y(Y) 또는 n(N)만 선택하실 수 있습니다.");
					}
				}
				else {
					set.add(newAcc2);
					System.out.println("계좌계설이 완료되었습니다.");
				}
			}
			else {
				System.out.println("계좌종류가 다르며 1과 2만 선택하실 수 있습니다.");
			}
		}
		catch(Exception e) {
			System.out.println("계좌종류가 다릅니다.");
		}
	}// makeAccount 메서드 끝

	// 입금 및 이자 계산
	//addIntest라는 정수 매개변수를 받는 매서드를 만듬
	public void depositMoney(int addInterest) {
		
		//사용자로부터 입력받기
		Scanner input = new Scanner(System.in);
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.println("입금시[500원단위] ");
		System.out.print("계좌번호: ");
		String searchAcc = input.nextLine();
		
		while (true) {
			
			System.out.print("입금액: ");
			int depositM = input.nextInt();
			
			//특판계좌를 위한 변수명
			//입금을 몇번했는지 카운터
//			int depositCount = 0;
			//추가금액지급 500원으로 설정
//			int giftMoney = 0;
			
			// 입금 음수, 문자 예외처리 if문
			if (depositM > 0 && depositM % 500 == 0) {
				Iterator<Account> itr = set.iterator();
				while (itr.hasNext()) {
					Account saveAccount = itr.next();
					
					if (saveAccount.accountNum.equals(searchAcc)) {
						
						int choiceNum;
						
				        if (saveAccount instanceof NormalAccount) {
				        	//보통계좌
				            choiceNum = 1;  
				        } else if (saveAccount instanceof HighCreditAccount) {
				            //신용계좌
				        	choiceNum = 2; 
				        } else {
				            choiceNum = 0;  
				        }
						
						//보통계좌이자계산식
						if (choiceNum == 1) {
							
							//특판계좌이며 카운트 증가시켜 횟수올림
//							depositCount++;

							int baseInterest = ((NormalAccount) saveAccount).interest;
							int result = saveAccount.balance + ((saveAccount.balance * baseInterest) / 100)
									+ depositM;
							
							//특판계좌 계산식(입금 1회차, 2회차를 확인하는 조건문)
							//즉, 짝수이면을 확인하기 위한 조건문
//							if(depositCount % 2 == 0) {
//								result = saveAccount.balance + ((saveAccount.balance * baseInterest) / 100)
//										+ depositM;
//								//500원 추가
//								result += 500;
//							}

							saveAccount.balance = (int) Math.round(result);
							
							itr.remove();
							set.add(saveAccount);
							System.out.println("입금이 완료되었습니다.");
							
							break;
						}
						// 신용계좌 이자계산식
						else if (choiceNum == 2) {
							
							int baseInterest = ((HighCreditAccount) saveAccount).interest;
							int result = saveAccount.balance + ((saveAccount.balance * baseInterest) / 100)
									+ ((saveAccount.balance * addInterest) / 100) + depositM;
							
							saveAccount.balance = (int) Math.round(result);

							itr.remove();
							set.add(saveAccount);
							System.out.println("입금이 완료되었습니다.");
							break;
						}
					}
				}

				break;
			}
			else {
				System.out.println("금액을 잘못 입금하셨습니다.");
			}
		}
	}// depositMoney 메서드 끝
	
	//출금
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
							break;
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
	
	//계좌정보 출력

	// 전체계좌정보출력
	public void showAccInfo() {

		for (Account saveAccount : set) {
			System.out.println(saveAccount);
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	//계정정보 삭제
	
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
	}//deleteAccountInfo 끝
	
	//IO 직렬화 - 저장

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
	}//saveAccountInfo 끝
	
	//IO 직렬화 - 복원

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
		catch (NullPointerException e) {
			System.out.println("예외처리발생");
			as = new AutoSaver();
			as.setDaemon(true);
			as.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// txt파일 생성 
	
	//파일 생성
	public void printWriter() {
		
	
			System.out.println("AccountInfo.txt 파일이 저장되었습니다."); 
			
		try {	
			
			PrintWriter out = new PrintWriter(new FileWriter("src/banking6/AccountInfo.txt"));
			
			Iterator<Account> itr = set.iterator();
			
			while (itr.hasNext()) {
				Account writeSave = itr.next();
				
				if (writeSave instanceof NormalAccount) {
					out.println("[보통계좌]");
					out.printf("계좌번호: %s  고객이름: %s  잔고: %d  기본이자: ", writeSave.accountNum, writeSave.userName,
							writeSave.balance, ((NormalAccount) writeSave).interest);
					out.println();
					
				} else if (writeSave instanceof HighCreditAccount) {
					out.println("[신용계좌]");
					out.printf("계좌번호: %s  고객이름: %s  잔고: %d  기본이자:  신용등급: %s", writeSave.accountNum,
							writeSave.userName, writeSave.balance, ((HighCreditAccount) writeSave).interest,
							((HighCreditAccount) writeSave).grade);
				}
			}
			out.close();
		}
		catch(Exception e) {
			System.out.println("예외발생함");
			e.printStackTrace();
		}
    }
	
}




//참고용 List사용시
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
