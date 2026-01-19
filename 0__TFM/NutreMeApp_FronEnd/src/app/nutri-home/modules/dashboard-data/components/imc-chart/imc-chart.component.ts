import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'imc-chart',
  templateUrl: './imc-chart.component.html',
  styleUrls: ['./imc-chart.component.scss']
})
export class ImcChartComponent implements OnInit {
  @Input('imc') imc: number = 0; 
  imcText = ''; 
  constructor() { }

  ngOnInit(): void {
    this.calculateImcText(); 
  }

  calculateImcText(){
    if(this.imc <= 18.5){
      this.imcText = 'Peso bajo'
    }else if(this.imc > 18.5 && this.imc <= 24.9){
      this.imcText = 'Peso saludable'
    }else if(this.imc >= 25 && this.imc < 29.9){
      this.imcText = 'Sobrepeso'
    }else{
      this.imcText = 'Obesidad'
    }
  }


}
