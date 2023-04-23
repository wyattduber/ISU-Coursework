# Lab 05 Part 01 Skeleton code for LFSR crypto-system
import sys


# The cycle function produces the new bit, shifts the registers to the right by one position,
# and puts the new bit in the 4th bit position.
# As it is written it XORs the first bit and the last bit in the registers
# You may need to select different bits to create the correct xorValue
# However, the shifting of the registers will not need to be modified.
def cycle(registers):
    xorValue = ((registers & 1) ^ (registers >> 3)) & 1
    registers = (registers >> 1) | (xorValue << 3)
    return registers


# Loop through the length of the plaintext in binary
# Create a keystream from the 4th bit in every cycle
# of the registers.  Don't forget that you must
# include bit 4 of the seed value as the first bit
# in the keyStream.  To get the last bit out of the registers
# variable you can &1 to it.
def makeKeystream(registers, length):
    # Use cycle(registers) as well as other code you
    keyStream = ""
    for i in range(length):
        keyStream += str(registers & 1)
        registers = cycle(registers)
    return keyStream


# Use to convert a string to binary representation
def toBinary(string):
    return ''.join(format(ord(i), '08b') for i in string)


# Use to convert a binary string to ascii
def toAscii(bits):
    byte = bits.to_bytes(1, 'big')  #
    ascii_text = byte.decode()
    return ascii_text


def main():
    # Prompt for seed values: input()
    # Wise students will use the int() function with the base 2
    # For example, int('11111111',2) where the 1s are input from the terminal

    seed = int(input("Enter the seed: "), 2)

    # Input the plaintext message

    msg = input("Plaintext message: ")

    # Convert plaintext message to binary
    # Use the toBinary function

    msg = toBinary(msg)
    print("Message in binary: ", msg)

    # Generate a keystream that is the length of our plaintext
    # Use makeKeystream(registers, length)

    keyStream = makeKeystream(seed, len(msg))
    print("Keystream: ", keyStream)

    # XOR the Plaintext bits and the Keystream bits
    # Use int(variable,2) to represent the variable in binary value
    # The ^ is the XOR operator in python

    ct = ""
    for i in range(len(msg)):
        ct += str(int(''.join(format(ord(msg[i]), 'b')), 2) ^ int(''.join(format(ord(keyStream[i]), 'b')), 2))
    print("Encrypted Ciphertext: ", ct)

    # To check your work XOR the Ciphertext and the keystream
    # Use int(variable,2) to represent the variable in binary value
    # The ^ is the XOR operator in python

    dct = ""
    for i in range(len(msg)):
        dct += str(int(''.join(format(ord(ct[i]), 'b')), 2) ^ int(''.join(format(ord(keyStream[i]), 'b')), 2))
    print("Decrypted Plaintext: ", dct)

    # To further check your work convert the bits to ascii using
    # the toAscii() function.
    pt = ""
    for i in range(0, len(dct), 8):
        pt += toAscii(int(dct[i:i + 8], 2))
    print("Plaintext in Ascii: ", pt)


if __name__ == "__main__":
    main()
