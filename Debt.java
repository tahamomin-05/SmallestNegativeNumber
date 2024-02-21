package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Debt {
	
	private String borrower;
	private String lender;
	private Integer amount;
	
	
	public Debt(String borrower, String lender, Integer amount) {
		super();
		this.borrower = borrower;
		this.lender = lender;
		this.amount = amount;
	}
	public Debt() {
		
	}
	
	public String getBorrower() {
		return borrower;
	}


	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}


	public String getLender() {
		return lender;
	}


	public void setLender(String lender) {
		this.lender = lender;
	}


	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public static Set<String> smallestNegativeBal(List<Debt> debts,Map<String,Integer> balances) {
		int smallestNegativeBal = 0;
		Set<String> res = new TreeSet<>();
		for(String person: balances.keySet()) {
			int totLending = 0;
			int totBorrow = 0;
			int bal = 0;
			for(Debt debt : debts) {
				if(debt.getLender().equals(person))
					totLending+=debt.getAmount();
				if(debt.getBorrower().equals(person))
					totBorrow+=debt.getAmount();
			}
			bal = totLending - totBorrow;
			balances.put(person, bal);
			if(smallestNegativeBal > bal)
				smallestNegativeBal = bal;
		}
		if(smallestNegativeBal >=0)
			res.add("Nobody has a negative balance");
		else {
			for(String person: balances.keySet()) {
				if(balances.get(person)==smallestNegativeBal)
					res.add(person);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int no = Integer.parseInt(sc.nextLine());
		List<Debt> debts = new ArrayList<Debt>();
		Map<String,Integer> balances = new TreeMap<String,Integer>();
		for(int i=0;i<no;i++)
		{
			String str[] = sc.nextLine().split(" ");
			debts.add(new Debt(str[0],str[1],Integer.parseInt(str[2])));
			if(!balances.containsKey(str[0]))
				balances.put(str[0], 0);
			if(!balances.containsKey(str[1]))
				balances.put(str[1],0);
		}
		System.out.println(Debt.smallestNegativeBal(debts, balances).toString());

	}

}
