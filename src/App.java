import java.util.Scanner;


/*
    Author: Liron Ostrovsky 7/1/2021
    Course: Data Structures
    Assignment: Data structure implementation
*/
public class App {
    /*
        Main - entry to the program
        Algorigthm - Asks the user to enter the array bit size and the number of the hush functions to create the structure.
                     Then, it creates a new structure, and calls the test function - explained in the Structure class.
    */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter structure size");
        int structureSize = scanner.nextInt();
        System.out.println("Enter hash functions count");
        int hashFunctionsCount = scanner.nextInt();
        scanner.close();
        Structure s = new Structure(structureSize, hashFunctionsCount);
        s.testStructure();
    }
}
