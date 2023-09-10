package src.models.expense;

import src.models.User;
import src.models.split.Split;
import src.models.split.EqualSplit;

import java.util.List;

public class EqualExpense extends Expense {
	public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata) {
		super(amount, paidBy, splits, metadata);
	}

	@Override
	public boolean validate() {
		for (Split split : getSplits()) {
			if (!(split instanceof EqualSplit)) {
				return false;
			}
		}
		return true;
	}
}