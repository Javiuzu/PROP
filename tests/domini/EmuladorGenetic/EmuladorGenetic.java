package domini.EmuladorGenetic;

import exceptions.CodeOrCorrectionNull;
import exceptions.CorrectionIsInvalid;
import model.*;
import exceptions.CodeIsInvalid;

import java.util.Scanner;

public class EmuladorGenetic {
    public static void main(String[] args) {
        System.out.println("Input a difficulty e|n|h");
        Scanner sc = new Scanner(System.in);
        boolean gameCreated = false;
        Game g = new Game(false, Diff.NORMAL);
        while (!gameCreated) {
            String diff = sc.nextLine();
            if (diff.equalsIgnoreCase("e")) {
                g = new Game(false, Diff.EASY);
                gameCreated = true;
            }
            else if (diff.equalsIgnoreCase("n")) {
                g = new Game(false, Diff.NORMAL);
                gameCreated = true;
            }
            else if (diff.equalsIgnoreCase("h")) {
                g = new Game(false, Diff.HARD);
                gameCreated = true;
            }
        }
        System.out.println("Input secret code");
        boolean codeCreated = false;
        Code secretCode = new Code();
        Board b = g.getBoard();
        while (!codeCreated) {
            int scCode = sc.nextInt();
            secretCode.setCode(scCode);
            try {
                if (g.codeIsValid(secretCode)) {
                    codeCreated = true;
                    b.setSecretCode(secretCode);
                }
            } catch (CodeIsInvalid codeIsInvalid) {
                codeIsInvalid.printStackTrace();
                }
            System.out.println("Input a valid code");
            }
        Genetic gen = new Genetic(g);
        Code guess;
        guess = gen.codeBreakerTurn(null, null);
        try {
            b.addGuess(guess);
        } catch (CodeIsInvalid codeIsInvalid) {
            codeIsInvalid.printStackTrace();
        }
        Correction correction = guess.correct(secretCode);
        try {
            b.addCorrection(correction);
        } catch (CorrectionIsInvalid correctionIsInvalid) {
            correctionIsInvalid.printStackTrace();
        }

        while (!b.hasWon() && b.turnsDone() < 12) {
                System.out.println(
                        "Tried " + guess.getCode().toString() + ", got " + correction.getBlackPins() + " black pins and " +
                                correction.getWhitePins() + " white pins"
                );
                guess = gen.codeBreakerTurn(guess, correction);
                try {
                    b.addGuess(guess);
                } catch (CodeIsInvalid e) {
                    System.out.println("Code invalid returned by genetic function");
                }
                correction = guess.correct(secretCode);
                try {
                    b.addCorrection(correction);
                } catch (CorrectionIsInvalid e) {
                    System.out.println("Correction returned by correct wrong");
                }
                if (b.hasWon()) System.out.println("The ai guessed the code! " +guess.getCode().toString());

        }
        sc.close();
    }
}