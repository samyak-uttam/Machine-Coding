package src;

import src.models.User;
import src.models.split.Split;
import src.models.split.EqualSplit;
import src.models.split.PercentSplit;
import src.models.split.ExactSplit;
import src.models.expense.ExpenseType;
import src.services.ExpenseManager;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.*;

public class Driver {
	public static void main(String args[]) throws FileNotFoundException {
		try {
			ExpenseManager expenseManager = new ExpenseManager();

			expenseManager.addUser(new User("u1", "User1", "user1@gmail.com", "9988776644"));
			expenseManager.addUser(new User("u2", "User2", "user2@gmail.com", "9988776633"));
			expenseManager.addUser(new User("u3", "User3", "user3@gmail.com", "9988776622"));
			expenseManager.addUser(new User("u4", "User4", "user4@gmail.com", "9988776611"));

			Scanner scanner = new Scanner(new File("../input.txt"));
			while (scanner.hasNextLine()) {
				String command = scanner.nextLine();
				String[] commands = command.split(" ");
				String commandType = commands[0];

				switch (commandType) {
					case "SHOW":
						if (commands.length == 1) {
							expenseManager.showBalances();
						} else {
							expenseManager.showBalance(commands[1]);
						}
						break;
					case "EXPENSE":
						String paidBy = commands[1];
						Double amount = Double.parseDouble(commands[2]);
						int noOfUsers = Integer.parseInt(commands[3]);
						String expenseType = commands[4 + noOfUsers];
						List<Split> splits = new ArrayList<Split>();
						switch (expenseType) {
							case "EQUAL":
								for (int i = 0; i < noOfUsers; i++) {
									splits.add(new EqualSplit(expenseManager.getUserMap().get(commands[4 + i])));
								}
								expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits, null);
								break;
							case "EXACT":
								for (int i = 0; i < noOfUsers; i++) {
									splits.add(new ExactSplit(expenseManager.getUserMap().get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
								}
								expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits, null);
								break;
							case "PERCENT":
								for (int i = 0; i < noOfUsers; i++) {
									splits.add(new PercentSplit(expenseManager.getUserMap().get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
								}
								expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits, null);
								break;
							default:
								break;
						}
						break;
					default:
						break;
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
	}
}