import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that will calculate the rent and display it
 *
 * @author Leroy Valencia
 */
public class RentalDue {

  ArrayList<RentalProperty> rentApArr = new ArrayList<RentalProperty>();
  ArrayList<RentalProperty> rentSFArr = new ArrayList<RentalProperty>();

  /**
   * Default contructor for the RentalDue class.
   */
  public RentalDue() {
    populateArrays();
  }

  /**
   * This is the method that populates the arrays from the rentalDB.txt
   */
  public void populateArrays() {
    File file = new File("src/rentalDB.txt");
    String idNumber;
    String typeOfProp;
    int numOfRooms;

    //Try catch to catch the exception of not finding a file.
    try {
      Scanner sc = new Scanner(file); //scanner that opens the file and reads it
      while (sc.hasNext()) {
        //This will store the values formatted properly from the rentalDB into variables
        typeOfProp = sc.next();
        idNumber = sc.next();
        numOfRooms = sc.nextInt();
        //Add the information based on which type of property it is.
        if (typeOfProp.equals("S")) {
          rentSFArr.add(new SingleFamilyRental(typeOfProp, idNumber, numOfRooms));
        } else if (typeOfProp.equals("A")) {
          rentApArr.add(new ApartmentRental(typeOfProp, idNumber, numOfRooms));
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is the one that generates the output to the the Rental Summary.
   */
  public void outputCurrentRent() {
    System.out.println("\nSingle-Family Rental Summary:");
    System.out.println("House ID Number    # of Bedrooms    Rental Due");
    System.out.println("===============    =============    ==========");
    //This is the loop to generate each line of the Single Family Rental
    for (int i = 0; i < rentSFArr.size(); i++) {
      System.out.print(
          "    "
              + rentSFArr.get(i).getIdNumber()
              + "              "
              + rentSFArr.get(i).getNumOfRooms()
              + "           "
              + "$"
              + rentSFArr.get(i).calculateRentDue()
              + "\n");
    }
    System.out.println("\nApartment Rental Summary:");
    System.out.println("Apartment ID No.    # of Bedrooms    Rental Due");
    System.out.println("================    =============    ==========");
    //This is the loop to generate each line of the Apartment Rental
    for (int j = 0; j < rentApArr.size(); j++) {
      System.out.print(
          "    "
              + rentApArr.get(j).getIdNumber()
              + "               "
              + rentApArr.get(j).getNumOfRooms()
              + "           "
              + "$"
              + rentApArr.get(j).calculateRentDue()
              + "\n");
    }
  }
}
