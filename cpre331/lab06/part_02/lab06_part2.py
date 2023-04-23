"""

Implement the ChaCha20 stream cipher.

Found in Implementing Cryptography with Python.

Adapted for CprE 331

"""

import struct

import sys, os, binascii

from base64 import b64encode


def get_binary_values(x, ctx):
    # Convert to Binary
    xBits = []
    ctxBits = []
    for i in x:
        xBits.append(bin(i)[2:])
    for i in ctx:
        ctxBits.append(bin(i)[2:])

    # Compare array lengths and find the max stream size
    maxStream = 0
    for stream in ctxBits:
        if len(stream) > maxStream:
            maxStream = len(stream)
    for stream in xBits:
        if len(stream) > maxStream:
            maxStream = len(stream)
    # Pad the smaller streams to make them equal size and comparable
    for i in range(len(ctxBits)):
        if len(ctxBits[i]) != maxStream:
            ctxBits[i] + ('0' * (maxStream - len(ctxBits[i]))) + xBits[i]
        if ctxBits[i] == "0":
            ctxBits[i] = '0' * 32
    for i in range(len(xBits)):
        if len(xBits[i]) != maxStream:
            xBits[i] = ('0' * (maxStream - len(xBits[i]))) + xBits[i]

    # Find the differing bits by comparison
    diffBits = []
    for i, j in zip(xBits, ctxBits):
        try:
            for n in range(maxStream):
                if i[n] != j[n]:
                    diffBits.append(i[n] + j[n])
                    # print("N: " + str(n) + ", Length: " + str(len(diffBits)))
        except:
            continue
    print("Original Matrix in Binary: " + str(ctxBits))
    print("Round 0 Odd qr 1 Matrix in Binary: " + str(xBits))
    print("Diffused Bits: " + str(len(diffBits)))
    print("Percentage: " + str(float(len(diffBits) / 512) * 100) + "%")


def yield_chacha20_xor_stream(key, iv, position=0):
    # Generate the xor stream with the ChaCha20 cipher."""

    if not isinstance(position, int):
        raise TypeError

    if position & ~0xffffffff:
        raise ValueError('Position is not uint32.')

    if not isinstance(key, bytes):
        raise TypeError

    if not isinstance(iv, bytes):
        raise TypeError

    if len(key) != 32:
        raise ValueError

    if len(iv) != 8:
        raise ValueError

    def rotate(v, c):

        return ((v << c) & 0xffffffff) | v >> (32 - c)

    def print_matrix(x):

        print(x[:4])

        print(x[4: 8])

        print(x[8: 12])

        print(x[12: 16])

        print()

        print()

        return

    def quarter_round(x, a, b, c, d):

        x[a] = (x[a] + x[b]) & 0xffffffff

        x[d] = rotate(x[d] ^ x[a], 16)

        x[c] = (x[c] + x[d]) & 0xffffffff

        x[b] = rotate(x[b] ^ x[c], 12)

        x[a] = (x[a] + x[b]) & 0xffffffff

        x[d] = rotate(x[d] ^ x[a], 8)

        x[c] = (x[c] + x[d]) & 0xffffffff

        x[b] = rotate(x[b] ^ x[c], 7)

    ctx = [0] * 16

    ctx[:4] = (1634760805, 857760878, 2036477234, 1797285236)

    ctx[4: 12] = struct.unpack('<8L', key)

    ctx[12] = ctx[13] = position

    ctx[14: 16] = struct.unpack('<LL', iv)

    print('original 4x4 matrix')

    print_matrix(ctx)

    while 1:

        x = list(ctx)

        for i in range(10):
            print('>>>>  ROUND ', i, '   <<<<<')

            quarter_round(x, 0, 4, 8, 12)

            if i == 0:
                get_binary_values(x, ctx)

            print('odd qr 0')

            print_matrix(x)

            quarter_round(x, 1, 5, 9, 13)

            print('odd qr 1')

            print_matrix(x)

            quarter_round(x, 2, 6, 10, 14)

            print('odd qr 2')

            print_matrix(x)

            quarter_round(x, 3, 7, 11, 15)

            print('odd qr 3')

            print_matrix(x)

            quarter_round(x, 0, 5, 10, 15)

            print('even qr 0')

            print_matrix(x)

            quarter_round(x, 1, 6, 11, 12)

            print('even qr 1')

            print_matrix(x)

            quarter_round(x, 2, 7, 8, 13)

            print('even qr 2')

            print_matrix(x)

            quarter_round(x, 3, 4, 9, 14)

            print('even qr 3')

            print_matrix(x)

        for c in struct.pack('<16L', *(

                (x[i] + ctx[i]) & 0xffffffff for i in range(16))):
            yield c

        ctx[12] = (ctx[12] + 1) & 0xffffffff

        if ctx[12] == 0:
            ctx[13] = (ctx[13] + 1) & 0xffffffff


def chacha20_encrypt(data, key, iv=None, position=0):
    # Encrypt (or decrypt) with the ChaCha20 cipher.

    if not isinstance(data, bytes):
        raise TypeError

    if iv is None:
        iv = b'\0' * 8

    if isinstance(key, bytes):

        if not key:
            raise ValueError('Key is empty.')

        if len(key) < 32:
            key = (key * (32 // len(key) + 1))[:32]

        if len(key) > 32:
            raise ValueError('Key too long.')

    return bytes(a ^ b for a, b in

                 zip(data, yield_chacha20_xor_stream(key, iv, position)))


def main():
    key = b'Obi-Wan Kenobi'

    plaintext = b'For over a thousand generations, the Jedi Knights were the guardians of peace and justice in the Old Republic. Before the dark times, before the Empire.'

    iv = b'JediYoda'

    enc = chacha20_encrypt(plaintext, key, iv)

    decode_enc = b64encode(enc).decode('utf-8')

    # print('The encrypted string is {}. '.format(decode_enc))

    print()

    # dec = chacha20_encrypt(enc, key, iv)

    # print('The decrypted string is {}. '.format(dec))

    # print()


if __name__ == "__main__":
    sys.exit(int(main() or 0))
