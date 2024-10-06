// Made by: Jed Marco S. Mendizabal, in submission as part of the ECC Machine Problem Exercise 2
// Main file
package com.exist;

import java.util.Scanner; // Imported Scanner class used for capturing user input from the console
import java.util.InputMismatchException; // Import to handle invalid input in the menu

public class Exec2 {
    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in); // Scanner variable initialization
        boolean isDone = false; // Main loop control variable
        int choice = -1; // Choice variable for the menu
        Functionalities2 funcs = new Functionalities2(); // Object "funcs" initialization

        System.out.println("Java program 2-Dimensional Array random ASCII character generator");

        // Generate the initial table
        funcs.generate();

        // Main program loop
        while (!isDone) {
            // Inner menu loop to handle user operations
            boolean innerLoop = true;
            while (innerLoop) {
                try {
                    System.out.println("Welcome to the random ASCII character generator!");
                    System.out.println("1. Search");
                    System.out.println("2. Edit");
                    System.out.println("3. Print");
                    System.out.println("4. Reset");
                    System.out.println("5. Sort Table");
                    System.out.println("6. Add Column");
                    System.out.println("7. Save Table to File");
                    System.out.println("8. Load Table from File");
                    System.out.println("9. Exit");
                    System.out.print("Enter your menu choice (1-9): ");
                    choice = myInput.nextInt();

                    switch (choice) {
                        case 1:
                            funcs.search();
                            break;

                        case 2:
                            funcs.edit();
                            break;

                        case 3:
                            funcs.print();
                            break;

                        case 4:
                            funcs.reset();
                            break;

                        case 5:
                            funcs.sort();
                            break;

                        case 6:
                            funcs.addColumn();
                            break;

                        case 7:
                            funcs.saveToFile();
                            break;

                        case 8:
                            funcs.loadFromFile();
                            break;

                        case 9:
                            funcs.exit(); // This will close the Scanner internally
                            innerLoop = false; // Break the inner loop
                            isDone = true; // Mark the main loop as done
                            break;

                        default:
                            System.out.println("The choices are only 1-9. Please try again.");
                            System.out.println();
                            break;
                    }
                } catch (InputMismatchException e) {
                    // Error handling for invalid input (non-integer, special characters)
                    System.out.println("Invalid input. Please enter an integer value between 1 and 9.");
                    System.out.println();
                    myInput.next(); // Clear the invalid input to avoid an infinite loop
                }
            }
        }
    }
}