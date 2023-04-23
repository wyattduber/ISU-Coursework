from collections import Counter
import os
import fileinput


# Returns the most common element in a list
def mostCommon(lst):
    c = Counter(lst)
    return c.most_common(1)[0][0]


def split(line):
    entry = line.strip()
    tmp = entry[:2] + entry[6:]
    tmp = tmp.split(' ')
    return tmp


# Used to calculate m[0]
def flaw1(file):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            arr.append(int(entry[1], 16) ^ ((int(entry[0], 16) + 2) % 256))
        return mostCommon(arr)


# Used to calculate k[0]
def flaw2(file, m):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            results = (int(entry[1], 16) ^ m) - int(entry[0], 16) - 6
            arr.append((results % 255))
        return mostCommon(arr)


def flaw3_1(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4) - k[0]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def flaw3_2(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4+5) - k[0] - k[1]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def flaw3_3(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4+5+6) - k[0] - k[1] - k[2]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def flaw3_4(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4+5+6+7) - k[0] - k[1] - k[2] - k[3]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def flaw3_5(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4+5+6+7+8) - k[0] - k[1] - k[2] - k[3] - k[4]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def flaw3_6(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4+5+6+7+8+9) - k[0] - k[1] - k[2] - k[3] - k[4] - k[5]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def flaw3_7(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4+5+6+7+8+9+10) - k[0] - k[1] - k[2] - k[3] - k[4] - k[5] - k[6]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def flaw3_8(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4+5+6+7+8+9+10+11) - k[0] - k[1] - k[2] - k[3] - k[4] - k[5] - k[6] - k[7]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def flaw3_9(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4+5+6+7+8+9+10+11+12) - k[0] - k[1] - k[2] - k[3] - k[4] - k[5] - k[6] - k[7] - k[8]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def flaw3_10(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4+5+6+7+8+9+10+11+12+13) - k[0] - k[1] - k[2] - k[3] - k[4] - k[5] - k[6] - k[7] - k[8] - k[9]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def flaw3_11(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4+5+6+7+8+9+10+11+12+13+14) - k[0] - k[1] - k[2] - k[3] - k[4] - k[5] - k[6] - k[7] - k[8] - k[9] - k[10]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def flaw3_12(file, m, k):
    with open(file, "r") as f:
        arr = []
        for line in f:
            entry = split(line)  # Entry = ['0X01FF00', '0XDB']
            y = int(entry[1], 16)
            x = int(entry[0], 16)
            ks = (y ^ m) - x - (1+2+3+4+5+6+7+8+9+10+11+12+13+14+15) - k[0] - k[1] - k[2] - k[3] - k[4] - k[5] - k[6] - k[7] - k[8] - k[9] - k[10] - k[11]
            arr.append((ks % 255))  # IMPLEMENT HERE
        return mostCommon(arr)


def main():
    m0 = flaw1("./bytes_01FFXX.txt")
    k0 = flaw2("./bytes_03FFXX.txt", m0)
    print("Guess for m[0]: " + str(hex(m0)))
    print("Guess for k[0]: " + str(hex(k0)))
    ks = {0: k0}
    ks[1] = flaw3_1("./bytes_04FFXX.txt", m0, ks)
    ks[2] = flaw3_2("./bytes_05FFXX.txt", m0, ks)
    ks[3] = flaw3_3("./bytes_06FFXX.txt", m0, ks)
    ks[4] = flaw3_4("./bytes_07FFXX.txt", m0, ks)
    ks[5] = flaw3_5("./bytes_08FFXX.txt", m0, ks)
    ks[6] = flaw3_6("./bytes_09FFXX.txt", m0, ks)
    ks[7] = flaw3_7("./bytes_0AFFXX.txt", m0, ks)
    ks[8] = flaw3_8("./bytes_0BFFXX.txt", m0, ks)
    ks[9] = flaw3_9("./bytes_0CFFXX.txt", m0, ks)
    ks[10] = flaw3_10("./bytes_0DFFXX.txt", m0, ks)
    ks[11] = flaw3_11("./bytes_0EFFXX.txt", m0, ks)
    ks[12] = flaw3_12("./bytes_0FFFXX.txt", m0, ks)

    k = 1
    for i in range(len(ks) - 1):
        if i == ks[0]:
            continue
        print("Guess for k[" + str(k) + "]: " + str(hex(ks[k])))
        k += 1


if __name__ == '__main__':
    main()
