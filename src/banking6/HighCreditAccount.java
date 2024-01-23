package banking6;

import java.io.Serializable;

class HighCreditAccount extends Account implements Serializable {

	double interest;
	String grade;

	public HighCreditAccount(String accountNum, String userName, int balance, double interest, String grade) {
		super(accountNum, userName, balance);
		this.interest = interest;
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "계좌번호: " + accountNum + "\n" + "고객이름: " + userName + "\n" + "잔고: " + balance + "\n" + "기본이자: " + interest
				+ "%" + "\n" + "신용등급: " + grade + "\n" + "----------------";
	}

	@Override
	public int hashCode() {
		return super.accountNum.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		HighCreditAccount nAcc = (HighCreditAccount) obj;
		if(nAcc.accountNum.equals(super.accountNum)) {
			return true;
		}
		else {
			return false;
		}
	}
}
