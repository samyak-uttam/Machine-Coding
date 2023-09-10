package src.models.split;

import src.models.User;

public class ExactSplit extends Split {
	public ExactSplit(User user, double amount) {
		super(user);
		setAmount(amount);
	}
}