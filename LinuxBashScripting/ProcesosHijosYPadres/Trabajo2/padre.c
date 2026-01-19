//este archivo es el fichero fuente que al compilarse produce el ejecutable PADRE

#include <sys/sem.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "ipcsUtilidades.h"
int buscarGanador(int *listaPids,int n){
    for(int i = 0; i < n; i++){
        if(listaPids[i] != 0){
            return listaPids[i];
        }
    }
    return -1;
}
//============================
//PROGRAMA PRINCIPAL
//==========================
int main(int argc, char *argv[]){
    int n,pid,status;  //N indica numero de hijos a crear, status es una variable que va determinadno el estado al solicitar la terminacion de un proceso
    tMensajeCola mensajeCola;
    int longitud = sizeof(mensajeCola) -sizeof(mensajeCola.tipo);
    mensajeCola.tipo = 2;
    if(argc==2){
        printf("Para generar la llave se ha introducido ruta de fichero : %s \n", argv[0]);
        n = atoi(argv[1]);
        printf("Se ha introducido como valor de n : %d \n", n);
        fflush(stdin);
    }else{
    	printf("Error en el numero de argumentos recibidos al ejecutar PADRE, asegurese de mandar unicamente como argumentos un numero natural\n");
    }

    key_t llave; // La llave nos permite el acceso al recurso.
    if((llave = ftok(argv[0],'X'))==-1) {  //La ruta de acceso a un fichero (numero i nodo) que debe existir en el sistema y un caracter
	perror("Ftok");                       //Se muestra error en la funcion ftok en caso de que no se haya realizado con exito y finaliza el programa
        exit(1);
    }
    printf("=====================================\n");
    printf("Llave:  %d \n", llave);             //Se imprime por consola la llave generada
    int msqid = crearColaDeMensajes(llave);
    printf("Cola de mensajes id: %d \n", msqid);

    int shmid = obtenerIDMemoriaCompartida(llave,n); //Este metodo esta en ipcsUtilidades.h
    printf("Memoria compartida id:  %d \n", shmid);
    int *lista;
    lista = shmat(shmid,0,0);

    int semid = crearInicializarSemaforo(llave); //Este metodo esta en ipcsUtilidades.h
    printf("Semaforo id:  %d \n", semid);
    int barrera[2];
    int resultadoCreaciontuberia = pipe(barrera);    //Creacion de tuberia
    if(resultadoCreaciontuberia == -1){
        perror("La creacion de la tuberia sin nombre no se ha realizado con exito");
    }else{
        printf("Tuberia descriptor lectura: %d ;descriptor escritura: %d \n", barrera[0], barrera[1]);
    }
    printf("======================================\n");  //FIN DE INICIALIZACION DE MECANISMOS IPC
    fflush(stdin);
    int pidResultanteFork;
    for(int i = 0; i < n; i++){
       pidResultanteFork = fork();
       if(pidResultanteFork == 0){
           char llaveEnTexto[15]; //Variable para almacenar la llave en formato texto
           sprintf(llaveEnTexto,"%d",llave); //Se formatea a cadena texto la variable llave de tipo entero y se almacena en la variable llaveEnTexto
           char tuberiaLeer[5]; //El proceso hijo solo necesita leer, no necesita escribir en la tuberia
           sprintf(tuberiaLeer,"%d",barrera[0]); //Casting del descriptor de tuberia para leer
           char *const argumentos[] = {argv[1], llaveEnTexto, tuberiaLeer, NULL};
           execv("./Trabajo2/hijo", argumentos);
           i = n; //Ruptura del bucle
       }else if(pidResultanteFork == -1){
           perror("Error en la creacion de un proceso hijo ");
           exit(1);
       }else{ //PADRE
           wait_sem(semid);//Espera a que el semaforo este disponible
   	       lista[i] = pidResultanteFork; //insertar pid de nuevo hijo #region critica
           signal_sem(semid);//Liberar el semaforo
       }
    }


    sleep(1); //Se espera a que todos los procesos esten en la barrera esperando mensaje (con objeto de sincronizacion)
    if(pidResultanteFork > 0){ //Codigo del padre
        int proc_muertos;
        int num_procVivos = n; //inicialmente estan todos los procesos vivos
        bool fin = false;
        char mensaje1[20]; //Mensaje para imprimir en archivo fifo
        do{
            proc_muertos = 0;
            printf("==========================\n");
            printf("Iniciando ronda de ataques\n");
            printf("==========================\n");
            fflush(stdout);
            char mensaje[num_procVivos];
            for (int i = 0; i < num_procVivos; i++){
                int numAleatorio;
                numAleatorio = rand()%1000; //Se intenta que nada numero aleatorio cambien en cada vuelta para que no se repitan patrones
                sprintf(mensaje,"%d",numAleatorio);
                write(barrera[1], mensaje, strlen(mensaje)); //Se escribe mensaje en tuberia
                usleep(1); //El tiempo entre mensaje y mensaje es de 5 ns
            }
            int mensajesPendientes = num_procVivos;
            usleep(300000); //Se esperan 3 milsegundos a que acabe la contienda actual
            while(mensajesPendientes > 0  ) { //COMIENZO DEL PROCESAMIENTO DE LA COLA DE MENSAJES DESPUES DE LA CONTIENDA
		if(msgrcv(msqid, &mensajeCola, longitud, 2,0) == -1){
                    perror("Error en la lectura de un mensaje: ");
                }else{
                    if((mensajeCola.cadena[0])== 'K' ){   //Esta muy controlado el formato de mensaje recibido,tal que los dos primeros caracteres deben ser "KO" o "OK"
                        char pid_hijo_texto[6];
                        for(int i = 2; i <= 7; i++){ //Se cogen los ultimos 6 digitos del mensaje recibido
                            pid_hijo_texto[i-2] = mensajeCola.cadena[i];
                        }
                        int pid_hijo_num = atoi(pid_hijo_texto);
                        printf("Asesinato del proceso con pid %d\n", pid_hijo_num);
                        if(kill(pid_hijo_num, SIGTERM) == -1){
                            printf("Error en la terminacion del proceso con pid %d", pid_hijo_num);
                            perror("SIGTERM");
                            exit(0);
                        }
                        waitpid(pid_hijo_num, &status,0);
                        for(int i = 0; i < n ; i++){
			    wait_sem(semid);
                            if(lista[i] == pid_hijo_num) {
                            	lista[i] = 0;
                            }
                            signal_sem(semid);
			}
                        num_procVivos--;
                        proc_muertos++;
                    }//FIN DE TRATAMIENTO EN CASO DE QUE UN PROCESO HAYA ESTADO KO
                }
                mensajesPendientes--;
	   } //FIN DEL PROCESAMIENTO DE LA COLA DE MENSAJES
	   printf("==== RESULTADO DE CONTIENDA ====\n");
           printf(" Numero procesos supervivientes: %d\n", num_procVivos);
           printf(" Numero procesos muertos en esta ronda: %d\n",proc_muertos );
           printf(" GANADOR: ");
           if(num_procVivos == 0){
	         	printf("empate\n");
                sprintf(mensaje1, "Empate\n");
                fin = true;
           }else if(num_procVivos == 1){
                int ganador = buscarGanador(lista,n);
             	printf("%d\n",ganador);
                sprintf(mensaje1, "El hijo %d ha ganado\n", ganador);
                kill(ganador,SIGTERM);
                waitpid(ganador,&status,0);
                fin = true;
           }else{
                printf("Sin determinar\n");
           }
           printf("================================\n");
           fflush(stdout);
        }while(!fin);

        //printf("Mensaje que se debe almacena en la tuberia FIFO: %s", mensaje1); //Depurando el mensaje esta bien
        char *direccion = "./resultado";
        int descriptor_tuberia;
        descriptor_tuberia = open("./resultado", O_WRONLY);
        write(descriptor_tuberia, mensaje1, strlen(mensaje1)+1);
        close(descriptor_tuberia);
	system("ipcrm -a");  // Se eliminan todos los recursos propios de mecanismos IPCS
        system("ipcs -qs");  //Se muestra por salida los mecanismos ipcs de tipo cola y de tipo semaforo que existen en el sistema
        shmctl(shmid,IPC_RMID,0);
        semctl(semid,IPC_RMID,0);
        msgctl(msqid,IPC_RMID,0);
        close(barrera[0]);
        close(barrera[1]);
    }
    shmdt(lista);
    exit(0);
}





