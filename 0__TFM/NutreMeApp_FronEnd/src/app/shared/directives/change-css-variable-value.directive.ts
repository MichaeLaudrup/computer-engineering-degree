import { Directive, ElementRef, Input, Renderer2 } from '@angular/core';

@Directive({
  selector: '[changeCssVariableValue]'
})
export class ChangeCssVariableValueDirective {
  @Input() cssVariableName: string;
  @Input() cssVariableValue : string; 
  constructor( private renderer: Renderer2, private elementRef: ElementRef) { 

  }
  ngOnInit(){
    this.renderer.setStyle(
      this.elementRef.nativeElement,
      this.cssVariableName,
      this.cssVariableValue
    );
  }

}
