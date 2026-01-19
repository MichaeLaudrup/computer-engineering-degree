import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Aliment } from '@shared/models';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'list-meal-item',
  templateUrl: './list-meal-item.component.html',
  styleUrls: ['./list-meal-item.component.scss']
})
export class ListMealItemComponent implements OnInit {
  @Input() meal : Aliment; 
  @Input() index: number; 
  @Output() listItemClicked: EventEmitter<number> = new EventEmitter(); 
  foodImagesURL  = environment.staticMealsImagesURL; 
  constructor() { }

  ngOnInit(): void {
  }

  itemClicked() {
    this.listItemClicked.emit(this.index);
  }

}
