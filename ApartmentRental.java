/**
 * Sub Class to contain an Apartment rental property
 *
 * @author Leroy Valencia
 */
public class ApartmentRental extends RentalProperty {
  public ApartmentRental(String typeOfProp, String idNumber, int numOfRooms) {
    super(typeOfProp, idNumber, numOfRooms);
  }

  /**
   * This is the calculateRentDue that will literally calculate the rent that is due for the Apartment
   * rentals. It uses the numbers that are provided in the instructions.
   *
   * @return the double that the rent is due depends on the num of rooms.
   */
  @Override
  public double calculateRentDue() {
    double result = 0;
    if (super.getTypeofProp().equals("A")) {
      switch (super.getNumOfRooms()) {
        case 1:
          return (600 * 0.08) + 600;
        case 2:
          return (800 * 0.08) + 800;
        default:
          System.err.print("Impossible rent in Apartment Rental");
          System.exit(1);
      }
    }
    return result; // This is unreachable so return 0
  }
}
