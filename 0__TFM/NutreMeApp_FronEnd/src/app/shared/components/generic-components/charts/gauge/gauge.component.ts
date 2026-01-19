import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-gauge',
  templateUrl: './gauge.component.html',
  styleUrls: ['./gauge.component.scss']
})
export class GaugeComponent implements OnInit {
  @Input('imc') imc : number = 24.5; 
  rotation: string = 'rotate(0.3turn)'; 
  constructor() { }

  ngOnInit(): void {
    this.rotation = `rotate(${(this.imc / 55) / 2}turn)`
  }

}
