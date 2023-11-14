package terlan.interview;

import java.util.*;

public class InterviewTasks {
/**
 * 
 * @param ar array of integer numbers
 * @param sum integer number
 * @return true if the given array contains two numbers, the sum of which equals the given sum value
 */
	public static boolean isSum2(int [] ar, int sum) {
		HashMap<Integer, Integer> myMap = new HashMap<>();
		for (int i = 0; i < ar.length; i++) {
			int meaning = sum - ar[i];
			if (myMap.containsKey(meaning)) {
				return true;
			}
			myMap.put(ar[i], i);
		}
		return false;
	}
	/**
	 * 
	 * @param ar array of integer numbers
	 * @return maximal positive number having negative number with the same absolute value
	 */
	public static int getMaxPositiveWithNegativeValue (int[] ar) {
		HashSet<Integer> set = new HashSet<>();
		for (int num : ar) {
			set.add(num);
		}
		HashMap<Integer, Integer> moduleMap = new HashMap<>();
		int maxNumber = -1;
		for (int num : set) {
			if (moduleMap.containsKey(Math.abs(num))) {
				maxNumber = Math.max(maxNumber, num);
			} else {
				moduleMap.put(Math.abs(num), num);
			}
		}
		return maxNumber;
    }

	/**
	 * 
	 * @param strings - array of strings
	 * @return Map where key - string, value - number of occurrences in the array
	 */
	public static HashMap<String, Long> getMapOccurrences(String[] strings) {
		//Optionally, additional bonus if you apply the method "merge" of the interface Map
		//try to understand this method https://docs.oracle.com/javase/8/docs/api/java/util/Map.html#merge-K-V-java.util.function.BiFunction- 
		//it uses Functional interface BiFunction
		HashMap<String, Long> occurrencesMap = new HashMap<>();
		for (String str : strings) {
			occurrencesMap.merge(str, 1L, Long::sum);
		}
		return occurrencesMap;
	}

	public static boolean isAnagram(String str1, String str2){
		boolean flag;
		if (str1.equals(str2) || str1.length() != str2.length()) {
			flag = false;
		} else {
			HashMap<Character,Integer> map1 = strToMap(str1);
			HashMap<Character,Integer> map2 = strToMap(str2);
			flag = map1.equals(map2);
		}
		return flag;
	}
	static HashMap<Character,Integer> strToMap(String str){
		HashMap<Character,Integer> map = new HashMap<>();
		for (char c : str.toCharArray()) {
			map.put(c,map.merge(c, 1, Integer::sum));
		}
		return map;
	}

	//Task for streams /grouping
	public static void displayDigitsDistribution() {
		int nNumbers = 1_000_000;
		//create stream of random int's (nNumbers), each int number in range [1, Integer.Max_VALUE)
		//conversion to stream of Strings
		//extracting separate char's from Strings
		//grouping with counting of occurrences
		//sorting in descending order of occurrences
		//printing
	}
}