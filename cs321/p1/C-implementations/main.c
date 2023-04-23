#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Swaps the elements from pos1 and pos2 in arr[]
void swapArr(long int arr[], long int pos1, long int pos2)
{
	long int temp; // Temp var to avoid overwrites

	// Swap
	temp = arr[pos1];
	arr[pos1] = arr[pos2];
	arr[pos2] = temp;
}

// Starting at index currIndex and stopping at index length, returns the index of the smallest value in arr[]
long int findSmallest(long int arr[], long int length, long int currIndex)
{
	long int i, smallest, index;
	smallest = arr[currIndex]; // Assumes that the smallest value is at the starting index
	index = currIndex;
	for(i = currIndex; i < length; i++) // Loop through the array
	{
		if(smallest > arr[i]) // If the current value is smaller than the current smallest, note the index
		{
			smallest = arr[i];
			index = i;
		}
	}
	return index; // After looping through the whole array, return the index of the smallest found value
}

// Sorts a given array via the Selection Sort algorithm, depends: findSmallest() and swapArr()
void selectionSort(long int arr[], long int length) {
	for (int i = 0; i < length; i++) { // Loop through the whole array
		long int smallestIndex = findSmallest(arr, length, i); // Find the smallest index
		swapArr(arr, smallestIndex, i); // Put it at the front of the sorted portion of the array
	}
}

long int * fillRandom(long int arr[], long int size) {
	//int lower = 1, upper = 1000;
	srand(time(0)); // Set the seed for the random method
	for (int i = 0; i < size; i++) { // Loop through the whole array
		arr[i] = rand(); // Set each element of the array to a random number
	}

	int swap = 0;

	// Reverse-Sort the array using bubblesort (cuz im lazy)
	for (int j = 0; j < size - 1; j++) {
		for (int k = 0; k < size - j - 1; k++) {
			if (arr[k] < arr[k+1]) {
				swap = arr[k];
				arr[k] = arr[k+1];
				arr[k+1] = swap;
			}
		}
	}

	return arr; // Return the reverse-sorted random array

}

int main() {

	long int arr[100];
	fillRandom(arr, 100);
	selectionSort(arr, 100);
	for (int i = 0; i < 100; i++) {
		printf("%d, ", arr[i]);
	}
	printf("\n");
}
