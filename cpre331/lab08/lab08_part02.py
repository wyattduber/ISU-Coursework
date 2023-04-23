import numpy as py

SUB_BYTE = {'0': 'a', '1': '4', '2': '3', '3': 'b', '4': '8', '5': 'e', '6': '2', '7': 'c', '8': '5', '9': '7', 'a':
            '6', 'b': 'f', 'c': '0', 'd': '1', 'e': '9', 'f': 'd'}

MIX_COLUMN = [['1', '0', '1', '0', '0', '0', '1', '1'],
              ['1', '1', '0', '1', '0', '0', '0', '1'],
              ['1', '1', '1', '0', '1', '0', '0', '0'],
              ['0', '1', '0', '1', '0', '1', '1', '1'],
              ['0', '0', '1', '1', '1', '0', '1', '0'],
              ['0', '0', '0', '1', '1', '1', '0', '1'],
              ['1', '0', '0', '0', '1', '1', '1', '0'],
              ['0', '1', '1', '1', '0', '1', '0', '1']]

ROUND_KEYS = {0: [["6", "5"],
                  ["b", "d"]],
              1: [["6", "3"],
                  ["5", "8"]],
              2: [["1", "2"],
                  ["e", "6"]],
              3: [["7", "5"],
                  ["d", "b"]],
              4: [["0", "5"],
                  ["3", "8"]]}


def state_to_bin_state(state):
    bin_state = [["0", "0"],
                 ["0", "0"],
                 ["0", "0"],
                 ["0", "0"],
                 ["0", "0"],
                 ["0", "0"],
                 ["0", "0"],
                 ["0", "0"]]
    for i in range(0, 2):
        b1 = bin(int(state[0][i], 16))[2:]  # Take the binary of the hex characters
        b2 = bin(int(state[1][i], 16))[2:]
        if len(b1) < 4:  # Pad extra spaces with 0s
            for e in range(0, (4 - len(b1))):
                b1 = '0' + b1[0:]
        if len(b2) < 4:
            for e in range(0, (4 - len(b2))):
                b2 = '0' + b2[0:]
        for j in range(0, 4):  # Assign binary values to table in order
            bin_state[j][i] = b1[j]
            tmp = j + 4
            bin_state[tmp][i] = b2[j]

    return bin_state


def bin_state_to_state(bin_state):
    state = [["0", "0"],
             ["0", "0"]]
    for i in range(0, 2):
        string = ""
        string += bin_state[0][i]
        string += bin_state[1][i]
        string += bin_state[2][i]
        string += bin_state[3][i]
        state[0][i] = str(hex(int(string, 2)))[2:]
        string = ""
        string += bin_state[4][i]
        string += bin_state[5][i]
        string += bin_state[6][i]
        string += bin_state[7][i]
        state[1][i] = str(hex(int(string, 2)))[2:]
    return state


def text_to_state(text):
    state = [[text[0], text[2]],
             [text[1], text[3]]]
    return state


def sub_byte_method(state):
    i = 0
    for a in state:
        j = 0
        for b in a:
            state[i][j] = SUB_BYTE.get(b)
            j += 1
        i += 1
    return state


def shift_row(state):
    tmp = state[1][0]
    state[1][0] = state[1][1]
    state[1][1] = tmp
    return state


def round_key(state, key_num):
    round_bin_state = [["0", "0"],
                       ["0", "0"],
                       ["0", "0"],
                       ["0", "0"],
                       ["0", "0"],
                       ["0", "0"],
                       ["0", "0"],
                       ["0", "0"]]
    bin_state = state_to_bin_state(state)
    key_bin_state = state_to_bin_state(ROUND_KEYS[key_num])
    for i in range(0, 2):
        for j in range(0, 8):
            round_bin_state[j][i] = str(int(bin_state[j][i], 2) ^ int(key_bin_state[j][i], 2))
    return bin_state_to_state(round_bin_state)


def mix_column(state):
    bin_state = state_to_bin_state(state)
    mix_state = [["0", "0"],
                 ["0", "0"],
                 ["0", "0"],
                 ["0", "0"],
                 ["0", "0"],
                 ["0", "0"],
                 ["0", "0"],
                 ["0", "0"]]

    for j in range(0, 2):
        tmp = []
        for i in range(0, 8):
            tmp.append(bin_state[i][j])
        tmp = list(map(int, tmp))
        for i in range(0, 8):
            tmp2 = list(map(int, MIX_COLUMN[i]))
            mix_state[i][j] = py.dot(tmp2, tmp) % 2
    for i in range(0, 8):
        mix_state[i] = list(map(str, mix_state[i]))

    return bin_state_to_state(mix_state)


def print_state(state):
    print("Ciphertext: " + state[0][0] + state[1][0] + state[0][1] + state[1][1])


def main():
    plaintext = input("Enter the 4 hex digit plaintext to encrypt: ")
    state = text_to_state(plaintext)
    state = round_key(state, 0)
    for i in range(0, 4):
        state = sub_byte_method(state)
        state = shift_row(state)
        if i != 3:
            state = mix_column(state)
        state = round_key(state, i + 1)
    print_state(state)


if __name__ == "__main__":
    main()
