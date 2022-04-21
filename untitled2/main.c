#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

int f1(int), f2(int), z(int a); //задаем функции

int pid1, pid2; //задаем id процессов

int i,j=0; //переменные для функций (указывается количество повторений)

void main(void)
{
    FILE *f;
    f = fopen("file.txt", "w+"); //открываем текстовый файл (если нет – создаем)
    fclose(f);
    pid1 = getpid(); //получаем идентификатор процесса
    pid2 = fork(); //создаем дочерний процесс
    if (pid2 == 0) //process 2
    {
        signal(SIGUSR2, f2);//определяем реакцию на сигнал SIGUSR2
        pid2 = getpid();
        sleep(1); //задаем время ожидания – 1 секунда
        kill(pid1, SIGUSR1);//передаём сигнал первому процессу
        for (;;);
    }
    else // process 1
    {
        signal(SIGUSR1, f1);//определяем реакцию на сигнал SIGUSR1
        if (pid2 < 0 ) puts("Fork error");
        if (pid2 > 0)
            for(;;);
    }
}
int f1(int signum) //функция для первого процесса
{
    signal(SIGUSR1, f1);
    if (i<5)
    {
        z(1);
        i++;
        //printf(kill(pid2, SIGUSR2));//отправляем сигнал 2-му процессу
        printf("%d", kill(pid2, SIGUSR2));//отправляем сигнал 2-му процессу
    }
    else
    {
        exit(0);
        return 0;
    }
}

int f2(int signum)
{
    signal(SIGUSR2, f2);
    if (j<5)
    {
        z(2);
        j++;
        kill(pid1, SIGUSR1);//отправляем сигнал первому процессу
    }
    else
    {
        exit(0);
        return 0;
    }
}

int z(int a) //функция для записи в файл
{
    FILE *f;
    f = fopen("file.txt", "a"); //открываем файл
    fprintf(f, "%d", a);
    if(a==1)
        printf("Process %d (%d) write %d\n",a, pid1, a);
    else
        printf("Process %d (%d) write %d\n",a, pid2, a);
    fclose(f);
}