// Made by: Jed Marco S. Mendizabal, in submission as part of the ECC Machine Problem Exercise 2
// Class file for Exec2.java
package com.exist;
import org.apache.commons.lang3.StringUtils;
import java.util.Scanner; // To handle user input
import java.util.Random; // To generate random ASCII characters
import java.util.Map; // To use the Map interface for using key-value pairs
import java.util.List; // To use the List interface for ordered collection of elements
import java.util.ArrayList; // To use the ArrayList class which implements the List interface
import java.util.LinkedHashMap; // To use the LinkedHashMap class which implements the Map interface
import java.util.Collections; // To use utility methods to operate on collections (e.g., sorting)
import java.util.Comparator; // To use Comparator class interface to define custom sorting logic
import java.io.BufferedReader; // To read text from a character-input stream
import java.io.BufferedWriter; // To write text to a character-output stream
import java.io.FileReader; // To read data from a file
import java.io.FileWriter; // To write data to a file
import java.io.IOException; // To handle input/output exceptions
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;


public class Functionalities2 {
	private ArrayList<LinkedHashMap<String, String>> table;
    private Scanner myInput;

    public Functionalities2() {
    	myInput = new Scanner(System.in);
    	table = new ArrayList<>();
    }

	// Method to generate a table with key-value pairs using LinkedHashMap
    public void generate() {
        System.out.print("Enter the number of rows: ");
        int rows = myInput.nextInt();
        System.out.print("Enter the number of columns: ");
        int columns = myInput.nextInt();

        table.clear(); // Clear the table before generating a new one
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        // Populate the table with key-value pairs
        for (int i = 0; i < rows; i++) {
            LinkedHashMap<String, String> row = new LinkedHashMap<>();
            for (int j = 0; j < columns; j++) {
                // Generate a random 3-character key and value
                String key = generateRandomString(random, characters, 3);
                String value = generateRandomString(random, characters, 3);

                // Store the key-value pair in the row
                row.put(key, value);
            }
            table.add(row); // Add the row to the table
        }

        System.out.println("Table generated successfully!");
    }

    // Helper method to generate a random string of given length
    private String generateRandomString(Random random, String characters, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int k = 0; k < length; k++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }


	// Search method to find characters in the key-value pairs
	public void search() {
	    System.out.println("Search Options:");
	    System.out.println("1. Search by Key");
	    System.out.println("2. Search by Value");
	    System.out.println("3. Search by Both");
	    
	    int option = 0;
	    
	    // Ensure valid input for search options
	    while (true) {
	        System.out.print("Choose an option (1/2/3): ");
	        if (myInput.hasNextInt()) {
	            option = myInput.nextInt();
	            if (option >= 1 && option <= 3) {
	                break;
	            } else {
	                System.out.println("Error: Please enter a valid option (1, 2, or 3).");
	            }
	        } else {
	            System.out.println("Error: Invalid input. Please enter a number (1, 2, or 3).");
	            myInput.next(); // Clear invalid input
	        }
	    }
	    
	    // Ensure valid input for the search term
	    String searchTerm;
	    while (true) {
	        System.out.print("Enter the search term (1 to 3 characters): ");
	        searchTerm = myInput.next();
	        if (StringUtils.isNotBlank(searchTerm) && searchTerm.length() >= 1 && searchTerm.length() <= 3) {
	            break;
	        } else {
	            System.out.println("Error: Search term must be between 1 to 3 characters and cannot be blank.");
	        }
	    }
	
	    boolean found = false;
	    System.out.println("Search Results:");
	
	    // Iterate through each row (LinkedHashMap) in the table
	    for (int i = 0; i < table.size(); i++) {
	        LinkedHashMap<String, String> row = table.get(i);
	        int j = 0; // Column index
	
	        // Iterate through each key-value pair in the row
	        for (Map.Entry<String, String> entry : row.entrySet()) {
	            String key = entry.getKey();
	            String value = entry.getValue();
	
	            boolean keyMatch = option == 1 || option == 3;
	            boolean valueMatch = option == 2 || option == 3;
	
	            // Check for matches based on the user's choice
	            if (keyMatch && StringUtils.contains(key, searchTerm)) {
	                found = true;
	                System.out.println("[" + i + "," + j + "] - Character '" + searchTerm + "' found in Key: " + key);
	            }
	            if (valueMatch && StringUtils.contains(value, searchTerm)) {
	                found = true;
	                System.out.println("[" + i + "," + j + "] - Character '" + searchTerm + "' found in Value: " + value);
	            }
	            if ((option == 3) && StringUtils.contains(key + ":" + value, searchTerm)) {
	                found = true;
	                System.out.println("[" + i + "," + j + "] - Found in combined Key-Value: " + key + ":" + value);
	            }
	
	            j++; // Increment column index
	        }
	    }
	
	    if (!found) {
	        System.out.println("No occurrences of '" + searchTerm + "' found in the table.");
	    }
	
	    System.out.println();
	}
	

	public void edit() {
	    System.out.println("2. Edit");
	
	    try {
	        // Get the row index
	        System.out.print("Enter the row index to edit (starting from 1): ");
	        if (!myInput.hasNextInt()) {
	            System.out.println("Error: Invalid input. Row index must be an integer.");
	            myInput.next(); // Consume the invalid input
	            return;
	        }
	        int editRow = myInput.nextInt() - 1;
	
	        if (editRow < 0 || editRow >= table.size()) {
	            System.out.println("Error: Invalid row index. Please enter a valid row.");
	            return;
	        }
	
	        LinkedHashMap<String, String> row = table.get(editRow);
	
	        // Get the column index
	        System.out.print("Enter the column index to edit (starting from 1): ");
	        if (!myInput.hasNextInt()) {
	            System.out.println("Error: Invalid input. Column index must be an integer.");
	            myInput.next(); // Consume the invalid input
	            return;
	        }
	        int editCol = myInput.nextInt() - 1;
	
	        if (editCol < 0 || editCol >= row.size()) {
	            System.out.println("Error: Invalid column index. Please enter a valid column.");
	            return;
	        }
	
	        // Find the key-value pair at the specified column
	        String currentKey = "";
	        String currentValue = "";
	        int currentIndex = 0;
	
	        for (Map.Entry<String, String> entry : row.entrySet()) {
	            if (currentIndex == editCol) {
	                currentKey = entry.getKey();
	                currentValue = entry.getValue();
	                break;
	            }
	            currentIndex++;
	        }
	
	        // Debug output
	        System.out.println("Current Key: " + currentKey);
	        System.out.println("Current Value: " + currentValue);
	
	        // Select what to edit: key, value, or both
	        System.out.println("What would you like to edit?");
	        System.out.println("1. Key");
	        System.out.println("2. Value");
	        System.out.println("3. Both");
	
	        int choice = myInput.nextInt();
	        String newKey = currentKey;
	        String newValue = currentValue;
	
	        switch (choice) {
	            case 1:
	                System.out.print("Enter new key (3 characters): ");
	                newKey = myInput.next();
	                if (!StringUtils.equals(newKey, StringUtils.stripToNull(newKey)) || newKey.length() != 3) {
	                    System.out.println("Error: The key must be exactly 3 characters.");
	                    return;
	                }
	                break;
	
	            case 2:
	                System.out.print("Enter new value (3 characters): ");
	                newValue = myInput.next();
	                if (!StringUtils.equals(newValue, StringUtils.stripToNull(newValue)) || newValue.length() != 3) {
	                    System.out.println("Error: The value must be exactly 3 characters.");
	                    return;
	                }
	                break;
	
	            case 3:
	                System.out.print("Enter new key (3 characters): ");
	                newKey = myInput.next();
	                System.out.print("Enter new value (3 characters): ");
	                newValue = myInput.next();
	
	                if (!StringUtils.equals(newKey, StringUtils.stripToNull(newKey)) || newKey.length() != 3 ||
	                    !StringUtils.equals(newValue, StringUtils.stripToNull(newValue)) || newValue.length() != 3) {
	                    System.out.println("Error: Both key and value must be exactly 3 characters.");
	                    return;
	                }
	                break;
	
	            default:
	                System.out.println("Error: Invalid choice.");
	                return;
	        }
	
	        // Modify the entry while retaining its original position
	        LinkedHashMap<String, String> newRow = new LinkedHashMap<>();
	        currentIndex = 0;
	
	        for (Map.Entry<String, String> entry : row.entrySet()) {
	            if (currentIndex == editCol) {
	                newRow.put(newKey, newValue);  // Add the modified entry at the same position
	            } else {
	                newRow.put(entry.getKey(), entry.getValue());  // Add other entries as they are
	            }
	            currentIndex++;
	        }
	
	        table.set(editRow, newRow);  // Update the row in the table
	        System.out.println("Edit successful. Updated Row: " + newRow);
	
	    } catch (Exception e) {
	        System.out.println("Error: An unexpected error occurred. Returning to the menu.");
	        myInput.nextLine(); // Consume any remaining input
	    }
	}
	
	
		
	public void sort() {
	    System.out.println("5. Sort Rows");
	
	    // Get sorting order (ascending or descending)
	    int orderChoice = getSortingOrder();
	
	    // Get row index to sort
	    int rowToSort = getRowIndexToSort();
	
	    // Retrieve the row to sort
	    LinkedHashMap<String, String> row = table.get(rowToSort);
	
	    if (row.isEmpty()) {
	        System.out.println("The selected row is empty and cannot be sorted.");
	        return;
	    }
	
	    // Sort the entries of the row based on key-value pairs
	    sortRowEntries(row, orderChoice);
	    System.out.println("Row sorted successfully.");
	}
	
	private int getSortingOrder() {
	    int orderChoice;
	    while (true) {
	        System.out.print("Enter sorting order (1 for ascending, 2 for descending): ");
	        if (myInput.hasNextInt()) {
	            orderChoice = myInput.nextInt();
	            if (orderChoice == 1 || orderChoice == 2) {
	                return orderChoice; // Return valid choice
	            } else {
	                System.out.println("Invalid choice. Please enter 1 for ascending or 2 for descending.");
	            }
	        } else {
	            System.out.println("Invalid input. Please enter an integer value.");
	            myInput.next();  // Discard invalid input
	        }
	    }
	}
	
	private int getRowIndexToSort() {
	    int rowToSort;
	    while (true) {
	        System.out.print("Enter the row index to sort (starting from 1): ");
	        if (myInput.hasNextInt()) {
	            rowToSort = myInput.nextInt() - 1;
	            if (rowToSort >= 0 && rowToSort < table.size()) {
	                return rowToSort; // Return valid row index
	            } else {
	                System.out.println("Error: Invalid row index. Please enter a valid row.");
	            }
	        } else {
	            System.out.println("Invalid input. Please enter an integer value.");
	            myInput.next();  // Discard invalid input
	        }
	    }
	}

	private void sortRowEntries(LinkedHashMap<String, String> row, int orderChoice) {
	    List<Map.Entry<String, String>> entries = new ArrayList<>(row.entrySet());
	
	    // Capture the final value of orderChoice for comparator
	    final int finalOrderChoice = orderChoice;
	
	    // Comparator for sorting entries based on concatenated key-value pairs
	    Comparator<Map.Entry<String, String>> comparator = (entry1, entry2) -> {
	        String pair1 = entry1.getKey() + entry1.getValue();
	        String pair2 = entry2.getKey() + entry2.getValue();
	        return finalOrderChoice == 1 ? pair1.compareTo(pair2) : pair2.compareTo(pair1);
	    };
	
	    entries.sort(comparator); // Sort entries
	
	    // Clear the original row and populate it with sorted entries
	    row.clear();
	    for (Map.Entry<String, String> entry : entries) {
	        row.put(entry.getKey(), entry.getValue());
	    }
	}

		
	public void addColumn() {
	    System.out.println("6. Add Column");
	
	    // Prompt user to choose the row to add the column value
	    int rowIndex = getRowIndexForColumn();
	
	    // Get unique key-value pair from user
	    String[] keyValue = getUniqueKeyValue();
	
	    // Add or replace the key-value pair in the selected row
	    LinkedHashMap<String, String> selectedRow = table.get(rowIndex);
	    selectedRow.put(keyValue[0], keyValue[1]);
	
	    // Ensure all other rows have placeholders if they don't have this key
	    addPlaceholdersToOtherRows(rowIndex, keyValue[0], selectedRow);
	
	    System.out.println("Column added successfully.\n");
	}

	private int getRowIndexForColumn() {
	    System.out.print("Enter the row index to add a column (starting from 1): ");
	    int rowIndex = myInput.nextInt() - 1;
	
	    if (rowIndex < 0 || rowIndex >= table.size()) {
	        System.out.println("Error: Invalid row index. Please enter a valid row.");
	        return -1; // Return -1 to indicate an invalid index
	    }
	    return rowIndex;
	}

	private String[] getUniqueKeyValue() {
	    String newKey = "";
	    String newValue = "";
	
	    while (true) {
	        // Prompt user to enter the new key-value pair
	        System.out.print("Enter the new key-value pair (format: key:value): ");
	        String inputValue = myInput.next();
	
	        // Split the input into key and value
	        String[] keyValue = inputValue.split(":");
	        if (keyValue.length != 2 || keyValue[0].length() != 3 || keyValue[1].length() != 3) {
	            System.out.println("Error: Invalid input format. Please use the format 'key:value' with 3-character key and value.");
	            continue;
	        }
	
	        newKey = keyValue[0];
	        newValue = keyValue[1];
	
	        // Check for duplicate keys in the table
	        if (isDuplicateKey(newKey)) {
	            System.out.println("Error: Duplicate key detected. Please enter a unique key.");
	        } else {
	            return keyValue; // Return valid key-value pair
	        }
	    }
	}

	private boolean isDuplicateKey(String key) {
	    for (LinkedHashMap<String, String> row : table) {
	        if (row.containsKey(key)) {
	            return true; // Duplicate key found
	        }
	    }
	    return false; // No duplicate key
	}

	private void addPlaceholdersToOtherRows(int rowIndex, String newKey, LinkedHashMap<String, String> selectedRow) {
	    for (int i = 0; i < table.size(); i++) {
	        if (i != rowIndex) {
	            LinkedHashMap<String, String> otherRow = table.get(i);
	            if (!otherRow.containsKey(newKey)) {
	                // Check if any existing key-value pair precedes this position in the row
	                boolean shouldAddPlaceholder = shouldAddPlaceholderToRow(otherRow, selectedRow);
	                if (shouldAddPlaceholder) {
	                    otherRow.put("null", "non-existent");
	                }
	            }
	        }
	    }
	}

	private boolean shouldAddPlaceholderToRow(LinkedHashMap<String, String> otherRow, LinkedHashMap<String, String> selectedRow) {
	    for (String key : selectedRow.keySet()) {
	        if (otherRow.containsKey(key) && !otherRow.containsKey(selectedRow.keySet())) {
	            return true; // Indicate that a placeholder should be added
	        }
	    }
	    return false; // No need for a placeholder
	}



	// Method to save the current table state to a file
	public void saveToFile() {
	    System.out.println("7. Save Table to File");
	
	    // Define the target directory as '/classes'
	    String targetDirectory = System.getProperty("user.dir") + "/classes";
	
	    // Create a File object for the directory and ensure it exists
	    File directory = new File(targetDirectory);
	    if (!directory.exists()) {
	        directory.mkdirs(); // Create directories if they do not exist
	    }
	
	    // Prompt the user for the filename to save in the target directory
	    String filename = saveFilename();
	
	    // Construct the full path for the file to save
	    String filePath = targetDirectory + "/" + filename;
	
	    // Ensure the file has a .txt extension if it doesn't already
	    if (!filePath.endsWith(".txt")) {
	        filePath += ".txt";
	    }
	
	    // Save the table data to the specified file
	    if (!saveTableToFile(filePath)) {
	        System.out.println("Error saving the table. Please try again.");
	    } else {
	        System.out.println("Table saved successfully to " + filePath + ".\n");
	    }
	}

	private String saveFilename() {
	    System.out.print("Enter the filename to save the table (e.g., table.txt): ");
	    return myInput.next(); // Get the filename input from the user
	}

	private boolean saveTableToFile(String filename) {
	    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
	        // Write the number of rows
	        writer.write("Rows: " + table.size());
	        writer.newLine();
	        
	        // Write the table data row by row
	        for (LinkedHashMap<String, String> row : table) {
	            writeRowToFile(writer, row);
	        }
	        
	        return true; // Indicate success
	    } catch (IOException e) {
	        System.out.println("Error saving the table: " + e.getMessage());
	        return false; // Indicate failure
	    }
	}

	private void writeRowToFile(BufferedWriter writer, LinkedHashMap<String, String> row) throws IOException {
	    for (Map.Entry<String, String> entry : row.entrySet()) {
	        // Write each key-value pair in the format "key:value"
	        writer.write(entry.getKey() + ":" + entry.getValue());
	        writer.write(" "); // Separate entries in the same row
	    }
	    writer.newLine(); // Move to the next line for the next row
	}

	
	// Method to load a table state from a file
	public void loadFromFile() {
	    System.out.println("8. Load Table from File");
	    
	    // Prompt user for the file name
	    String filename = loadFilename();
	
	    // Load the table data from the specified file in the resources directory
	    if (!loadTableFromFile(filename)) {
	        System.out.println("Error loading the table. Please try again.");
	    }
	}

	private String loadFilename() {
	    System.out.print("Enter the filename to load the table (e.g., table.txt): ");
	    return myInput.next(); // Get the filename input from the user
	}

	private boolean loadTableFromFile(String filename) {
	    // Use the class loader to find the resource
	    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
	    
	    if (inputStream == null) {
	        System.out.println("Error loading the table: " + filename + " (File not found in resources)");
	        return false; // Indicate failure
	    }
	
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
	        // Validate the file format
	        int numRows = validateFileFormat(reader);
	        if (numRows <= 0) {
	            return false; // Error messages are handled in validateFileFormat
	        }
	
	        ArrayList<LinkedHashMap<String, String>> tempTable = new ArrayList<>();  // Temporary table for validation
	
	        // Read the table data row by row
	        for (int i = 0; i < numRows; i++) {
	            LinkedHashMap<String, String> row = readRowFromFile(reader, i);
	            if (row == null) {
	                return false; // Error messages are handled in readRowFromFile
	            }
	            tempTable.add(row);  // Add the row to the temp table
	        }
	
	        // If all checks pass, assign the temp table to the actual table
	        table = tempTable;
	        System.out.println("Table loaded successfully from " + filename + ".\n");
	        return true; // Indicate success
	    } catch (IOException | NumberFormatException e) {
	        System.out.println("Error loading the table: " + e.getMessage());
	        return false; // Indicate failure
	    }
	}

	private int validateFileFormat(BufferedReader reader) throws IOException {
	    String rowsLine = reader.readLine();
	    if (rowsLine == null || !rowsLine.startsWith("Rows: ")) {
	        System.out.println("Error: The file does not contain valid row information.");
	        return -1; // Indicate error
	    }
	
	    int numRows = Integer.parseInt(rowsLine.substring(6));
	    if (numRows <= 0) {
	        System.out.println("Error: The file does not contain any rows.");
	        return 0; // Indicate no rows
	    }
	    
	    return numRows; // Return the number of rows
	}

	private LinkedHashMap<String, String> readRowFromFile(BufferedReader reader, int rowIndex) throws IOException {
	    String line = reader.readLine();
	    if (line == null || line.trim().isEmpty()) {
	        System.out.println("Error: Not enough data in the file. Missing rows.");
	        return null; // Indicate error
	    }
	
	    LinkedHashMap<String, String> row = new LinkedHashMap<>();
	    String[] keyValuePairs = line.split(" ");
	
	    for (String pair : keyValuePairs) {
	        String[] keyValue = pair.split(":");
	        if (keyValue.length == 2) {
	            if (row.containsKey(keyValue[0])) {
	                System.out.println("Error: Duplicate key '" + keyValue[0] + "' found in row " + (rowIndex + 1) + ". Aborting load.");
	                return null; // Indicate error
	            }
	            row.put(keyValue[0], keyValue[1]); // Add the key-value pair to the row
	        } else {
	            System.out.println("Warning: Invalid key-value pair '" + pair + "' skipped.");
	        }
	    }
	
	    return row; // Return the populated row
	}
	
 	// Print method (updated to work with LinkedHashMap)
    public void print() {
	    System.out.println("3. Print");
	    
	    // Check if the table is empty before printing
	    if (table.isEmpty()) {
	        System.out.println("The table is empty. Nothing to print.\n");
	        return;
	    }
	
	    System.out.println("Printing the table...\n");
	    
	    for (LinkedHashMap<String, String> row : table) {
	        printRow(row);
	    }
	    
	    System.out.println(); // Add an extra newline at the end for better readability
	}

	private void printRow(LinkedHashMap<String, String> row) {
	    StringBuilder rowOutput = new StringBuilder();
	    
	    for (String key : row.keySet()) {
	        rowOutput.append(key).append(":").append(row.get(key)).append(" ");
	    }
	    
	    System.out.println(rowOutput.toString().trim()); // Trim to remove trailing space
	}



	// Reset method
	public void reset() {
	    // Notifying the user that the table is being reset
	    System.out.println("Resetting the table...");
	
	    try {
	        // Calling the generate method to reinitialize the table with new random values
	        generate();
	        System.out.println("Table reset successfully.\n");
	    } catch (Exception e) {
	        // Handle any potential exceptions that may occur during table generation
	        System.out.println("Error resetting the table: " + e.getMessage());
	    }
	}

	// Exit method
	public void exit() {
	    // Display farewell message to the user
	    System.out.println("Thank you for using the random ASCII character generator!");
	
	    // Close the Scanner object to release resources/memory
	    if (myInput != null) {
	        myInput.close();
	    }
	
	    // Terminate the program with status code 0 (success)
	    System.exit(0);
	}
}
