# ISU Lottery generates two lottery numbers every other day.
# The formula for generating these random numbers has been kept secret until
# a curious cpre231 student hacked into their database and leaked the
# code they used to generate their lottery numbers
import random
import time
# Generates a winning lottery number for today and tomorrow
def generateLottery(dateOfGeneration, timeInSeconds):
    # timeInSeconds = [0, 1, ..., 86400]
    # dateOfGeneration = MM/DD/YYYY
    lotteryNumbers = []
    # Add the date and the seconds to get the seed
    random.seed(int(dateOfGeneration.replace("/", "")) + timeInSeconds)
    # Generate lottery number for the current day
    randomNumbers = []
    for i in range(10):
        randomNumbers.append(str(random.randrange(0, 10)))
    lotteryNumbers.append('-'.join(randomNumbers))
    # Generate lottery number for the next day
    randomNumbers = []
    for i in range(10):
        randomNumbers.append(str(random.randrange(0, 10)))
    lotteryNumbers.append('-'.join(randomNumbers))
    # Return an array of two values
    # lotteryNumbers[0] = Today's lottery number
    # lotteryNumbers[1] = Tomorrow's lottery number
    return lotteryNumbers

def convert(seconds):
    seconds = seconds % (24 * 3600)
    hour = seconds // 3600
    seconds %= 3600
    minutes = seconds // 60
    seconds %= 60
    time = ""

    if hour > 12:
       hour = hour - 12
       time = "PM"
    else:
       time = "AM"

    return f"%d:%02d:%02d {time}" % (hour, minutes, seconds)

# Written by Wyatt Duberstein
def main():
    t = time.time()
    for i in range(0, 86401):
        tmp = generateLottery("11/11/2022", i)
        print("November 11th 2022 Ticket at " + str(i) + " seconds is " + tmp[0])
        if tmp[0] == '5-4-3-1-2-9-1-4-2-5':
            print("*** MATCH FOUND ***")
            print("The ticket for November 12th will be: " + tmp[1])
            print("Seconds: " + str(i))
            print("Date/Time: November 12th, at " + convert(i))
            print("Time taken to find: " + str(round((time.time() - t), 3)) + "s")
            return

if __name__ == '__main__':
    main()
