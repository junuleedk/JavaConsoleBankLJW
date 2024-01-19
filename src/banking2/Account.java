package banking2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Account {

	String accountNum;
	String userName;
	int balance;
	int interest;
	String grade;
	
	public Account(String accountNum, String userName, 
			int balance, int interest, String grade) {

		this.accountNum = accountNum;
		this.userName = userName;
		this.balance = balance;
		this.interest = interest;
		this.grade = grade;
	}
	public Account() {}

	@Override
	public String toString() {
		return "계좌번호: " + accountNum + "\n" + "고객이름: " + userName + "\n" + 
				"잔고: " + balance + "\n" + "기본이자: " + interest + "\n" + 
				"신용등급: " + grade + "\n" + "----------------";

	}
}
