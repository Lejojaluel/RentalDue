/**
 * Sub Class to contain a Single Family rental property
 *
 * @author Leroy Valencia
 */
public class SingleFamilyRental extends RentalProperty {
  public SingleFamilyRental(String typeOfProp, String idNumber, int numOfRooms) {
    super(typeOfProp, idNumber, numOfRooms); //inherits the super instance variables
  }

  /**
   * This is the calculateRentDue that will literally calculate the rent that is due for the Single
   * Family rentals. It uses the numbers that are provided in the isntructions
   *
   * @return the double that the rent is due depends on the num of rooms.
   */
  @Override
  public double calculateRentDue() {
    double result = 0;
    if (super.getTypeofProp().equals("S")) {
      switch (super.getNumOfRooms()) {
        case 1:
          return (900 * 0.04) + 900;
        case 2:
          return (1100 * 0.04) + 1100;
        case 3:
          return (1300 * 0.04) + 1300;
        default:
          System.err.print("Impossible room number in Single Family Rental");
          System.exit(1);
      }
    }
    return result; // return 0 this is unreachable.
  }
}
