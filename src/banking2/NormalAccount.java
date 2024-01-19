package banking2;

class NormalAccount extends Account {

	int interest;

	public NormalAccount(String accountNum, String userName, int balance, int interest) {
		super(accountNum, userName, balance);
		this.interest = interest;
	}
	
	@Override
	public String toString() {
		return "계좌번호: " + accountNum + "\n" + "고객이름: " + userName + "\n" + 
				"잔고: " + balance + "\n"  + "기본이자: " + interest + "%" + "\n" + "----------------";
	}
	
}
