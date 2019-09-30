/**
 * Super Class to contain a rental property
 *
 * @author Leroy Valencia
 */

/**
 * This is the payment interface that implements the method calculate rent to each sub class
 */
interface Payment {
  double calculateRentDue();
}
//Main class
public class RentalProperty implements Payment {
  private String typeOfProp;
  private int numOfRooms;
  private String idNumber;

  /**
   * This is the rental property super class that holds the values for any sub classes. This is also the main
   * constructor for the RentalProperty.
   *
   * @param typeofProp
   * @param idNumber
   * @param numOfRooms
   */
  public RentalProperty(String typeofProp, String idNumber, int numOfRooms) {
    this.idNumber = idNumber;
    this.typeOfProp = typeofProp;
    this.numOfRooms = numOfRooms;
  }

  /**
   * This is the implemented method from the interface. THis has jsut a return 0 because there is no calculations that
   * need to be done here.
   * @return 0
   */
  @Override
  public double calculateRentDue() {
    double result = 0;
    return result;
  }

  /**
   * Getters for the property information
   * @return
   */
  public String getIdNumber() {
    return idNumber;
  }

  public String getTypeofProp() {
    return typeOfProp;
  }

  public int getNumOfRooms() {
    return numOfRooms;
  }
}
