#!/bin/bash
export LC_NUMERIC="en_US.UTF-8" #esto se hace para poder imprimir numeros con punto 

procesos_PIDS="$(ls /proc | awk '/[0-9]+/{print}')"
num_procesos=$(echo $procesos_PIDS | wc -w)
tics=$(getconf CLK_TCK)


#Esta funcion calcula el porcentaje de memoria utilizada de un proceso segun su pid 
#param $1 indice para localizar el proceso en el vector_pids
#return 1 siempre; No se ha primado la programacion robusta en esta practica dado que no es el objetivo
calcularPorcentajeMemoriaUsada (){
   memory_size=$(awk '$1=="MemTotal:" {print $2}' /proc/meminfo)  #se calcula el tamanio de la memoria
   memory_free=$(awk '$1=="MemFree:" {print $2}' /proc/meminfo)   #se calcula el espacio libre en memoria
   memory_ocuped=$(expr $memory_size - $memory_free)              #se calcula memoria en uso en KB
   memory_usage_pid=$(awk '{print $1}' /proc/"${vector_pids[$1]}"/statm)     #se obtiene cuanta memoria en KB ocupa el proceso con pid recibido como parametro

   memory_usage_percent[$1]=$(echo "scale=10; $memory_usage_pid/$memory_ocuped*100" | bc ) #se hace un contraste entre memoria usada por proceso y memoria total usada en tanto por 100
   return 1 #premisa de que la funcion se ejecutara siempre sin problemas
}

#esta funcion calcula el tiempo que un proceso esta ejecutandose en el sistema
calcularTiempoDeEjecucion(){
   start_time=$(awk '{print $22}' /proc/"${vector_pids[$1]}"/stat) #tiempo en el que empieza a ejecutarse un proceso con pid recibido como primer parametro en  tics de reloj
   start_time=$(echo "$start_time/$tics" | bc) #se divide entre 100 que es el numero habitual que valen los tics del reloj
   uptime_system=$(awk '{print int($1)}' /proc/uptime) #tiempo de actividad del sistema en segundos
   execution_time_seconds[$1]=$(echo "$uptime_system-$start_time" | bc)
}

#Esta funcion calcula el uso de CPU tanto a nivel global como a nivel de proceso
function calcularPorcentajeUsoCPU (){
        
	for((j=1; j<= num_procesos;j++))
	do      
		ruta_actual="/proc/"$(echo $procesos_PIDS | awk '{print $('$j')}')                 
		if [ -d $ruta_actual ]; then  #se comprueba que la ruta existe (algunos procesos son creados y eliminados por este propio script y no deben tenerse en cuenta) 
			total_time_t1[$j]=$(awk '{print $14+$15}' $ruta_actual/stat)  #suma de tiempo en modo usuario y modo nucleo de cada proceso en instante "t"
		        time_t1[$j]=$(date +%s.%N) #hora en el instante "t"
		fi
		
	done
    
        total_cpu_t1=$(awk '$1=="cpu"{print $2+$3+$4+$5+$6+$7+$8}' /proc/stat) #suma de todo el uso de CPU en el instante t
        total_cpu_use_t1=$(awk '$1=="cpu"{print $2+$3+$4}' /proc/stat) #se quita el uso de CPU por procesos en modo usuario y modo supervisor en el instante t 
    
	sleep 1   #espera de un segundo
        total_cpu_t2=$(awk '$1=="cpu"{print $2+$3+$4+$5+$6+$7+$8}' /proc/stat) #suma de todo el uso de CPU en el instante t
        total_cpu_use_t2=$(awk '$1=="cpu"{print $2+$3+$4}' /proc/stat) #se quita el uso de CPU por procesos en modo usuario y modo supervisor en el instante "t+1"
        cpu_usage=$(echo "scale=10;($total_cpu_use_t2-$total_cpu_use_t1)/($total_cpu_t2-$total_cpu_t1)*100" | bc) #variacion del uso de cpu en modo nucleo y usuario /variacion del uso CPu total       
	
		
	for((k=1; k<= num_procesos;k++))
	do      
                ruta_actual="/proc/"$(echo $procesos_PIDS | awk '{print $('$k')}') 
                if [ -d $ruta_actual ]; then  #se comprueba que la ruta existe
			total_time_t2[$k]=$(awk '{print $14+$15}' $ruta_actual/stat) #suma de tiempo en modo usuario y modo nucleo de cada proceso en instante "t+1"
		        time_t2[$k]=$(date +%s.%N) #hora en instante "t+1"			
			cpu_use_percent[$k]=$(echo "scale=4; (((${total_time_t2[$k]}-${total_time_t1[$k]})/$tics)/(${time_t2[$k]}-${time_t1[$k]}))*100" | bc) #formula
                        $(echo -e "$k ${cpu_use_percent[$k]}" >> /tmp/temporal_file) #se va almacenando en un fichero temporal el indice del proceso y su %CPU asociado
                        
		fi      
	done 
        sort -nrk2 /tmp/temporal_file > /tmp/temporal_file2 #se ordena el fichero con todos los indices de los procesos segun el valor de %CPU
       
} #fin de funcion para calcular porcentaje de uso de CPU y uso de CPU global

function imprimeCabecera(){
	echo -e "\e[7;34m  PEC 1 DYASO                                    MICHAEL LAUDRUP LUIS GONZALEZ \e[0m"
	echo "Numero de procesos: $num_procesos"
        printf "%17s %5.2f %s \n" "Uso total de CPU:" "$cpu_usage" "%"
        memoriaTotal=$(awk '/MemTotal:/{print $2}' /proc/meminfo )
        memoriaLibre=$(awk '/MemFree:/{print $2}' /proc/meminfo )
	memoriaOcupada=$(echo "$memoriaTotal-$memoriaLibre" | bc)
        echo "Memoria total: $memoriaTotal"
        echo "Memoria ocupada: $memoriaOcupada"
        echo "Memoria libre: $memoriaLibre"
	echo -e "\e[7;34m     PID  USUARIO    PR     VIRTUAL      S   %CPU     %MEM   TIME       COMANDO             \e[0m"
} #fin metodo de impresion de cabecera

#impresion de datos de proceso i-esiomo
function imprimirProcesoIesimo(){
	ruta_actual="/proc/"$(echo $procesos_PIDS | awk '{print $('$1')}') 
                if [ -d $ruta_actual ]; then 
			hours=$(echo ${execution_time_seconds[$1]}/3600 | bc)
			minuts=$(echo "((${execution_time_seconds[$1]}%3600)/60)" | bc)
			seconds=$(echo "((${execution_time_seconds[$1]}%3600)%60)" | bc)
			printf "%8s  %-10s %-6s %-10s %3s  %5.2f%-1s   %5.2f%-1s  %2.2d:%2.2d.%2.2d   %-25s \n" "${vector_pids[$1]}" "${vector_user_names[$1]}" "${vector_priority[$1]}" "${vector_virtualSize[$1]}" "${vector_proc_status[$1]}" "${cpu_use_percent[$1]}" "%" "${memory_usage_percent[$1]}" "%" "$hours" "$minuts" "$seconds" "${vector_comand_invoked[$1]}"
                fi

}


#PROGRAMA PRINCIPAL
for((i=1; i<= $num_procesos;i++))
do 
   ruta_actual="/proc/"$(echo $procesos_PIDS | awk '{print $('$i')}')  #en cada vuelta se actualiza la ruta a /proc/[pid]  
   if [ -d $ruta_actual ]; then  #si existe la ruta se procede a calcular sus datos (hay rutas numericas que desaparecen y aparecen mientras se ejecuta el script y da problemas
	   vector_pids[$i]=$(echo $procesos_PIDS | awk '{print $('$i')}')      # los vectores de datos se alinean segun un indice-i no segun pid, en este caso se asigna el pid    
	   vector_uids_num[$i]=$(awk '$1=="Uid:" {print $2}' $ruta_actual"/status" ) #asignacion de numero de usuario asociado al proceso de indice-i (NO PID)
	   vector_user_names[$i]=$(awk -F:  '$3=="'${vector_uids_num[$i]}'"   {print $1}' "/"etc"/passwd") # se asocia num usuario con nombre de usuario en archivo passwd
           vector_priority[$i]=$(awk '{print $18}' $ruta_actual/stat) # se consulta la prioridad del proceso con indice-i
           vector_virtualSize[$i]=$(awk '{print $23}' $ruta_actual/stat) #se consulta la memoria virtual del proceso con indice-i
           vector_proc_status[$i]=$(awk '{print $3}' $ruta_actual/stat) #se consulta el estado del proceso con indice-i
           calcularPorcentajeMemoriaUsada $i #se calcula el procentaje de memoria usada del proceso con indice.-i
           calcularTiempoDeEjecucion $i #se calcula el tiempo de ejecucion del proceso con indice-i
           vector_comand_invoked[$i]=$(awk '{print $2}' $ruta_actual/stat | tr -d '()')   #se comprueba comando invocador de proceso con indice-i eliminando parentesis que envuelven comando
   fi
done
calcularPorcentajeUsoCPU #se calcula porcentaje de uso de CPU
imprimeCabecera #se imprime la cabecera con datos globales del sistema
for((v=1; v<=10;v++)) 
do
    index=$(awk 'NR == '$v' {print $1}' /tmp/temporal_file2)  #se imprimen solo los 10 primeros indices asociados a procesos que aparezcan en el archivo temporal_2 (tiene ordenado segun %cpu)
    imprimirProcesoIesimo $index #metodo que imprime proceso segun indice de vector de procesos
done

rm /tmp/temporal_file #se borran archivos residuales para que no influencien en posteriores ejecuciones
rm /tmp/temporal_file2 
#FIN PROGRAMA PRINCIPAL













