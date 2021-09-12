import java.util.Random;

/**
 * A class that represents a six-sided die.
 */
public class Die {
  public int value;
  private Random randGen;

  /**
   * Constructs the die.
   */
  public Die() {
    this.value = 0;
    this.randGen = new Random();
  }

  /**
    * Simulates a roll of the die.
    *
    * @return the value of the die after the roll
    */
  public int roll() {
    this.value = randGen.nextInt(6)+1; // here nextInt() will return a number from 0 inclusive to 6 exclusive--to fix this, add one to the number generated
    return this.value;
  }

  /**
    * Compares this die with another die to see if their sides are equal.
    *
    * @param  die2 the die to compare this die with
    * @return      whether or not the dice are equal
    */
  public Boolean equals(Die die2) {
    return this.value == die2.value;
  }

  /**
   * Converts the die to a String representation of the showing side.
   *
   * @return the String representation of the die's side
   */
  @Override
  public String toString() {
    String side;
    switch (this.value) {
      case 1:
        side = "one";
        break;
      case 2:
        side = "two";
        break;
      case 3:
        side = "three";
        break;
      case 4:
        side = "four";
        break;
      case 5:
        side = "five";
        break;
      case 6:
        side = "six";
        break;
      default:
        side = "unknown";
        break;
    }
    return side;
  }
}
