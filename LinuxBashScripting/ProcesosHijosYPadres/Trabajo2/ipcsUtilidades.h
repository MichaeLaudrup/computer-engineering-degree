#ifndef IPCSUTILIDADES_H
#define IPCSUTILIDADES_H
#include <stdio.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include "ipcsUtilidades.c"


typedef struct  {
    long tipo; 
    char cadena[9]; 
} tMensajeCola;

int init_sem (int semid, ushort valor); 
int wait_sem (int semid);
int signal_sem(int semid); 
int crearColaDeMensajes(key_t llave); 
int obtenerIDMemoriaCompartida(key_t llave, int n);
int crearInicializarSemaforo(key_t llave); 

#endif

