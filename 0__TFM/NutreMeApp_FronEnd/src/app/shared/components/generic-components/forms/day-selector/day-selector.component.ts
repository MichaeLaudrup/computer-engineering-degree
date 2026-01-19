import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'day-selector',
  templateUrl: './day-selector.component.html',
  styleUrls: ['./day-selector.component.scss']
})
export class DaySelectorComponent implements OnInit {
  @Input('control-name') nameControl: string;
  @Input('form-group')formGroupFromParent: FormGroup; 
  constructor() { }

  ngOnInit(): void {
  }

}
