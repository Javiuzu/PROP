package domini.EmuladorFiveGuess;

import java.util.Scanner;

import domini.*;
import exceptions.CodeAlreadyUsed;
import exceptions.CodeOrCorrectionNull;
import model.*;

//VictorGuillem
public class EmuladorFGRandom {
	public static void main (String[] args) {
		System.out.println("Input Game Difficulty e|n|h");
		Scanner sc= new Scanner(System.in);
		Boolean correctinput = false;
		Game g = new Game(false, Diff.EASY);
		while(!correctinput) {
			String A = sc.nextLine();
			correctinput = true;
			if(A.equalsIgnoreCase("e")) g = new Game(false, Diff.EASY);
			else if (A.equalsIgnoreCase("n")) g = new Game(false, Diff.NORMAL);
			else if (A.equalsIgnoreCase("h")) g = new Game(false, Diff.HARD);
			else correctinput = false;
			if (!correctinput) System.out.println("Input Game Difficulty e|n|h");
		}
		System.out.println("Input Secret Code");
		correctinput = false;
		Integer secret;
		Code secretCode = new Code();
		Board b = g.getBoard();
		while(!correctinput) {
			secret = sc.nextInt();
			secretCode = new Code(secret);
			correctinput = true;
			if(g.codeIsValid(secretCode)) 
				b.setSecretCode(secretCode);
			else correctinput = false;
			if (!correctinput) System.out.println("Input Valid Secret Code for the chosen difficulty");
		}
		System.out.println("Playing game on difficulty " + g.getDifficulty() + " with secret code " + secretCode.getCode().toString());
		FiveGuess fg = new FiveGuess(g);
		Code nextGuess = new Code();
		try {
			nextGuess = fg.codeBreakerTurn(null, null);
		} catch(Exception e) {
			System.out.println("This should never happen");
		}
		b.addGuess(nextGuess);
		Correction correction = nextGuess.correct(secretCode);
		b.addCorrection(correction);
		try {
			while (!b.hasWon() && ! (b.turnsDone()== 12)) {
				System.out.println("Tried " + nextGuess.getCode().toString() + ", got correction " + correction.getBlackPins() + "B " + correction.getWhitePins()+"W" );
					nextGuess=fg.codeBreakerTurn(nextGuess, correction); // CodeOrCorrectionNull, CodeAlreadyUsed
					correction = nextGuess.correct(secretCode);
					b.addGuess(nextGuess); b.addCorrection(correction);
			
			}
			if (b.hasWon()) System.out.println("AI won by guessing " + nextGuess.getCode());
			else System.out.println("AI Lost by guessing  " + nextGuess.getCode());
		} catch (CodeOrCorrectionNull e) {
			System.out.println("This should never happen, but one was null");
		} catch (CodeAlreadyUsed e) {
			System.out.println("This should never happen, code was already used");
		}
		sc.close();
	}
}