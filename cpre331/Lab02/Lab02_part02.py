from collections import Counter  # Import Counter library for counting characters in a string


def main():
    go = input("(1) Char Freq, (2) Alphabet Perm, (3) Bigram, (4) Trigram: ")
    if go == "1":
        freq()
    if go == "2":
        alpha()
    if go == "3":
        bigram()
    if go == "4":
        trigram()
    else:
        main()


def freq():
    msg = input("Enter an input: ")  # Input the String to be Counted
    chars = Counter(msg)  # Run the message through the Counter to get the characters counted
    therest(chars, msg)


def alpha():
    a = input("Input the normal alphabet at the exact length of your key: ")  # Input the alphabet key
    a = a.upper()  # Convert a to uppercase
    b = input("Input the alphabet to use as the key: ")  # Input the alphabet key
    b.upper()  # Convert b to uppercase
    m = input("Ciphertext: ")  # Input the ciphertext
    m = m.upper()  # Convert m to uppercase

    t = m.maketrans(b, a)  # Make translation table, converting all letters to the new one
    print(m.translate(t))  # Print the translated ciphertext

def therest(var, m):  # Helper function to do the same thing as the end of all the dict functions
    tmp = reversed(sorted(var.items(), key=lambda x: x[1]))
    sort = dict(tmp)

    for i in sort:  # Loop through the sorted dict for each char
        print(
            i + ": " + str(round((sort[i] / len(m)) * 100, 2)))  # Print the formatted percentage along with the char


def bigram():
    m = input("Input the message to find the bigram: ")
    bigrams = {}
    for i in range(len(m) - 1):
        bigrams["" + m[i] + m[i+1]] = bigrams.get("" + m[i] + m[i+1], 0) + 1
    therest(bigrams, m)



def trigram():
    m = input("Input the message to find the bigram: ")
    trigrams = {}
    for i in range(len(m) - 2):
        trigrams["" + m[i] + m[i+1] + m[i+2]] = trigrams.get("" + m[i] + m[i+1] + m[i+2], 0) + 2
    therest(trigrams, m)



if __name__ == "__main__":
    main()
