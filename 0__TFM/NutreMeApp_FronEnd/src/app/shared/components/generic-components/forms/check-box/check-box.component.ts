import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';


@Component({
  selector: 'check-box',
  templateUrl: './check-box.component.html',
  styleUrls: ['./check-box.component.scss']
})
export class CheckBoxComponent implements OnInit {
  @Input('initial-value')selected = false;
  @Output('check-clicked') checkClicked = new EventEmitter<boolean>(); 
  @Input('idName') id:string; 
  constructor() { }

  ngOnInit(): void {
  }

  inputClicked(){
    this.selected = !this.selected; 
    this.checkClicked.emit(this.selected); 
  }

}
