import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';


@Component({
  selector: 'search-input',
  templateUrl: './search-input.component.html',
  styleUrls: ['./search-input.component.scss']
})
export class SearchInputComponent implements OnInit {
  @ViewChild('searchText') input: ElementRef; 
  @Output('updatedInput')inputEmitterEvent: EventEmitter<string> = new EventEmitter<string>(); 
  constructor() { }

  ngOnInit(): void {
  }

  inputClicked(event){
    event.preventDefault(); 
    this.inputEmitterEvent.emit(this.input.nativeElement.value); 
  }

}
