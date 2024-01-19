package banking2;


class HighCreditAccount extends Account {
	
	int interest;
	String grade;
	
	public HighCreditAccount (String accountNum, 
			String userName, int balance, int interest, String grade) {
		super(accountNum, userName, balance);
		this.interest = interest;
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "계좌번호: " + accountNum + "\n" + "고객이름: " + userName + "\n" + 
				"잔고: " + balance + "\n" + "기본이자: " + interest+ "%" + "\n" + "신용등급: " + grade + "\n" + "----------------";
	}
	
	@Override
	public double calc() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
