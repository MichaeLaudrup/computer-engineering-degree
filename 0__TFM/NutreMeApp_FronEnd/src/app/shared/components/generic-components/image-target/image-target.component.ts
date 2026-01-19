import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';


@Component({
  selector: 'image-target',
  templateUrl: './image-target.component.html',
  styleUrls: ['./image-target.component.scss']
})
export class ImageTargetComponent implements OnInit {
  @Input() title = "Desayuno"; 
  @Input() imgSrc = 'assets/store-imgs/breakFast.jpg'; 
  @Input() withEditMode : boolean = false; 
  @Input() actionsEnabled: boolean = false; 
  @Input('item-id') itemId: string = ''; 
  @Output() editModeClicked: EventEmitter<string> = new EventEmitter();
  @Output() deleteModeClicked: EventEmitter<string> = new EventEmitter();
  @Output() elementClicked: EventEmitter<string> = new EventEmitter(); 
  constructor() { }

  ngOnInit(): void {
  }

  
  editClicked() {
    this.editModeClicked.emit(this.itemId); 
  }

  deleteClicked() {
    this.deleteModeClicked.emit(this.itemId); 
  }

  emitElementClicked(){
    this.elementClicked.emit(this.itemId); 
  }


}
