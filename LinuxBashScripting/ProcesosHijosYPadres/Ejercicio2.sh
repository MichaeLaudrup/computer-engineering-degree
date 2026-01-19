#!/bin/bash
#este archivo es un script que:
#1 compila los fuentes padre.c e hijo.c con gcc
#2 crea el fihero fifo "resultado"
#lanza un cat en segundo plano para leer "resultado"  
#lanza el proceso padre
#al acabar limpia todos los ficheros que ha creado

gcc ./Trabajo2/padre.c -o ./Trabajo2/padre 
gcc ./Trabajo2/hijo.c -o ./Trabajo2/hijo 
mkfifo ./resultado 
cat ./resultado & 
$(echo ./Trabajo2/padre 10) 
rm ./Trabajo2/padre 
rm ./Trabajo2/hijo 
rm resultado


