msg = input("Enter the message to shift: ")  # Input the message
shift = int(input("Amount to shift message characters by: "))  # Input the amount to shift characters by
newMsg = ""  # Placeholder for string to be built
msg = msg.upper()  # Convert the message to all uppercase characters
for i in range(len(msg)):  # Loop through all the characters to shift
    num = ord(msg[i])  # Assign ASCII Integer value of the char
    if (num + shift) > 90:  # If the ASCII number is higher than 'Z', then wrap around to 'A'
        dif = (num + shift) - 91  # Find the difference once shifted to 'A'
        char = chr(ord('A') + dif)  # Find new char once shifted to 'A' and the difference is added
    else:  # If not shifted past A, then shift like normal
        char = chr(ord(msg[i]) + shift)  # Shift char by 'shift' characters
    newMsg += char  # Add the new char to the output message
print("Encoded Message:", newMsg)  # Print the final shifted message
