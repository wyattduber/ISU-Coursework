import subprocess


def des_encrypt(text, key):
    # Use openssl to encrypt the data
    command = "/bin/echo -n " + text + " | openssl enc -a -des-cbc -nosalt -iv 0000000000000000 -K " + key + " -A  | xxd"
    proc = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    output = proc.stdout.readlines()

    base64String = ""
    for i in range(len(output)):
        # We must parse the ciphertext out of the outout
        # output[i] = [b'00000000: 3172 3973 5850 6644 5431 4737 7737 4149  1r9sXPfDT1G7w7AI\n']
        line = str(output[i]).split(" ")
        string = str(line[len(line) - 1])
        base64String = base64String + string[:-3]
    return base64String


def des_decrypt(ciphertext, key):
    # Use openssl to decrypt the data
    command = "/bin/echo -n " + ciphertext + " | openssl enc -d -a -des-cbc -nosalt -iv 0000000000000000 -A -K " + key + " | xxd"
    proc = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    output = proc.stdout.readlines()

    base64String = ""
    for i in range(len(output)):
        # We must parse the ciphertext out of the outout
        # output[i] = [b'00000000: 3172 3973 5850 6644 5431 4737 7737 4149  1r9sXPfDT1G7w7AI\n']
        line = str(output[i]).split(" ")
        string = str(line[len(line) - 1])
        base64String = base64String + string[:-3]
    return base64String


def main():
    knownPlaintext = "Tatooine"
    k1 = "319df2f409baee"
    k2 = "64abc398ac4fee"
    ciphertext = "tm97RIBRG3eY8fkb0iU696gHGhfGxnYZGVcB2sJRDK4="

    hex_string = "0123456789abcdef"  # Reference table for the hex language to loop through

    for a in hex_string:
        for b in hex_string:
            k1guess = k1 + a + b
            for c in hex_string:
                for d in hex_string:
                    k2guess = k2 + c + d
                    print("Trying k1=" + k1guess + " and k2=" + k2guess)
                    ci = des_encrypt(knownPlaintext, k1guess)
                    de = des_decrypt(ciphertext, k2guess)
                    print(ci)
                    print(de)
                    if ci == de:
                        print("*** MATCH FOUND ***")
                        print("Key 1: " + k1guess)
                        print("Key 2: " + k2guess)
                        return


if __name__ == '__main__':
    main()
