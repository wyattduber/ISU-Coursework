from PIL import Image

def main():
    # Read the image from a file
    img = Image.open('image3.png', 'r')

    # Obtain the pixels
    pixels = img.load()
    (width, height) = img.size
    binary = ""  # Binary leads with 0
    letter = ""
    letter_bin = "0"

    # Find the lsb in each pixel
    for x in range(width):
        for y in range(height):

            # End of message
            if binary.endswith('0000101000001010'):
                break

            # Obtain an array of 3 bytes as a pixel
            # pixel = (pixel[0], pixel[1], pixel[2])
            pixel = pixels[x, y]

            # Convert pixel to binary
            bin_char = str(bin(pixel[2]))[-1]
            binary += bin_char

            # Add the char to the 8-char binary string
            # If the string is length 8, reset it with the new binary char
            # If the string is less than 8, append it to the string
            if len(letter_bin) < 8:
                letter_bin += bin_char
            else:
                letter += chr(int(letter_bin, 2))
                letter_bin = bin_char

    # Print the resulting message
    print(letter)


if __name__ == "__main__":
    main()
