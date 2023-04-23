import random
import time


# Sorts array a[0..n-1] using Bogo sort
global largestClose = 0
global arr = []

def bogoSort(a):
    n = len(a)
    while (is_sorted(a) == False):
        shuffle(a)


# To check if array is sorted or not


def is_sorted(a):
    n = len(a)
    close = 0
    for i in range(0, n - 1):
        close += 1
        if (a[i] > a[i + 1]):
            if close > largestClose:
                largestClose = close
                arr = a.copy()
            print(arr)
            return False
    return True


# To generate permutation of the array


def shuffle(a):
    n = len(a)
    for i in range(0, n):
        r = random.randint(0, n - 1)
        a[i], a[r] = a[r], a[i]


# Driver code to test above

t = time.time()
a = [3, 2, 4, 1, 0, 5, 10, 9, 6, 10, 32, 55, 27, 4, 18, 20, 19, 20, 20, 20, 55, 6, 12, 35, 48]
bogoSort(a)
print("Sorted array :")
for i in range(len(a)):
    print("%d" % a[i])
print(round(time.time() - t, 2))
