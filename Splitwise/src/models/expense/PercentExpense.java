package src.models.expense;

import src.models.User;
import src.models.split.Split;
import src.models.split.PercentSplit;

import java.util.List;

public class PercentExpense extends Expense {
	public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata) {
		super(amount, paidBy, splits, metadata);
	}

	@Override
	public boolean validate() {
		for (Split split : getSplits()) {
			if (!(split instanceof PercentSplit)) {
				return false;
			}
		}

		double totalPercent = 100.0;
		double sumSplitPercent = 0.0;

		for (Split split : getSplits()) {
			PercentSplit percentSplit = (PercentSplit) split;
			sumSplitPercent += percentSplit.getPercent();
		}

		return sumSplitPercent == totalPercent;
	}
}