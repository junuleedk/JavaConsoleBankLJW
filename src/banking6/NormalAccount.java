package banking6;

import java.io.Serializable;
import java.util.Iterator;

class NormalAccount extends Account implements Serializable {

	int interest;
	
	public NormalAccount(String accountNum, String userName, int balance, int interest) {
		super(accountNum, userName, balance);
		this.interest = interest;
	}
	
	@Override
	 public String toString() {
		return "------------------------------------------------------" + "\n" + "계좌번호: " + accountNum + "  " + "고객이름: " + userName + "  " + 
				"잔고: " + balance + "  "  + "기본이자: " + interest + "%" + "\n" + "------------------------------------------------------";
	}
	
	@Override
	public int hashCode() {
		return super.accountNum.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		NormalAccount nAcc = (NormalAccount) obj;
		if(nAcc.accountNum.equals(super.accountNum)) {
			return true;
		}
		else {
			return false;
		}
		
	}
}	
	
	
	 // 이자 계산 메서드 추가
//	   public double calculateInterest() {
//	        return (balance * interest) / 100;
//	    }
//}

