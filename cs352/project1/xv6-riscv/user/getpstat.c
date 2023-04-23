// Test Command for the pstat system call
// Checks if the state of running processes are in use, returns the pids and the nicevalues
// of all running processes.
#include "kernel/types.h"
#include "kernel/pstat.h"
#include "user/user.h"

int main() {
  struct pstat stats;
  getpstat(&stats); // print the arrays in stats

  for (int i = 0; i < NPROC; i++) {
    printf("inuse: %d, pid: %d, nice value: %d, runtime: %d, stride: %d, pass: %d\n", stats.inuse[i], stats.pid[i], stats.nice[i], stats.runtime[i], stats.stride[i], stats.pass[i]);
  }

  exit(0);
}
