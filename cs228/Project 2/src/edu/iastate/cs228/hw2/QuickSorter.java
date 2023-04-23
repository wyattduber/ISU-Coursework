package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs quick sort
 * to sort the list.
 * 
 * @author Wyatt Duberstein
 */
public class QuickSorter extends Sorter
{
  @Override
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException{
    quickSortRec(toSort, comp, 0, toSort.length() - 1);
  }

  private void quickSortRec(WordList list, Comparator<String> comp, int start, int end){

    if (start >= end) { return; }

    int pivot = partition(list, comp, start, end);

    quickSortRec(list, comp, start, pivot);

    quickSortRec(list, comp, pivot + 1, end);


  }

  private int partition(WordList list, Comparator<String> comp, int start, int end){
    // TODO

    String[] arr = list.getArray();

    int middle = start + (end - start) / 2;
    String pivot = arr[middle];
    int low = start;
    int high = end;

    boolean done = false;
    while (!done) {

      while (comp.compare(arr[low], pivot) < 0) {
        low++;
      }

      while (comp.compare(arr[high], pivot) > 0) {
        high--;
      }

      if (low >= high) {
        done = true;
      } else {
        String temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
        low++;
        high--;
      }

    }

    return high;
  }
}
