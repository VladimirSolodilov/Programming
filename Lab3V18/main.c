#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include <stdlib.h>

int N; //количество считываемых байт
int pid1, pid2; //процессы symbol; //считываемый байт
int symbol;
char * readFile(FILE *, int, int);//чтение файла
void * funcProcess1(FILE *, int);//1 процесс
void * funcProcess2(FILE *, int);//2 процесс

void main(void) {
    pid1 = getpid();//создаём первый процесс
    FILE *file1 = fopen("read.txt", "rb");//открываем файл на чтение
    printf("Enter number of bytes: ");
    scanf("%d", &N);
    if (file1 == NULL) { //проверяем открытие файла
        printf("File isn't open. Return...");
        return;
    } else {
        printf("File is open...\n");
        pid2 = fork();//создаем дочерний процесс
        if (pid2 == 0) {
            pid2 = getpid();//получаем id дочерного процесса
            sleep(1);
            signal(SIGUSR1, funcProcess2(file1, N)); //определяем реакцию для процесса 2
        } else signal(SIGUSR1, funcProcess1(file1, N)); //определяем реакцию для процесса 1
    }
}

void * funcProcess1(FILE *file1, int n) {
    if(strcmp(readFile(file1, n, 1), "EOF") == 0) { //считываем N байт и проверяем окончание файла
        fclose(file1); //закрываем файл
        signal(SIGUSR2, pid2); //посылаем сигнал процессу 2
        printf("\nProcess 2 (%d) completed with code %d\n", pid2, signal(SIGCHLD, pid2)); //завршаем 2 процесс
        printf("Process 1 (%d) completed with code %d\n", pid1, signal(SIGTERM, pid1)); //завршаем 1 процесс
        exit(0);
    } else {
        signal(SIGUSR1, funcProcess2(file1, n)); //передаем сигнал второму процессу
    }
}

void * funcProcess2(FILE *file1, int n) {
    if(strcmp(readFile(file1, n, 2), "EOF") == 0) { //считываем N байт и проверяем окончание файла
        fclose(file1); //закрываем файл
        signal(SIGUSR2, pid1); //посылаем сигнал процессу 1
        printf("\nProcess 2 (%d) completed with code %d\n", pid2, signal(SIGCHLD, pid2)); //завршаем 2 процесс
        printf("Process 1 (%d) completed with code %d\n", pid1, signal(SIGTERM, pid1)); //завршаем 1 процесс
        exit(0);
    } else {
        signal(SIGUSR1, funcProcess1(file1, n)); //передаем сигнал первому процессу
    }
}

char * readFile(FILE *file1, int n, int id) {
    if (id == 1) { //выводим информацию о работающем процессе
        printf("\nProcess %d (%d) read %d bytes: ", id, pid1, n);
    } else {
        printf("\nProcess %d (%d) read %d bytes: ", id, pid2, n);
    }
    for (int i = 0; i < n; i++) {
        symbol = getc(file1);
        if (symbol == EOF) {
            if (feof(file1) != 0) { //проверяем конец файла
                printf("\n\nFile's reading completed...\n");
                return "EOF"; //возвращаем, если конец файла
            } else {
                printf("\nError of file's reading!\n");
            }
        } else printf("%c ", symbol); //печатаем символ
    }
    return "EOR"; //возвращаем, если конец считывания*/
}
