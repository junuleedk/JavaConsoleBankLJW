package banking6;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

abstract public class Account implements Serializable {

	String accountNum;
	String userName;
	int balance;
	
	public Account(String accountNum, String userName, int balance) {

		this.accountNum = accountNum;
		this.userName = userName;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "계좌번호: " + accountNum + "\n" + "고객이름: " + userName + "\n" + 
				"잔고: " + balance + "\n" + "----------------";
		
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}