//SEI Tech Lead Candidate Problem Set
//
//Submitted by: Mohamed Aasim Shaji
//
//Consulting Firm: tekSystems
//
//Contact Info: shajiaasim@gmail.com
//
//1. Given the following class, write 3 methods that can be used to return an array that has no 
//
//duplicates.
// Three methods are defined in this class each of which that return an array without duplicates.
//2. You should define a method signature that you feel is appropriate to the problem.
//
//3. We would prefer to see three implementations (one that should take into consideration #4 
//
//below) and an explanation of what use-cases are suitable to each implementation 
//  To retain the original order I have used a LinkedHashSet which preserves the original order,
// explanations are mentioned in method comments
//4. What if we need to retain the original order?
// Use LinkedHashSet
//5. What are the positives and negatives of your solution?
//  Mentioned in code comments
//6. Your solution should be testable and “production ready.”
// Used JUnit tests to ensure the results are as expected
//7. Your solution should be posted to a public github repo that SEI can clone.
// 
//a. Can you implement it another way so as to avoid the negatives?

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Assert;

public class DeDup {

	public int[] randomIntegers = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34,
			86, 25, 43, 2, 1, 10000, 11, 16, 19, 1, 18, 4, 9, 3, 20, 17, 8, 15,
			6, 2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14, 20, 17,
			16, 3, 6, 19, 13, 5, 11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7, 15, 17,
			10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };

	public static void main(String[] args) {
		DeDup deDup = new DeDup();

		int[] result1 = removeDupesUsingSet(deDup.randomIntegers);
		int[] result2 = removeDuplicates(deDup.randomIntegers);
		int[] result3 = removeDupes(deDup.randomIntegers);
		int[] expectedResultOriginalOrder = { 1, 2, 34, 25, 45, 3, 26, 85, 4,
				86, 43, 10000, 11, 16, 19, 18, 9, 20, 17, 8, 15, 6, 5, 10, 14,
				12, 13, 7 };
		int[] expectedResultNaturalOrder = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
				12, 13, 14, 15, 16, 17, 18, 19, 20, 25, 26, 34, 43, 45, 85, 86,
				10000 };

		Assert.assertArrayEquals(
				"Sucessfully returned Array with No duplicates whilst preserving original order",
				expectedResultOriginalOrder, result1);
		Assert.assertArrayEquals(
				"Sucessfully returned Array with No duplicates with natural order",
				expectedResultNaturalOrder, result2);
		Assert.assertArrayEquals(
				"Sucessfully returned Array with No duplicates with natural order",
				expectedResultNaturalOrder, result3);

	}

	/**
	 * The following method removes duplicates using a LinkedHashSet which
	 * Positives: preserves original order, No negatives
	 */
	static int[] removeDupesUsingSet(int[] duplicateArray) {
		Set<Integer> hashset = new LinkedHashSet<Integer>();
		for (int i = 0; i < duplicateArray.length; i++) {
			hashset.add(duplicateArray[i]);
		}
		int[] uniqueArray = toInt(hashset);
		return uniqueArray;

	}

	/**
	 * The following method removes duplicates without utilizing Collections It
	 * does so by sorting the array, eliminating the duplicates and replacing
	 * creating a new array without the zeroes. The negatives are that this
	 * method will not work if there are duplicate zeroes in the original Array
	 */
	public static int[] removeDuplicates(int[] duplicateArray) {

		Arrays.sort(duplicateArray);
		int uniqueCount = countUnique(duplicateArray);
		int[] result = new int[duplicateArray.length];
		int j = duplicateArray[0];
		result[0] = j;

		for (int i = 1; i < duplicateArray.length; i++) {
			int temp = duplicateArray[i];

			if (j != temp) {
				result[i] = temp;
			}
			j = temp;
		}

		int[] uniqueArray = new int[uniqueCount];
		int k = 0;
		for (int i = 0; i < duplicateArray.length; i++) {
			if (result[i] == 0) {
				continue;
			} else {

				uniqueArray[k++] = result[i];
			}
		}
		return uniqueArray;

	}

	/**
	 * The following method removes duplicates without utilizing Collections
	 * Negatives : For a large array this method will be time consuming.
	 */
	static int[] removeDupes(int[] duplicateArray) {
		Arrays.sort(duplicateArray);

		int j = 0;
		for (int i = 1; i < duplicateArray.length; i++) {

			if (duplicateArray[i] == duplicateArray[j]) {
				continue;
			} else {
				j++;
				duplicateArray[j] = duplicateArray[i];
			}
		}
		int[] uniqueArray = new int[j + 1];
		for (int k = 0; k < uniqueArray.length; k++) {
			uniqueArray[k] = duplicateArray[k];
		}

		return uniqueArray;

	}

	/**
	 * Counts the number of unique elements in an array
	 */
	public static int countUnique(int[] duplicateArray) {
		int countDupe = 0;
		for (int i = 0; i < duplicateArray.length - 1; i++) {
			if (duplicateArray[i] == duplicateArray[i + 1]) {
				countDupe++;
			}
		}
		int countUnique = duplicateArray.length - countDupe;
		return countUnique;

	}

	/**
	 * Converts a Set to an Array
	 */
	static public int[] toInt(Set<Integer> set) {
		int[] a = new int[set.size()];
		int i = 0;
		for (Integer x : set)
			a[i++] = x;
		return a;
	}

}