import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'generic-button',
  templateUrl: './generic-button.component.html',
  styleUrls: ['./generic-button.component.scss']
})
export class GenericButtonComponent implements OnInit {
  @Input('button-type') buttonType = 'button'
  @Input('button-text') buttonText = 'Hello world'; 
  @Output('clicked') clicked = new EventEmitter<any>(); 
  constructor() { }

  ngOnInit(): void {
  }

  

}
