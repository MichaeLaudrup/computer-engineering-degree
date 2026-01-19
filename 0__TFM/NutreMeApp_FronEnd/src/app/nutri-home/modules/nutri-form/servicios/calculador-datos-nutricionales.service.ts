import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CalculadorDatosNutricionalesService {

  constructor() { }

  calculadorMBA(altura: number, peso:number, edad:number, genero:string){
    let MBA: number; 
    if(genero === 'hombre'){
        MBA = Math.trunc(66.5 + (13.76 * peso ) + (5.003 * altura) - (6.755 * edad)); 
    }else{
        MBA = (665 + (9.563 * peso ) + (1.850 * altura) - (4.676 * edad));; 
    }
    return MBA; 
}
}
