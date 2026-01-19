import { AfterContentChecked, AfterViewInit, Component, ElementRef, Input, OnChanges, OnDestroy, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'radial-chart',
  templateUrl: './radial-chart.component.html',
  styleUrls: ['./radial-chart.component.scss']
})
export class RadialChartComponent implements OnInit, OnDestroy, OnChanges{
  @ViewChild('container', {static:true}) container: ElementRef<HTMLDivElement>; 
  height: number = 0;  
  @Input() value; 
  @Input() limit = 75; 
  @Input() label = 'Carbohidratos'
  @Input() units = 'gr';
  @Input() firstColor = 'var(--c2)'; 
  @Input() secondColor = 'var(--c5)'; 
  @Input() bcColor = 'var(--elements-bg-primary)'
  actualDegree = 50; 
  private destroySuscriptions$: Subject<any> = new Subject()
  
  constructor() { }

  ngOnInit(): void {
    this.actualDegree= (+this.value * 360 / this.limit);
    this.height = this.container.nativeElement.offsetWidth; 
  }
  ngOnDestroy(): void {
    this.destroySuscriptions$.next({})
     this.destroySuscriptions$.unsubscribe()
  }
  updateHeight(){
    this.height = this.container.nativeElement.offsetWidth;
  }
  ngOnChanges(simpleChanges: SimpleChanges) {
    this.actualDegree = (+this.value * 360 / this.limit)
  }



}
