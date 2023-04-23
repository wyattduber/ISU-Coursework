from collections import Counter


def main():
    go = input("(1) Encrypt, (2) Decrypt: ")
    if go == "1":
        encrypt()
    if go == "2":
        decrypt()
    else:
        main()


def encrypt():
    m = input("Enter a string to encrypt: ")
    s = input("Enter a character to use as the key: ")
    m = m.upper()
    s = s.upper()

    t = m.maketrans("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "EFGHIJKLMNOPQRSTUVWXYZABCD")

    msg = m.translate(t)
    msg = msg.replace('E', s)

    print(msg)



def decrypt():
    m = input("Enter the ciphertext: ")
    s = input("Enter the key: ")
    m = m.upper()
    s = s.upper()

    m = m.replace(s, 'E')
    t = m.maketrans("EFGHIJKLMNOPQRSTUVWXYZABCD", "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

    print(m.translate(t))


if __name__ == "__main__":
    main()
