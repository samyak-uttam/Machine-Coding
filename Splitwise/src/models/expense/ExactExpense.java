package src.models.expense;

import src.models.User;
import src.models.split.Split;
import src.models.split.ExactSplit;

import java.util.List;

public class ExactExpense extends Expense {
	public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata) {
		super(amount, paidBy, splits, metadata);
	}

	@Override
	public boolean validate() {
		for (Split split : getSplits()) {
			if (!(split instanceof ExactSplit)) {
				return false;
			}
		}

		double totalAmount = getAmount();
		double sumSplitAmount = 0.0;

		for (Split split : getSplits()) {
			ExactSplit exactSplit = (ExactSplit) split;
			sumSplitAmount += exactSplit.getAmount();
		}

		return sumSplitAmount == totalAmount;
	}
}