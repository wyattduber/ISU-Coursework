// A program to set a "nice value", etc between -20 and 19
// executes the program passed through as arguments when/if the value is between that range

#include "kernel/types.h"
#include "kernel/stat.h"
#include "user/user.h"

int
main(int argc, char *argv[])
{
  // If the arguments passed in the nice command are less than 4, then end the program with an error. There should be at least 4 arguments
  if (argc < 4) exit(0);

  int pid = atoi(argv[1]); // Read the string of the given nicevalue

  if (!nice(pid)) { // If the value is between -20 and 19 for that given nicevalue,
    exec(argv[2], &argv[2]); // Then execute the program with the following arguments
  }

  return 0;
}
