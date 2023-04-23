#include <stdio.h>
#include <assert.h>
#include <string.h>
#include <stdlib.h>

// maximum number of integers stored in buffer
#define MAX_BUF_SZ 1024

int srcBuffer[MAX_BUF_SZ];	// source buffer
int dstBuffer[MAX_BUF_SZ];	// destination buffer

// randomly fill buffer
void fillBuffer_srcBuffer() {
    for(int i = 0; i < MAX_BUF_SZ; ++i) {
        srcBuffer[i] = rand() * 100;
    }
}

int main(int argc, char** argv) {
    if(argc < 2) {
        printf("Usage - ./bufferOverflow [Number of Integers to Copy]\n");
        return -1;
    }
    assert(sizeof(int)==4); // make sure we are working on machine with (sizeof(int) = 4)

    fillBuffer_srcBuffer();

    int copySize = atoi(argv[1]);
    if(copySize > MAX_BUF_SZ || copySize < 0) {
        printf("You cannot copy more than (%d) integers from our buffer\n", MAX_BUF_SZ);
        return -1;
    }
    printf("About to copy (%u) integers from our buffer\n", copySize);
    memcpy(dstBuffer, srcBuffer, copySize * sizeof(int));
    return 0;
}
