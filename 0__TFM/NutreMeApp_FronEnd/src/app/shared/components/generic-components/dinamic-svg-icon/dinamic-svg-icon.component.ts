import { Component, Host, HostBinding, Input, OnInit } from '@angular/core';

@Component({
  selector: 'dinamic-svg-icon',
  templateUrl: './dinamic-svg-icon.component.html',
  styleUrls: ['./dinamic-svg-icon.component.scss']
})
export class DinamicSvgIconComponent implements OnInit {
  @Input('svg-name') svgName: string; 
  @Input('svg-width') svgWidth: string = '25px'; 
  @Input('svg-height') svgHeight: string = '25px'; 
  @Input('svg-color') svgColor: string = 'var(--svg-generic-color)'
  @HostBinding('style.width') width; 
  @HostBinding('style.height') height; 
  constructor() { }

  ngOnInit(): void {
    this.width = this.svgWidth; 
    this.height = this.svgHeight; 
  }

}
