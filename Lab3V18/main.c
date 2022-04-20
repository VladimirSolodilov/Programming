#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>

int N = 15;
int pid1, pid2, symbol;
int readFile(FILE *, int, int);
void * funcProcess1(FILE *, int);
void * funcProcess2(FILE *, int);

int main(void) {
    pid1 = getpid();
    FILE *file1 = fopen("file1.txt", "r+");
    pid2 = fork();

    if (pid2 == 0) {
        pid2 = getpid();
    } else {
        signal(SIGUSR1, funcProcess1(file1, N));
    }
}

void * funcProcess1(FILE *file1, int n) {
    if(readFile(file1, n, 1) == 2) {
        kill(pid2, SIGUSR2);
        exit(0);
    } else {
        signal(SIGUSR1, funcProcess2(file1, n));
    }
    return NULL;
}

void * funcProcess2(FILE *file1, int n) {
    if(readFile(file1, n, 2) == 2) {
        kill(pid1, SIGUSR2);
        exit(0);
    } else {
        signal(SIGUSR1, funcProcess1(file1, n));
    }
    return NULL;
}

int readFile(FILE *file1, int n, int id) {
    if (id == 1) {
        printf("\nProcess %d (%d) read %d bytes: ", id, pid1, n);
    } else {
        printf("\nProcess %d (%d) read %d bytes: ", id, pid2, n);
    }

    for (int i = 0; i < n; i++) {
        symbol = getc(file1);
        if (symbol == EOF) {
            if (feof(file1) != 0) {
                printf("\nFile's reading completed...\n");
                return 2;
            } else {
                printf("\nError of file's reading!\n");
            }
        }
        printf("%d ", symbol);
    }

    return 1;
}