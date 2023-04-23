/*
Author: Sullivan Jahnke and Wyatt Duberstein

This is our implementation of a multi-threaded encrypting script. 
In this encrypting script we used a circular buffer that we implemented
ourselves to ensure max concurrency as well as easy I/O handling.
We therefore had an input buffer and output buffer using this structure.
We also used 8 semaphores to ensure stability when performing multiple
tasks at once. We used a single mutex lock to make sure there is a safe
reset without causing errors. This program reaches max concurrency.
*/

#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>
#include <pthread.h>

#include "encrypt-module.h"

/* ******************************************************************************************** */
/* BEGIN CIRCULAR BUFFER IMPLEMENTATION */

/* The struct that implements a ring buffer */
typedef struct ring_buffer {
	char* data;             //The data in the buffer
	unsigned count;         //The count index of the buffer used by the counter threads.
	unsigned read;          //The read index of the buffer
	unsigned write;         //The write index of the buffer 
	unsigned numItems;      //The number of items currently in the buffer
	unsigned size;          //The size of the buffer.
	unsigned full;          //0 if buffer is not full, 1 if it is full
} buffer;

/* Function to create a buffer given a buffersize. Returns a null pointer if malloc is unsuccessful. */
buffer* create_buffer(int bufferSize) {
	buffer* thisBuffer = (buffer*)malloc(sizeof(buffer));
	if(thisBuffer != NULL) {
		thisBuffer->write = 0;
		thisBuffer->read = 0;
		thisBuffer->count = 0;
		thisBuffer->size = bufferSize;
		thisBuffer->data = (char*)malloc(bufferSize * sizeof(char));
	}

	return thisBuffer;
}

/* Function to write a character to a buffer. */
void writeToBuffer(buffer* buf, char c) {
	buf->data[buf->write] = c;
	buf->write = (buf->write + 1) % buf->size;
}

/* Function to read a character from a buffer.
	Returns the character read. */
char readFromBuffer(buffer* buf) {
	char c;
	c = buf->data[buf->read];
	buf->read = (buf->read + 1) % buf->size;
	return c;
}

/* Function to count a character from a buffer.
	Returns the character to count. */
char countFromBuffer(buffer* buf) {
	char c;
	c = buf->data[buf->count];
	buf->count = (buf->count + 1) % buf->size;
	return c;
}

/* Function to print the data stores within the buffer.
   Returns the characters in order of the buffer. */
void printBuffer(buffer* buf) {
	for(int i = 0; i < buf->size; i++) {
		printf("%c ", buf->data[i]);
	}
	printf("\n");
}

/* END CIRCULAR BUFFER IMPLEMENTATION */
/* ******************************************************************************************** */

/* Global Variables */
buffer* inBuffer;
buffer* outBuffer;

sem_t inRead;
sem_t inReadCount;
sem_t inCount;
sem_t inEncrypt;

sem_t outRead;
sem_t outReadCount;
sem_t outCount;
sem_t outWrite;

pthread_mutex_t reset_lock = PTHREAD_MUTEX_INITIALIZER;

void reset_requested() {
	pthread_mutex_lock(&reset_lock);
	log_counts();
}

void reset_finished() {
	pthread_mutex_unlock(&reset_lock);
}

// Reads the input characters one at a time and places them in the input buffer if there is space.
void* reader_thread(void *n) {
	char x;
	while(x != EOF) {
		x = read_input();
		
		sem_wait(&inCount);
		sem_wait(&inEncrypt);
		pthread_mutex_lock(&reset_lock);

		writeToBuffer(inBuffer, x);

		pthread_mutex_unlock(&reset_lock);
		sem_post(&inRead);
		sem_post(&inReadCount);
	}
}

// Counts occurrences of each letter in the input file by looking at each character in the input buffer.
void* input_counter_thread(void *n) {
	char x;
	while(x != EOF) {
		sem_wait(&inReadCount);

		x = countFromBuffer(inBuffer);

		sem_post(&inCount);

		if(x != EOF) count_input(x);
	}
}

// Consumes one character at a type from the input buffer, encrypts it, 
void* encryption_thread(void *n) {
	char x, y;
	while(x != EOF) {
		sem_wait(&inRead);

		x = readFromBuffer(inBuffer);

		sem_post(&inEncrypt);

		if(x != EOF) y = encrypt(x);
		else y = x;

		sem_wait(&outCount);
		sem_wait(&outWrite);

		writeToBuffer(outBuffer, y);

		sem_post(&outRead);
		sem_post(&outReadCount);
	}
}

// Counts occurrences of each letter in the output file by looking at each character in the output buffer.
void* output_counter_thread(void *n) {
	char x;
    int bufferInitCounter = 0;
    while(x != EOF){
		sem_wait(&outReadCount);

        x = countFromBuffer(outBuffer);

		sem_post(&outCount);

		if(x == EOF) break;
		else count_output(x);
    }
}

// Writes the encrypted characters in the output buffer to the output file. 
void* writer_thread(void *n) {
	char x;
	while(x != EOF) {
		sem_wait(&outRead);

		x = readFromBuffer(outBuffer);

		sem_post(&outWrite);

		if(x == EOF) break;
		else write_output(x);
	}

}

/* Runs the main program; initiates the threads, the semaphores, and encrypts the input file.
   Outputs a log file and the output file with the encrypted text. */
int main(int argc, char *argv[]) {
	if(argc != 4) {
		fprintf(stderr, "Usage Error: Not enough command line arguments...\n");
		exit(0);
	}
	init(argv[1], argv[2], argv[3]); 

	int N, M;
	printf("Enter input buffer size: ");
	scanf("%d", &N);
	printf("Enter output buffer size: ");
	scanf("%d", &M);
	if(M < 1 || N < 1) {
		fprintf(stderr, "Usage Error: Buffer size(s) not valid...\n");
		exit(0);
	}

	inBuffer = create_buffer(N);
	sem_init(&inRead, 0, 0);
	sem_init(&inReadCount, 0, 0);
	sem_init(&inCount, 0, N);
	sem_init(&inEncrypt, 0, N);
	outBuffer = create_buffer(M);
	sem_init(&outRead, 0, 0);
	sem_init(&outReadCount, 0, 0);
	sem_init(&outCount, 0, M);
	sem_init(&outWrite, 0, M);

	pthread_t thread_array[5];
	pthread_create(&thread_array[0], NULL, reader_thread, NULL);
	pthread_create(&thread_array[1], NULL, input_counter_thread, NULL);
    pthread_create(&thread_array[2], NULL, encryption_thread, NULL);
    pthread_create(&thread_array[3], NULL, output_counter_thread, NULL);
    pthread_create(&thread_array[4], NULL, writer_thread, NULL);

	for(int i = 0; i < 5; i++) {
		pthread_join(thread_array[i], NULL);
	}

	sem_destroy(&inRead);
	sem_destroy(&inReadCount);
	sem_destroy(&inCount);
	sem_destroy(&inEncrypt);

	sem_destroy(&outRead);
	sem_destroy(&outReadCount);
	sem_destroy(&outCount);
	sem_destroy(&outWrite);

	printf("End of file reached.\n"); 
	log_counts();
}
