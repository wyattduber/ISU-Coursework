def main():
    array = [38, 27, 43, 3, 9, 82, 10]
    print(mergeSort(array, []))


def mergeSort(array, sortedarray):
    if len(array) > 1:
        arr1 = array[int(len(array)/2):]
        arr2 = array[:int(len(array)/2)]
        mergeSort(arr1, sortedarray)
        mergeSort(arr2, sortedarray)
    else:
        k = 0
        tmp = []
        for i in sortedarray:
            if i < array[k]:
                tmp.append(i)
            else:
                tmp.append(array[k])
                tmp.append(i)
                k += 1
        return mergeSort(tmp, sortedarray)
    return sortedarray


if __name__ == "__main__":
    main()