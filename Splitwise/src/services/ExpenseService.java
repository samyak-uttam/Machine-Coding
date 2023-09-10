package src.services;

import src.models.User;
import src.models.expense.*;
import src.models.split.Split;
import src.models.split.PercentSplit;

import java.util.List;

public class ExpenseService {
	public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata) {
		switch (expenseType) {
			case EXACT:
				return new ExactExpense(amount, paidBy, splits, metadata);
			case PERCENT:
				for (Split split : splits) {
					PercentSplit percentSplit = (PercentSplit) split;
					split.setAmount((amount * percentSplit.getPercent()) / 100.0);
				}
				return new PercentExpense(amount, paidBy, splits, metadata);
			case EQUAL:
				int totalSplits = splits.size();
				double splitAmount = ((double) Math.round(amount * 100.0 / totalSplits)) / 100.0;
				for (Split split : splits) {
					split.setAmount(splitAmount);
				}
				splits.get(0).setAmount(amount - (totalSplits - 1) * splitAmount);
				return new EqualExpense(amount, paidBy, splits, metadata);
			default:
				return null;
		}
	}
}