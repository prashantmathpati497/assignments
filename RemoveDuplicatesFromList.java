
// Java program to remove duplicates from ArrayList 

import java.util.*;

public class RemoveDuplicatesFromList {

	// Function to remove duplicates from an ArrayList
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList removeDuplicates(ArrayList list) {

		// Create a new ArrayList
		ArrayList newList = new ArrayList();

		// Traverse through the first list
		// for (List element : list) {
		for (int i = 0; i < list.size(); i++) {
			// If this element is not present in newList
			// then add it
			if (!newList.contains(list.get(i))) { // get(index) function is used
													// for retrieving the list
													// elements from list
				newList.add(list.get(i));
			}
		}

		// return the new list
		return newList;
	}

	// Main Method
	public static void main(String args[]) {

		// Get the ArrayList with duplicate values
		// ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 10,
		// 2, 2, 10, 3, 3, 3, 4, 5, 5));

		// Get the ArrayList with Duplicate Strings
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList list = new ArrayList(Arrays.asList("G", "e", "e", "k", "s"));

		// Print the Arraylist
		System.out.println("ArrayList with duplicates: " + list);

		// Remove duplicates
		@SuppressWarnings("unchecked")
		ArrayList<Integer> newList = removeDuplicates(list);

		// Print the ArrayList with duplicates removed
		System.out.println("ArrayList with duplicates removed: " + newList);
	}
}
