import { AfterContentChecked, AfterViewInit, Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'slider-input',
  templateUrl: './slider-input.component.html',
  styleUrls: ['./slider-input.component.scss']
})
export class SliderInputComponent implements OnInit{
  @Input() minValue: number = 0; 
  @Input() maxValue: number= 100; 
  @Input() actualValue: number = 50; 
  @Input() units: string = 'gr'; 
  @Input() textLabel: string = 'Proteinas';
  @Input() stepPrecision: number = 0.5
  @Input('form-group') formGroupFromParent : FormGroup; 
  @Input() controlName: string; 
  constructor() { }
  ngOnInit(): void {
  }

  inputMood( isInputMood: boolean) {
  }
}
