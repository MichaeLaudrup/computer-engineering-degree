#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/msg.h>
#include <string.h>
#include <stdbool.h>
#include "ipcsUtilidades.h"
#include <math.h>
char estado[3];

void enviarMensaje(int pid, char estadoo[],int msqid){
    char pidTexto[6]; //Normalmente un pid tiene maximo 5 digitos, es raro ver un pid de 6 digitos, de todas formas se pondra 6 digitos por si las moscas
    sprintf(pidTexto, "%d", pid);
    tMensajeCola mensajeCola;
    int longitud = sizeof(mensajeCola) -sizeof(mensajeCola.tipo);
    mensajeCola.cadena[0] = estado[0];
    mensajeCola.cadena[1] = estado[1];
    for(int i = 2; i <= 7; i ++) {
        mensajeCola.cadena[i] = pidTexto[i-2];
    }
    mensajeCola.cadena[8] = '\0'; //Marca de fin de cadena
    mensajeCola.tipo = 2;
    if(msgsnd(msqid, &mensajeCola, longitud, 0) == -1){
        perror("Ha habido un error durante el envio de un mensaje: ");
        exit(2);
    }
}
void defensa( int sig){
printf("El hijo %d ha repelido un ataque\n", getpid());
strcpy(estado, "OK");

}

void indefenso(int sig) {
printf("El hijo %d ha sido emboscado mientras realizaba un ataque\n",getpid() );
strcpy(estado, "KO");
}


//este archivo es el fichero fuente que al compilarse produce el ejecutable HIJO
int main(int argc, char *argv[]){
    //===CONVERSION DE ARGUMENTOS A FORMATO ADECUADO Y RECUPERACION DE MECANISMOS IPC
    int n = atoi(argv[0]); //Se hace una conversion de texto a entero del valor de N recibido como argumento
    int llave = atoi(argv[1]);  //se hace una conversion de texto a entero del valor de llave recibido como argumento
    int barreraLectura = atoi(argv[2]); //Se hace una convercion de string a entero del descriptor de la tuberia para leer
    int pidHijoActual = getpid();  //Se almacena el pid del hijo actual
    barreraLectura=atoi(argv[2]);
    int semid = semget(llave,0,0);
    int msqid = msgget(llave,0);
    int *arrayPidsHijos;
    int shmid = shmget(llave,0, 0);
    arrayPidsHijos = shmat(shmid,0,0);
    printf("El hijo con pid %d ha llegado a la barrera y esta esperando a recibir un mensaje\n", pidHijoActual);
    char mensajeBarrera[n];
    bool finRondas = false;
    do{
        if((read(barreraLectura, mensajeBarrera, n) > 0)){
            int aleatorio = atoi(mensajeBarrera); //El numero aleatorio le llega por la barrera a traves del padre en cada ronda
            abs(aleatorio);
            strcpy(estado, "OK"); //Hay procesos que no sufren ninguna ataque y por lo tanto su estado no puede ser la cadena vacia si no el estado OK
            if(aleatorio%2 == 0) {
                printf("El proceso con pid: %d esta en modo ataque. \n", pidHijoActual);
                signal(SIGUSR1, indefenso);
                usleep(100000);
                fflush(stdout);
                int pidBajoAtaque;
                int aux = 0;  //rodamos cero posiciones inicialmente a partir del aleatorio obtenido
                bool pidEncontrado =false; //Se parte de la premisa de que el pid no se ha encontrado
                do{
                    int indice = ((aleatorio)+aux)%n; //Obtenemos indice del vector, rodando una posicion en cada vuelta en caso de ser el pid = 0 o igual al getpid() actual
                    wait_sem(semid); //se espera a disponibilidad de semaforo
                    pidBajoAtaque = arrayPidsHijos[indice]; //Se asigna como pid a atacar el numero aleatorio recibido por mensaje a traves de la barrera
                    signal_sem(semid); //Se libera semaforo
                    if( (pidBajoAtaque != 0) && (pidBajoAtaque != pidHijoActual) ){ //Si el pid actual es diferente de cero y del pid que ejecuta este codigo entonces se selecciona como encotnra
                        pidEncontrado = true;
                    }else{
                        aux = aux + 1;
                    }
                }while(!pidEncontrado);
                printf("El proceso con id: %d va a atacar al proceso con id: %d\n", pidHijoActual, pidBajoAtaque);
                kill(pidBajoAtaque, SIGUSR1);
                usleep(100000);
                enviarMensaje(pidHijoActual,estado,msqid);
            }else{
                printf("El proceso con pid: %d esta en modo defensa. \n",pidHijoActual);
                fflush(stdout);
                signal(SIGUSR1, defensa);
                usleep(200000);
                enviarMensaje(pidHijoActual,estado,msqid);
            }
        }else{
            perror("Error al leer la tuberia: ");
            exit(0);
        }
    }while(1); //Lo va a estar haciendo infinitas veces hasta que el proceso padre le mande una SIGTERM
    shmdt(arrayPidsHijos); //Se desvincula la region de memoria

}




