#include "types.h"
#include "riscv.h"
#include "defs.h"
#include "date.h"
#include "param.h"
#include "memlayout.h"
#include "spinlock.h"
#include "proc.h"
#include "pstat.h"

uint64
sys_exit(void)
{
  int n;
  if(argint(0, &n) < 0)
    return -1;
  exit(n);
  return 0;  // not reached
}

uint64
sys_getpid(void)
{
  return myproc()->pid;
}

uint64
sys_fork(void)
{
  return fork();
}

uint64
sys_wait(void)
{
  uint64 p;
  if(argaddr(0, &p) < 0)
    return -1;
  return wait(p);
}

uint64
sys_sbrk(void)
{
  int addr;
  int n;

  if(argint(0, &n) < 0)
    return -1;
  addr = myproc()->sz;
  if(growproc(n) < 0)
    return -1;
  return addr;
}

uint64
sys_sleep(void)
{
  int n;
  uint ticks0;

  if(argint(0, &n) < 0)
    return -1;
  acquire(&tickslock);
  ticks0 = ticks;
  while(ticks - ticks0 < n){
    if(myproc()->killed){
      release(&tickslock);
      return -1;
    }
    sleep(&ticks, &tickslock);
  }
  release(&tickslock);
  return 0;
}

uint64
sys_kill(void)
{
  int pid;

  if(argint(0, &pid) < 0)
    return -1;
  return kill(pid);
}

// return how many clock tick interrupts have occurred
// since start.
uint64
sys_uptime(void)
{
  uint xticks;

  acquire(&tickslock);
  xticks = ticks;
  release(&tickslock);
  return xticks;
}

// Check if a process has a nice value (between -20 and 19)
uint64
sys_nice(void)
{
  int n;
  argint(0, &n);

  struct proc *p = myproc();

  if(n < -20 || n > 19)
    return -1;

  p->nicevalue = n;
  p->stride = nice_to_tickets[n + 20];
  return 0;
}

// Returns statistics of running processes
uint64 sys_getpstat(void) {
  uint64 result = 0;
  struct proc *p = myproc();
  uint64 upstat; // the virtual (user) address of the passed argument struct pstat
  struct pstat kpstat; // a struct pstat in kernel memory

  // get the system call argument passed by the user program
  if (argaddr(0, &upstat) < 0)
    return -1;

  // TODO: fill the arrays in kpstat (see the definition of struct pstat above).

  for (int i = 0; i < NPROC; i++) { // loops through all of the processes and the elements in the kpstat struct arrays
    kpstat.nice[i] = proc[i].nicevalue; // sets the nicevalue in the kpstat array
    kpstat.pid[i] = proc[i].pid; // sets the pid in the kpstat array
    kpstat.pass[i] = proc[i].pass; // sets the pass value in the kpstat array
    kpstat.stride[i] = proc[i].stride; // sets the stride value in the kpstat array
    kpstat.runtime[i] = proc[i].runtime; // sets the runtime value in the kpstat array
    if (proc[i].state != UNUSED) { // check to see if the state of a process is anything other than UNUSED
      kpstat.inuse[i] = 1; // If used, set to 1
    } else {
      kpstat.inuse[i] = 0; // If not used, then set to 0
    }
  }

  // The data to fill in the arrays comes from the process table array proc[].
  // copy pstat from kernel memory to user memory
  if (copyout(p->pagetable, upstat, (char *)&kpstat, sizeof(kpstat)) < 0)
    return -1;

  return result;
}
