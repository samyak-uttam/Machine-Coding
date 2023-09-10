package src.services;

import src.models.User;
import src.models.split.Split;
import src.models.expense.Expense;
import src.models.expense.ExpenseType;
import src.models.expense.ExpenseMetadata;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ExpenseManager {

	private List<Expense> expenses;
	private Map<String, User> userMap;
	private Map<String, Map<String, Double>> balanceSheet;

	public Map<String, User> getUserMap() {
		return userMap;
	}

	public ExpenseManager() {
		expenses = new ArrayList<Expense>();
		userMap = new HashMap<String, User>();
		balanceSheet = new HashMap<String, Map<String, Double>>();
	}

	public void addUser(User user) {
		userMap.put(user.getId(), user);
		balanceSheet.put(user.getId(), new HashMap<String, Double>());
	}

	public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits, ExpenseMetadata metadata) {
		Expense expense = ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy), splits, metadata);
		expenses.add(expense);
		for (Split split : expense.getSplits()) {
			String paidTo = split.getUser().getId();
			Map<String, Double> balances = balanceSheet.get(paidBy);
			if (!balances.containsKey(paidTo)) {
				balances.put(paidTo, 0.0);
			}
			balances.put(paidTo, balances.get(paidTo) + split.getAmount());

			balances = balanceSheet.get(paidTo);
			if (!balances.containsKey(paidBy)) {
				balances.put(paidBy, 0.0);
			}
			balances.put(paidBy, balances.get(paidBy) - split.getAmount());
		}
	}

	public void showBalance(String userId) {
		boolean isEmpty = true;
		Map<String, Double> balances = balanceSheet.get(userId);
		for (Map.Entry<String, Double> userbalance : balances.entrySet()) {
			if (userbalance.getValue() != 0) {
				isEmpty = false;
				printBalance(userId, userbalance.getKey(), userbalance.getValue());
			}
		}
		if (isEmpty) {
			System.out.println("No balances");
		}
	}

	public void showBalances() {
		boolean isEmpty = true;
		for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
			for (Map.Entry<String, Double> userbalance : allBalances.getValue().entrySet()) {
				if (userbalance.getValue() > 0) {
					isEmpty = false;
					printBalance(allBalances.getKey(), userbalance.getKey(), userbalance.getValue());
				}
			}
		}
		if (isEmpty) {
			System.out.println("No balances");
		}
	}

	private void printBalance(String user1, String user2, double amount) {
		String user1Name = userMap.get(user1).getName();
		String user2Name = userMap.get(user2).getName();
		if (amount < 0) {
			System.out.println(user1Name + " owes " + user2Name + " : " + Math.abs(amount));
		} else if (amount > 0) {
			System.out.println(user2Name + " owes " + user1Name + " : " + Math.abs(amount));
		}
	}
}