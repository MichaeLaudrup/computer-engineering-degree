#include "ipcsUtilidades.h"

int init_sem (int semid, ushort valor){
    ushort sem_Array[1]; 
    sem_Array[0]=valor; 
    if(semctl(semid,0,SETALL,sem_Array)==-1){
    	perror("Error en la inicializacion del semaforo");
        return -1; 
    } 
    return 0;  
}
int wait_sem (int semid){
    struct sembuf op[1]; 
    op[0].sem_num=0; 
    op[0].sem_op=-1; 
    op[0].sem_flg=0; 
    if(semop(semid,op,1) == -1){
        perror("Error en la espera del semaforo");
        return -1;      
    }else{
        return 0; 
    }

}
int signal_sem(int semid){
    struct sembuf op[1]; 
    op[0].sem_num=0; 
    op[0].sem_op=1; 
    op[0].sem_flg=0; 
     if(semop(semid,op,1) == -1){
        perror("Error en la espera del semaforo");
        return -1;      
    }else{
        return 0; 
    }
} 

int crearColaDeMensajes(key_t llave){
    int colaMensajesID = msgget(llave, IPC_CREAT| 0600);
    if (colaMensajesID == -1) {
        perror("semid");
        exit(2);
    }
    return colaMensajesID;
}

int obtenerIDMemoriaCompartida(key_t llave, int n){

    int memoriaCompartidaID = shmget(llave,n*sizeof(int), IPC_CREAT| 0600);
    if(memoriaCompartidaID == -1){
	perror("Error en la creacion de mecanismo IPC de tipo memoria compartida");
        exit(2);
    }
    return memoriaCompartidaID;
}

int crearInicializarSemaforo(key_t llave){
    int semaforoID = semget(llave,1, IPC_CREAT| 0600);
    if(semaforoID == -1){
    	perror("Error en la creacion de mecanismo IPC de tipo semaforo: ");
        exit(2);
    }
    init_sem(semaforoID,1);
    return semaforoID;
}
