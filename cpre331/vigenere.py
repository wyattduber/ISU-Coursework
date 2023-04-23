#  Written by Wyatt Duberstein for CPR E 331 Lab 3, Finished 9/19/22

#  This is a vigenere decipgering script. It is Semi-automatic in the sense that it requires 2 user
#  inputs to determine the final plaintext. If I get around to it some day, I may make it fully automatic
#  by comparing the outputs to an english dictionary of some kind. Who knows, that sounds like it's out of my league


#  HOW TO USE

#  Simply Enter a plaintext, then the code will sort out the most common trigrams, then seperate them into frequencies
#  in the ciphertext, then find the starting indexes of all of the trigrams and output them. The user will then
#  determine the most common factor between the indexes, which is the most likely length of the key.
#  The ciphertext will then be split into X shift-by-n ciphers, and will then find the 3 most common characters
#  in each of those shift-by-n ciphers, which can be used to find the key by shifting the letters to the left by 4
#  which is the ASCII code of the most common english letter, E. The user will use those X characters in order to
#  form the key that will then be used to attempt to decipher the main ciphertext, and the result will be printed.

import re  # Used to find starting indices of trigrams
from collections import Counter  # Used to count character frequency in


def main():  # Main method, runs all the following methods in sequence
    m = input("Enter a Vigenere Cipher: ")  # "WKTZWSGJGRNZIDVNKXNHAHFXDUYFOKEXFDVNKXFQOCMZGGKUJEEMBDQEJBIGNKXMLGNUNWOHFRRNGOYWHKZHXSZCZZOTBIGUYFYIOXUCHPXPBUEYTJIARMKHOVMZIOXTKDCKTXLETDZOIQFIXCRFVUSIJZQCKBIGARMOHCNOJOOMZCDSTPXRNYDDHNIFJIIZTJZNCIZWHKSDWWOMGEEIPHHALPMFELPMJOUEJUIRMYHPKOYVOTNVQATEJQLEJAWHKVILTKENWAZFNRCIVKLEYBKRSOUDRNUGKUEKNDQETDZFATXZKERQYHCOEZZHKUCHRZIDVNKXJFEGORLLRCZDSKBJIPKBXHOXBIHWZFMUILZDQGZIZDTKSJIWGSDGOTPOVAEUCDTCFNKOAMYRRCJGOGUVISRUUZFTKEVJAOONWTNFCRSZJGHMOTPVEUGNSAIFVQYSPMHTNBIZEMPPQPXPOHCZFYDGGJIVTZIZKOYUDOEATZRFRBIGOXTZDBAUDGOYBTWHGUNSAIFXDNHFZAPRPMHDGOYPAYUZUEJXDWHUVOIEKEDQGZIZIIXFNRFCBMZIZIJXTXFKHAZJIJTNFHLSZBFHSZIVWMGOCDSSBYHITFSWETEDQGNJNZROUVUOAOYWHOTBOOHFJIOASNWHKSZLSTPNWROGZQOVSZMUJJXHNUOVWIUOVOCUOAOIIUDQOAUZUSVBXHAYZZWIZTCDZGSYVAXFCRSZJGHTUVNDLRJOVCUOLXEYUYHSKSQHSZIZEEYUJIARMHDNQJIGATEDWSUQKRRZVILTEGJUPKBXHFAMXROVFMDTOPIPAEOZYEXDJPEGHVLNHVOZHETJPEYBTWHKNJRNCITFHUPNHTNJNDSUVMJOGMVQDZIZBMGZRHLRBNNWNZXOISCOKENJBKEYUHRUTUVLNCITWHOSOBFOWZBEGSNDGUGGBTNFVWLGOOLCCITGOKTMLCKQGDYZFSDSCFXKOUTZWOMPORTNFHROTXZFHUPNHTUHJWOZIZPOUORHCNPJVEZPBRTUUCHMUPILNZIDVDKDVGEGOYGOZIZRTNFMWHOOBVNUUWHCGVNHTNFTDRKFVVYHVOEEIBPVEZIZBAXFCDRJCZFAATZWHGUBRARXDOLYFMYEZPJUGGODCEGOYPEGTPUEZIZEEYUJIOASZQEXHDHSGOYVKOMGVBKDVXSKUCDTIIVOLKOBHIYPIHTNBOZEGSZZIRMDQGZPVFCKQORNKXZDRKVIZIRMDQGZPKRSZQJQEGOYRNKXZLNZFIGTUXDQATEOKEUUCHRYUJR"  # Hard coded ciphertext input
    print()
    sort = trigram(m)  # Runs the frequent trigrams finder, returns the sorted array
    indices = find(m, sort)  # Finds the starting indices of the most frequent trigrams
    differences(indices)  # Finds the differences in the starting indices
    splits = split(m)  # Splits the ciphertext into x shift-by-n ciphers
    freq(splits)  # Finds the char frequencies in the splits
    alpha(m)  # Shifts the ciphertext by n for each character according to the found key


def trigram(m):  # Finds the most frequent trigrams in the ciphertext
    print("Trigrams:")
    trigrams = {}
    for i in range(len(m) - 2):  # Loops through the ciphertext and pulls out trigrams as they repeat most common
        trigrams["" + m[i] + m[i + 1] + m[i + 2]] = trigrams.get("" + m[i] + m[i + 1] + m[i + 2], 0) + 2
    tmp = reversed(sorted(trigrams.items(), key=lambda x: x[1]))  # Sort by most frequent trigrams
    sort = dict(tmp)
    j = 0
    out = "The five most common trigrams are: ["
    for i in sort:  # Loop through the sorted array and print out with format j times
        if j < 4:
            out += "'" + str(i) + "', "
            j += 1
        if j == 4:
            out += "'" + str(i) + "']"
            j += 1
    print(out + "\n")
    return sort


def find(m, sort):  # Finds the starting indices of the 5 most common trigrams in the ciphertext
    print("Find:")
    indices = {}
    for i in sort:  # uses re.findIter() to find the indices of the sorted most common trigrams
        indices[i] = [j.start() for j in re.finditer(i, m)]
    j = 0
    for i in indices:  # Loops through the index dict and outputs them in format j times
        if j <= 4:
            print("The starting indices of '" + i + "' are: " + str(indices[i]))
            j += 1
        else:
            print("\n")
            return indices


def differences(indices):  # Finds the differences between the starting indices of the most common trigrams
    print("Differences:")
    diff = {}
    for i in indices:  # Loops through the indices and finds the differences between them
        diff[i] = [k - j for j, k in zip(indices[i][:-1], indices[i][1:])]
    j = 0
    for i in diff:  # Loops through the differences and outputs them in format j times
        if j <= 4:
            print("The differences between each index of '" + i + "' are: " + str(diff[i]))
            j += 1
        else:
            print("\n")
            return diff


def split(m):  # Split the ciphertext into x shift-by-n ciphers
    print("Splits:\n")
    print("Read the above text. Find the most common factor of the differences between the most common trigrams.")
    x = int(input("> "))
    splits = {}
    for i in range(0, x):  # Loops through x times and finds x, x+1, x+2, ..., x+n shift-by-n splits of the ciphertext
        splits[i] = m[::x]
        m = m[1:]
    for i in range(0, x):  # Outputs the splits in format
        print("///////// Cipher " + str(i) + " /////////")
        print(splits[i])
        print("\n")
    return splits


def freq(splits):  # Find the 3 most frequent characters of the x split ciphertexts
    print("Ciphertext Frequencies:")
    chars = {}
    sort = {}
    j = 0
    for i in splits:  # Loop through each split of the main ciphertext input
        chars[j] = Counter(splits[i])  # Find most frequent characters in ciphertext
        sort[j] = dict(reversed(sorted(chars.items(), key=lambda x: x[1])))[j]  # Sort into array by most freq char
        j += 1
    for j in range(0, 5):  # Loop through sorted arrays to output the 3 most freq char for each split
        s = "3 Most Common Characters in Ciphertext " + str(j) + ": ["
        tmp = dict(reversed(sorted(sort[j].items(), key=lambda x: x[1])))  # Sort each individual dict
        k = 0
        for i in tmp:  # Loop through only first 3 and format the output
            if k < 2:
                s += i + ", "
                k += 1
            else:
                s += i + "]"
                break
        print(s)
    print("\n")


def alpha(m):  # Do an individual shift-by-n cipher on each character using a key
    print("Shift-By-N (Final Cipher): ")
    s = ""
    key = input("Enter the key you've found: ")
    print("Key: " + key)
    j = 0
    for i in m:  # Loop through all chars in message m while looping through the key 5 at a time
        if j <= 4:
            s += n(i, key[j])  # Shift each individual character by they key's value
            j += 1
            if j > 4:
                j = 0
    print(s)


def n(msg, s):  # Shift each character given by N, the char of the key in that position
    c = ord(msg)
    shift = 65 - ord(s)
    if (c + shift) < 65:  # Check if the ASCII code goes below 65 when shifting to the left
        dif = (c + shift) - 64
        return chr(ord('Z') + dif)  # Return wrapped & shifted char
    else:
        return chr(c + shift)  # If it did not shift below 65, then just return the shifted char


if __name__ == "__main__":  # Run the main method
    main()
