import java.util.Scanner;

/**
 * A class that starts a game of Double 
 * Dice. This class contains the program's 
 * main() method and is not meant to be 
 * instantiated.
 */
public class DoubleDice {
  /**
   * Starts the game of Double Dice.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);

    Die die1 = new Die();
    Die die2 = new Die();

    int money = 10000; // initialize the player's money to $100.00 (10,000 cents)

    Boolean continueGame = true;

    while (continueGame) {
      System.out.print("You have ");
      printMoney(money);
      System.out.println();

      int betAmount = getBetAmount(scnr, money);

      if (betAmount == 0) { // if the input is 0, then print a message and stop the game
        System.out.println();
        System.out.println("See you around, winner!");
        continueGame = false;
      } else {
        die1.roll();
        die2.roll();

        System.out.println("You rolled a " + die1.toString() + " and a " + die2.toString());

        if (die1.equals(die2)) {
          System.out.print("You win ");
          printMoney(betAmount);
          money += betAmount;
        } else {
          System.out.printf("You lose ");
          printMoney(betAmount);
          money -= betAmount;
        }

        System.out.println(); // print a newline after the win or lose message

        System.out.println();

        if (money == 0) { // if the user is out of money, print a message and stop the while loop
          System.out.println("You are out of money!");
          System.out.println("Better luck next time");
          continueGame = false;
        }
      }
    }
  }

  /**
   * Retrieves a bet amount from the user.
   *
   * @param  scnr  the Scanner object
   * @param  money the money that the user has (used to check if the input is greater than the cash the user has)
   * @return       the input bet amount
   */
  public static int getBetAmount(Scanner scnr, int money) {
    Boolean valid;
    int parsed;

    do {
      parsed = 0;
      valid = true;

      System.out.print("How much would you like to bet (Enter 0 to quit)? ");

      String betRaw = scnr.next();

      // if the input starts with a dollar sign, remove it
      if (betRaw.charAt(0) == '$') {
        betRaw = betRaw.substring(1);
      }

      try {
        // attempt to parse the input
        double doubleParsed = Double.parseDouble(betRaw);

        // if the input has 2 or less decimal places, parse the input--otherwise, mark it as invalid
        int decimalIndex = betRaw.indexOf(".");
        if (
          decimalIndex == -1 ||
          betRaw.substring(decimalIndex+1).length() <= 2
        ) {
          parsed = (int)(doubleParsed*100.0);
        } else {
          valid = false;
        }
      } catch (NumberFormatException exception) {
        valid = false; // if there is a NumberFormatException thrown while parsing, then mark it as invalid input
      }

      /*
        if the input isn't already invalid and it's less than 0, 
        greater than the player's money, or has more than 2 decimal 
        places, then mark it as invalid input
      */
      if (
        valid && (
          parsed < 0 ||
          parsed > money
        )
      ) {
        valid = false;
      }

      // if the input is marked as invalid, print an error message before re-running the while loop
      if (!valid) {
        System.out.print("Invalid amount; input must be between ");
        printMoney(0);
        System.out.print(" and your current money (");
        printMoney(money);
        System.out.println(")");
      }
    } while (!valid);

    return parsed;
  }

  /**
   * Outputs the specified double as a 
   * formatted dollar amount.
   *
   * @param amount the amount to output
   */
  public static void printMoney(int amount) {
    System.out.printf("$%.2f", amount/100.0);
  }
}
