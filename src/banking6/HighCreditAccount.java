package banking6;

import java.io.Serializable;

class HighCreditAccount extends Account implements Serializable {

	int interest;
	String grade;
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public HighCreditAccount(String accountNum, String userName, int balance, int interest, String grade) {
		super(accountNum, userName, balance);
		this.interest = interest;
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "-----------------------------------------------------------------" + "\n" + "계좌번호: " + accountNum + "  "
	+ "고객이름: " + userName + "  " + "잔고: " + balance + "  " + "기본이자: " + interest
				+ "%" + "  " + "신용등급: " + grade + "\n" + "-----------------------------------------------------------------";
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
