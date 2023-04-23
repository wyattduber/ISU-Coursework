package lab7;

import java.util.*;

public class SparseArray<T> {
	
	public static final int INITIAL_SIZE = 1000;
	private int[] keys = new int[INITIAL_SIZE];
	private Object[] values = new Object[INITIAL_SIZE];
	private int size = 0;

	public void put(int key, T value) {
		if (value == null) return;
		int index = binarySearch(key, keys, size);
		if (index != -1 && keys[index] == key)

			values[index] = value;
		else
			insertAfter(key, value, index);
		size++;
	}
	
	public int size() {
		return size;
	}
	
	private void insertAfter(int key, T value, int index) {
		int[] newKeys = new int[INITIAL_SIZE];
		Object[] newValues = new Object[INITIAL_SIZE];
		copyFromBefore(index, newKeys, newValues);
		
		int newIndex = index + 1;
		newKeys[newIndex] = key;
		newValues[newIndex] = value;
		
		if (size - newIndex != 0)
			copyFromAfter(index, newKeys, newValues);
		
		keys = newKeys;
		values = newValues;
	
	}
	
	private void copyFromAfter(int index, int[] newKeys, Object[] newValues) {
		int start = index + 1;
		System.arraycopy(keys, start, newKeys, start + 1, size - start);
		System.arraycopy(values, start, newValues, start + 1, size - start);
		}
	
	private void copyFromBefore(int index, int[] newKeys, Object[] newValues) {
		System.arraycopy(keys, 0, newKeys, 0, index + 1);
		System.arraycopy(values, 0, newValues, 0, index + 1);
		}
	
	@SuppressWarnings("unchecked")
	public T get(int key) {
		int index = binarySearch(key, keys, size);
		if (index != -1 && keys[index] == key)
		return (T)values[index];
		return null; 
	}

	int binarySearch(int n, int[] nums, int size) {
		int first = 0, last = size, mid = size / 2;
		while (first <= last) {
			if (nums[mid] < n) {
				first = mid + 1; // Change index and try again
			} else if (nums[mid] == n) { // If number is found, return it
				return mid;
			} else {
				last = mid - 1; // Change index and try again
			}
			mid = (first + last) / 2; // Cut in half and try again
		}
		return -1; // Returns -1 if the number is not found in the index
	}

	public void checkInvariants() throws InvariantException {
		long nonNullValues = Arrays.stream(values).filter(Objects::nonNull).count();
		if (nonNullValues != size) {
			throw new InvariantException("size " + size + " does not match value count of " + nonNullValues);
		}
	}
	
	
}
	


	

