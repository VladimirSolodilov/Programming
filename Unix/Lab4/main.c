#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdbool.h>

#define SEM_ID 2001 //ключ массива семафоров
#define SHM_ID_ARR 2002 //ключ разделяемой памяти

int main() {
    FILE *file = fopen("text.txt", "w+"); //открываем файл для записи
    int *array; //массив 2 * int
    int pid = getpid();//инициализация 1-го потока
    int pid2, pid3; //создание 2-го и 3-го потока
    int number = 1; //записываемое число
    int writeRegister = 0; //число записей в текстовый документ

    //IPC_CREAT - создание новой очередей сообщений с ключом, 0666 - чтение и запись для всех пользователей и групп
    //int semget(key_t key, int nsems, int semflg) - считывает идентификатор набора семафоров и получает доступ к ним
    int semid = semget(SEM_ID, 1, IPC_CREAT | 0666);

    //int shmget (IPC_PRIVATE, SEGMENT_SIZE, IPC_CREAT | IPC_EXCL | S_IRUSR | S_IWUSR) - присваивает идентификатор разделяемому сегменту памяти
    int shmid = shmget(SHM_ID_ARR, 2 * sizeof(int), IPC_CREAT | 0666); //выделение 2 int общей памяти

    array = (int *) shmat(shmid, 0, 0); //присоединение области разделяемой памяти к виртуальному адресному пространству

    semctl(semid, 0, SETVAL, 0);//инициализация семафоровов

    //инициализация 2-го и 3-го дочерних потоков
    pid2 = fork();
    pid3 = fork();

    while (true) {
        if (semctl(semid, 0, GETVAL, 0)) continue;  // ожидание процессов
        semctl(semid, 0, SETVAL, 1); // установка значения на выполнение процессов

        //switch для записи данных в файл
        switch (number) {
            case 1: {
                printf("Process %d (%d) write %d \n", number, pid, number);
                array[0] = number;
                number++;
            }
            case 2: {
                printf("Process %d (%d) write %d \n", number, pid2, number);
                array[1] = number;
            }
            case 3: {
                //Определение записываемых чисел
                switch (writeRegister) {
                    case 0: case 2: {
                        printf("Process 3 (%d) write in the file: %d и %d\n\n", pid3, array[0], array[0]);
                        fprintf(file, "%d%d", array[0], array[0]); //запись
                        break;
                    }
                    case 1: case 3: {
                        printf("Process 3 (%d) write in the file: %d и %d\n\n", pid3, array[1], array[1]);
                        fprintf(file, "%d%d", array[1], array[1]); //запись
                        break;
                    }
                }

                //обнуление разделяемой памяти
                array[0] = 0;
                array[1] = 0;
                writeRegister++; //увеличение счётчика записанных данных

                switch(writeRegister) {
                    case 4: {
                        shmdt(array); //очистка массива int
                        exit(0); //завершение процессов
                    } default: {
                        number = 1; //возврат числа к исходному значению
                        semctl(semid, 0, SETVAL, 0); //установка на блокировку
                    }
                }
            }
        }
    }
}
