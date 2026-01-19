import { AfterContentChecked, AfterViewChecked, Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { FormControlName, FormGroup } from '@angular/forms';

@Component({
  selector: 'text-input',
  templateUrl: './text-input.component.html',
  styleUrls: ['./text-input.component.scss']
})
export class TextInputComponent implements OnInit, AfterContentChecked{
  @Input('form-group') formGroupFromParent: FormGroup; 
  @Input() controlName: string; 
  @Input() labelText: string; 
  @Input() inputId: string = '0' + Math.ceil(Math.random() * 100)
  @ViewChild('input', {static: true}) inputElement!: ElementRef<HTMLInputElement>; 

  labelElevated: boolean = false; 
  constructor() { }

  ngOnInit(): void {
  }

  ngAfterContentChecked(): void {
    this.labelElevated = !this.isInputEmpty; 
  }
  




  

  public checkInput(){
    this.labelElevated = !this.isInputEmpty; 
  }

  get isInputEmpty(): boolean {
   return (this.inputElement.nativeElement.value.length === 0); 
  }

}
