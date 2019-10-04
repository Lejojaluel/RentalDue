/**
 * Class that will calculate the rent and display it
 *
 * @author Leroy Valencia
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class RentalDue {
  //Main arrays used to keep track of the Properties.
  ArrayList<RentalProperty> rentApArr = new ArrayList<RentalProperty>();
  ArrayList<RentalProperty> rentSFArr = new ArrayList<RentalProperty>();

  /** Default constructor for the RentalDue class. */
  public RentalDue() {
    populateArrays();
  }

  /**
   * This is the method that populates the arrays from the rentalDB.txt
   */
  public void populateArrays() {
    File file = new File("src/rentalDB.txt"); //creates fuek
    String idNumber,typeOfProp;
    int numOfRooms;
    try { //This is necessary when dealing with File in Scanner.
      Scanner sc = new Scanner(file); // scanner that opens the file and reads it
      while (sc.hasNext()) {
        // This will store the values formatted properly from the rentalDB into variables
        typeOfProp = sc.next();
        idNumber = sc.next();
        numOfRooms = sc.nextInt();
        // Add the information based on which type of property it is.
        if (typeOfProp.equals("S")) {
          rentSFArr.add(new SingleFamilyRental(typeOfProp, idNumber, numOfRooms));
        } else if (typeOfProp.equals("A")) {
          rentApArr.add(new ApartmentRental(typeOfProp, idNumber, numOfRooms));
        }
      }
    } catch (FileNotFoundException e) { //Catch file not found exception
      e.printStackTrace();
    }
  }

  /**
   * This method calls the other sort methods in the right order. Order is very important here, it also
   * passes down the givenArr.
   */
  public void sortArray(ArrayList<RentalProperty> givenArr) {
    sortNumOfBeds(givenArr);
    sortID(givenArr);
  }

  /**
   * The method that sorts the array given by the num of beds.
   * @param givenArr The array that you want sorted by NumOfBeds.
   */
  public void sortNumOfBeds(ArrayList<RentalProperty> givenArr) {
    /*
    This is a for loop that sorts through the number of beds. Pretty simple sort loop*/
    for (int i = 0; i < givenArr.size(); i++) {
      for (int j = 0; j < givenArr.size(); j++) {
        if (givenArr.get(i).getNumOfRooms() < givenArr.get(j).getNumOfRooms()) { // If statement gets both of rooms
          swap(i, j, givenArr);
          //System.out.println("Sorted by numOfRooms");
        }
      }
    }
  }

  /**
   * I separated the two methods for the sorts because it looked neater then being in one big method.
   * This will need to be called after the sortBeds because its a secondary sort. That needs to be sorted by the
   * num of beds first.
   *
   * It just loops through the max number of beds because this is the different sections it needs to sort. And also it sums
   * the total occurrences in that num of bed. Then it sorts it with a bubble sort and then does it all over
   * for the next bed number
   *
   * @param givenArr The array that you want sorted by ID.
   */
  public void sortID(ArrayList<RentalProperty> givenArr) {
    int count,n;
    int countTotal = 0;
    for (int a = 1; a < maxNumBeds(givenArr) + 1; a++) { // loop the max number of beds
      //Count all times there is an entry that is equal to the above loop count. 1,2,3
      count = 0;
      for (int i = 0; i < givenArr.size(); i++) {
        if (givenArr.get(i).getNumOfRooms() == a) {
          count++;
        }
      }
      //After the count add count total and then set it to n. A vital part that needs to be here for it to work.
      n = count + countTotal;
      if (n != 1 || n != 0) { //Only runs if it finds a value with that number of beds
        //A Simple bubble sort that is fit to work with my classes.
        for (int i = 0; i < n - 1; i++) {
          for (int j = 0 + countTotal; j < (n - i - 1); j++) {
            //This only gets the numbers at the end of the ID to be able to sort the array.
            String tempJ = givenArr.get(j).getIdNumber().replaceAll("[^0-9]", "");
            String tempJ1 = givenArr.get(j + 1).getIdNumber().replaceAll("[^0-9]", "");
            //Comparison of the two
            if (Integer.parseInt(tempJ)
                > Integer.parseInt(tempJ1)) { // Compares the two int values of the string
              swap(j, j + 1, givenArr); //swaps the positions
              //System.out.println("Sorted by ID..");
            }
          }
        }
      }
      countTotal += count; //To be able to keep checking the next set of numofbeds.
    }
  }

  /**
   * I seperated this method out to prevent copy paste code. It just swaps the array positions.
   *
   * @param i first position
   * @param j second position
   * @param givenArr array to swap.
   */
  public void swap(int i, int j, ArrayList<RentalProperty> givenArr) {
    RentalProperty temp = givenArr.get(i); // sets temporary position
    givenArr.set(i, givenArr.get(j)); // swaps the two
    givenArr.set(j, temp); // sets the old one with the temp position
  }

  /**
   * Returns the max number of beds so the loop in sort doesn't go over the limit. I could have set this
   * to a magic number but to keep the program dynamic I did this.
   *
   * @param givenArr The array you want to look in for MaxNumofBeds
   * @return the Max number of beds in the array.
   */
  public int maxNumBeds(ArrayList<RentalProperty> givenArr) {
    int max = givenArr.get(0).getNumOfRooms();
    for (int i = 0; i < givenArr.size(); i++) {
      if (givenArr.get(i).getNumOfRooms() > max) {
        max = givenArr.get(i).getNumOfRooms();
      }
    }
    return max;
  }

  /**
   * This method is the one that generates the output to the the Rental Summary.
   */
  public void outputCurrentRent() {
    System.out.println("\nSingle-Family Rental Summary:");
    System.out.println("House ID Number    # of Bedrooms    Rental Due");
    System.out.println("===============    =============    ==========");
    if (rentSFArr.size() > 0) {
      sortArray(rentSFArr);
    }
    // This is the loop to generate each line of the Single Family Rental
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
    if (rentSFArr.size() > 0) {
      sortArray(rentApArr);
    }
    // This is the loop to generate each line of the Apartment Rental
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
