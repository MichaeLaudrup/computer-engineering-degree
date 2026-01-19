import { Component, HostBinding, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'progress-bar',
  templateUrl: './progress-bar.component.html',
  styleUrls: ['./progress-bar.component.scss']
})
export class ProgressBarComponent implements OnInit, OnChanges {
  @Input() progressValue = 50;
  @Input() maxValue = 100;  
  @Input() withLabel = false; 
  @Input() label = 'Proteinas';
  @Input() units = '%'

  displayValue: number; 
  @HostBinding('style.--actual-value') actualValue = '60%'; 
  constructor() { }

  ngOnInit(): void {
    this.updateValue(); 
  }

  ngOnChanges( changes: SimpleChanges): void {
    this.updateValue()
  }

  updateValue() {
    this.progressValue = Math.trunc(this.progressValue);
    let actualValuePercent = Math.trunc((this.progressValue) * 100 / this.maxValue); 
    if( actualValuePercent > 100){
      actualValuePercent = 100; 
    }
    this.actualValue = actualValuePercent + '%'
  }

}
