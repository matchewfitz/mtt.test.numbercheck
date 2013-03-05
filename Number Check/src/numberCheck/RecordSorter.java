package numberCheck;

public class RecordSorter{
	
	private Record[] records;
	
	/**
	 * sortAllRecords sorts a given array of Records by their Record number in numerical order.
	 * @param unsortedRecords an array of Records to be sorted.
	 * @return the array of Records sorted in numerical order.
	 */
	public Record[] sortAllRecords(Record[] unsortedRecords){
		//if the array is empty, don't bother
		if(unsortedRecords.length == 0){
			return unsortedRecords;
		}
		
		//records will need to stay up to date as sortSection performs recursion.
		records = unsortedRecords;
		sortSection(0, records.length -1);
		
		//return the sorted array
		return records;
	}
	
	/**
	 * sortSection performs quicksort on the array of Records. This is a recursive method.
	 * @param low an int value referring to the lower bound index of the section of the Records array to be sorted.
	 * @param high an int value referring to the upper bound index of the section of the Records array to be sorted.
	 */
	private void sortSection(int low, int high){
		int i = low, j = high;
		// Get the pivot element from the middle of the list (this can actually be any number in the sequence)
		long pivot = Long.parseLong(records[low + (high-low)/2].getRecordNumber());
		
		// Divide into two lists
		while (i <= j) {
			//increment i if the current record is smaller than the pivot value
			while (Long.parseLong(records[i].getRecordNumber()) < pivot) {
				i++;
			}
			//increment j if the current record is smaller than the pivot value
			while (Long.parseLong(records[j].getRecordNumber()) > pivot) {
				j--;
			}
			
			// If a value on the left larger than the pivot and a value on the right smaller than the pivot:
			if (i <= j) {
				//swap the values
				exchange(i, j);
				//continue
				i++;
				j--;
			}
		}
		
		// Perform the same transformation on the low half and the high half.
		if (low < j){
			sortSection(low, j);
		}
		if (i < high){
			sortSection(i, high);
		}
	}
	
	/**
	 * exchange swaps two elements in the Records array
	 * @param swap1 an int, the index of the first element to be swapped
	 * @param swap2 an int, the index of the second element to be swapped
	 */
	private void exchange(int swap1, int swap2) {
		Record temp = records[swap1];
		records[swap1] = records[swap2];
		records[swap2] = temp;
	}
	
}