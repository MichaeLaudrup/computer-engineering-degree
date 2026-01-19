import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { DeviceMode } from '@shared/enums';
import * as Highcharts from 'highcharts'; 
import { Subject, takeUntil } from 'rxjs';
import { UserInterfaceService } from 'src/app/core/services/user-interface.service';
import { NutritionTarget } from 'src/app/shared/enums/nutrition-target.enum';
@Component({
  selector: 'app-mba-chart',
  templateUrl: './mba-chart.component.html',
  styleUrls: ['./mba-chart.component.scss']
})
export class MbaChartComponent implements OnInit, OnDestroy {
  @Input('mba') mba: number = 0; 
  @Input('mba-with-activity') mbaWithActivity: number = 0; 
  @Input('mba-with-act-objetive') mbaWithActAndObjetive: number = 0; 
  @Input('objetive') objetive : NutritionTarget; 
  DeviceMode = DeviceMode; 
  deviceMode: DeviceMode = DeviceMode.Laptop; 
  Highcharts = Highcharts; 
  chartOptions: any; 
  private destroySuscriptions$: Subject<any> = new Subject()
  ngOnDestroy(): void {
      this.destroySuscriptions$.next({})
       this.destroySuscriptions$.unsubscribe()
  }
  constructor( private userInterfaceService: UserInterfaceService) {
    this.userInterfaceService.deviceMode$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( deviceMode => {
        this.deviceMode = deviceMode; 
        this.calcChart();  
    })
   }

  ngOnInit(): void {
    this.calcChart(); 
  }
  calcChart(){
    this.chartOptions = {
        chart: {
            type: 'column',
            backgroundColor: 'transparent',
        },
        title: {
            align: 'left',
            text: ''
        },
        subtitle: {
            align: 'left',
            text: ''
        },
        accessibility: {
            announceNewData: {
                enabled: true
            }
        },
        xAxis: {
            type: 'category',
            labels: {
              enabled: (this.deviceMode === DeviceMode.ExtraSmall) ? false : true,
              style: {
                color:'var(--text-generic-color)',
              }
            },
            lineColor: 'var(--text-generic-color)'
        },
        yAxis: {
            title: {
                text: (this.deviceMode === DeviceMode.ExtraSmall) ? '' : 'Total KCal Díarias',
                style: {
                  color:'var(--text-generic-color)',
                }
            },
            labels: {
              style: {
                color:'var(--text-generic-color)'
              }
            }
    
        },
        legend: {
            enabled: false,
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    allowOverlap: true,
                    backgroundColor: 'var(--elements-bg-secondary-transparent)',
                    borderWidth: 1,
                    borderColor: 'var(--generic-text-color)',
                    enabled: true,
                    format: `<span style="color: var(--text-generic-color); font-size:${ (this.deviceMode === DeviceMode.ExtraSmall) ? '.8rem' : '.9rem'}">{point.y:.1f} <span> Kcal/día`,
                    style: {
                      textShadow: false,
                      textOutline: false,
                    }
                }
            },
           
        },
    
        tooltip: {
            headerFormat: '',
            pointFormat: '<span style="color:black">{point.name}</span>: <br><b>{point.y:.2f} Kcal/día</b><br/>'
        },
    
        series: [
            {
                name: "Kcal/día",
                colorByPoint: true,
                data: [
                    {
                        name: "MBA",
                        y:this.mba,
                        color: 'var(--chart-color01)'
                    },
                    {
                        name: "MBA haciendo deporte",
                        y: this.mbaWithActivity,
                        color: 'var(--chart-color02)'
                    },
                    {
                        name: `MBA ${this.objetive}`,
                        y: this.mbaWithActAndObjetive,
                        color: 'var(--chart-color03)'
                    }
                ]
            }
        ],
    }

  }
}


