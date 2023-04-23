import sys

# The cycle function produces the new bit, shifts the registers to the right by one position,
# and puts the new bit in the 4th bit position.
# As it is written it XORs the first bit and the last bit in the registers
# You may need to select different bits to create the correct xorValue
# However, the shifting of the registers will not need to be modified.
def cycle(registers):
    xorValue = ((registers & 1) ^ (registers >> 1)) & 1
    registers = (registers >> 1) | (xorValue << 3)
    return registers

# Loop through the length of the plaintext in binary
# Create a keystream from the 4th bit in every cycle
# of the registers.  Don't forget that you must
# include bit 4 of the seed value as the first bit
# in the keyStream.  To get the last bit out of the registers
# variable you can &1 to it.
def makeKeystream(registers, length):
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
    byte = bits.to_bytes(1, 'big')
    ascii_text = byte.decode()
    return ascii_text


def main():
    # msg = input("Input the known plaintext: ")
    # cipherText = input("Input the ciphertext: ")
    msg = "General Kenobi"
    cipherText = "01110010100001110000010110100001101001011110100011000011001100110001010101000011110100100010001000011010111100111101000101000010100010110000011110101000111101111110110111001010011000110011111101010100110010000110110100011110111010001001111001011000110000100010010110100101101101011110011011000000001100110011100001001001110011100110110100101100111110111000010101011010100011010000001010101010101100101010100111000110011111010111111001010101110110010011101100011101111101001101000101011101100011010001111010110110101001001010100111000000011111010111111001000111110100100110110100100000101101111000011001011100100011000000110011100100101000001110000011011011011110110111111001010010110101000010100001011000111010101001000001000111100000010000111010101000"

    # Convert the partial plantext to binary
    bits = toBinary(msg)

    # XOR the plaintext with the ciphertext digits as long as the next plaintext
    period = ""
    for i in range(len(bits)):
        period += str(int(''.join(format(ord(bits[i]), 'b')), 2) ^ int(''.join(format(ord(cipherText[i]), 'b')), 2))

    # Take the seed from the first 4 of the plaintext and reverse it
    seed = int(period[0:4][::-1], 2)

    # Make the keystream
    keyStream = makeKeystream(seed, len(cipherText))

    # Decipher the ciphertext by XORing the ciphertext with the keystream
    ct = ""
    for i in range(len(cipherText)):
        ct += str(int(''.join(format(ord(cipherText[i]), 'b')), 2) ^ int(''.join(format(ord(keyStream[i]), 'b')), 2))
    print(ct)

    # Convert the decrypted bits into readable ascii
    pt = ""
    for i in range(0, len(ct), 8):
        pt += toAscii(int(ct[i:i + 8], 2))
    print("Plaintext in Ascii: ", pt)


if __name__ == "__main__":
    main()
