package src.services;

import java.util.Random;

public class DiceService {
	public static int roll() {
		return new Random().nextInt(6) + 1; // This game will have a six sided dice from 1 to 6 and will always give a random number on rolling it.
	}
}