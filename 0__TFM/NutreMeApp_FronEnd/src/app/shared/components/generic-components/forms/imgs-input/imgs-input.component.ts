import { AfterContentChecked, Component, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';

@Component({
  selector: 'imgs-input',
  templateUrl: './imgs-input.component.html',
  styleUrls: ['./imgs-input.component.scss']
})
export class ImgsInputComponent implements OnInit {
  @Input('img-options') imgsOptions: string[] = []; 
  @Input('form-group') formGroupFromParent: FormGroup; 
  @Input('control-name') controlName: string; 
  @Input() actualPath: string = ''; 
  isChoosingAImg: boolean = false; 
  constructor() { }

  ngOnInit(): void {
    this.actualPath = this.formGroupFromParent.get(this.controlName).value; 
  }

  boxClicked() {
    this.isChoosingAImg = !this.isChoosingAImg; 
  }
  calcHeight() : string {
    return (Math.ceil(this.imgsOptions.length / 3) * 50) + 'px'; 
  }

  selectImg(i: number){
    this.actualPath = this.imgsOptions[i]
    this.formGroupFromParent.controls[this.controlName].setValue(this.imgsOptions[i])
  }
}
