package banking2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public abstract class Account {

	String accountNum;
	String userName;
	int balance;

	public Account(String accountNum, String userName, int balance) {

		this.accountNum = accountNum;
		this.userName = userName;
		this.balance = balance;
	}

//	public Account() {}

	@Override
	public String toString() {
		return "계좌번호: " + accountNum + "\n" + "고객이름: " + userName + "\n" + "잔고: " + balance + "\n" + "----------------";

	}
	
	public abstract double calc();

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	
}
