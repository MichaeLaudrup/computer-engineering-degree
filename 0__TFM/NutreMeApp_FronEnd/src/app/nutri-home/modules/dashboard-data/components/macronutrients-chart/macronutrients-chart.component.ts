import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { UserInterfaceService } from '@core/services';
import { DeviceMode } from '@shared/enums';
import * as Highcharts from 'highcharts';
import { Subject, takeUntil } from 'rxjs';
@Component({
  selector: 'app-macronutrients-chart',
  templateUrl: './macronutrients-chart.component.html',
  styleUrls: ['./macronutrients-chart.component.scss']
})
export class MacronutrientsChartComponent implements OnInit, OnDestroy {
    @Input('carbo-hydrates') carbohydrates: number = 100; 
    @Input('proteins') proteins: number = 80; 
    @Input('fats') fats: number = 20; 
    
    deviceMode: DeviceMode; 
    chartOptions = {}; 
    Highcharts = Highcharts; 
    private destroySuscriptions$: Subject<any> = new Subject()

    constructor( private userInterfaceService: UserInterfaceService) {
        this.userInterfaceService.deviceMode$.pipe( takeUntil(this.destroySuscriptions$)).subscribe((deviceMode) => {
            this.deviceMode =deviceMode; 
        })

    }

    ngOnInit() {

        this.chartOptions ={
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie',
                backgroundColor: 'transparent'
            },
            title: {
                text: ''
            },
            tooltip: {
                valueDecimals: 2,
                pointFormat: '<b>{point.y} gr/día</b>'
            },
            accessibility: {
                point: {
                    valueSuffix: 'KCal'
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    colors: ['var(--chart-color01)', 'var(--chart-color02)', 'var(--chart-color03)'], 
                    dataLabels: {
                        enabled: false,
                        
                    },
                    showInLegend : true,
                    
                }
            },
            legend: {
                align: 'center',
                itemStyle: {'color': 'var(--text-generic-color)'},
                verticalAlign: 'bottom',
                aling: 'center',
                itemDistance: this.deviceMode === DeviceMode.ExtraSmall ? 4 : 5
                  
            },
            series: [{
                name: 'Share',
                data: [
                    { name: 'Hidratos', y: this.carbohydrates},
                    { name: 'Proteínas', y: this.proteins },
                    { name: 'Grasas', y: this.fats },
                ]
            }]
        }; 
        setTimeout( () => {
            window.dispatchEvent( new Event('resize'))
        }, 100)

    }

    ngOnDestroy(): void {
        this.destroySuscriptions$.next({})
         this.destroySuscriptions$.unsubscribe()
    }


}


