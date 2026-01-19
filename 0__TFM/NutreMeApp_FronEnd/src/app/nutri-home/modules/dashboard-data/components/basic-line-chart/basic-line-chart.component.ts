import { Component, OnDestroy, OnInit } from "@angular/core";
import { UserInterfaceService } from "@core/services";
import { DeviceMode } from "@shared/enums";
import * as HighCharts from 'highcharts'
import { Subject, take, takeUntil } from "rxjs";
@Component({
	selector: "app-basic-line-chart",
	templateUrl: "./basic-line-chart.component.html",
	styleUrls: ["./basic-line-chart.component.scss"]
})
export class BasicLineChartComponent implements OnInit, OnDestroy {
	chartOptions = { };
	Highcharts = HighCharts;  
	deviceMode: DeviceMode; 
	DeviceMode = DeviceMode; 
	showExtraInfo = false; 
	private destroySuscriptions$: Subject<any> = new Subject()
	ngOnDestroy(): void {
		this.destroySuscriptions$.next({})
		 this.destroySuscriptions$.unsubscribe()
	}
	constructor( private userInterfaceService: UserInterfaceService) {
	}


	ngOnInit(){
		this.userInterfaceService.deviceMode$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( (deviceMode) => {
			if(deviceMode === DeviceMode.Laptop || deviceMode === DeviceMode.BigLaptop) {
				this.showExtraInfo = true;
				
			}else{
				this.showExtraInfo = false; 
			}
			this.calcChart();  
		}); 

	}

	calcChart() {
		this.chartOptions = {
			chart: {
				backgroundColor: 'var(--elements-bg-secondary)',
			},
			title: {
				text: ""
			},

			subtitle: {
				text: ""
			},

			yAxis: {
				min: 0,
				title: {
					text: (this.showExtraInfo) ? "Calorías díarias" : "",
					style: {
						color: 'var(--text-generic-color)'
					}
				},

				labels: {
					style: {
						color: 'var(--text-generic-color)'
					}
				}
			},

			xAxis: {
				lineColor: '#96f2d7',
				categories: ['Mon','Thu','Wed','Tue','Fri','Sat','Sun', 'Mon', 'Thu', 'Wed', 'Tue', 'Fri', 'Sat'],
				gridLineWidth: 0,

				labels: {
					formatter: function() {
						return this.value;
					},
					style: {
						color: 'var(--text-generic-color)'
					}
				},
			},

			legend: {
				layout: "vertical",
				align: "right",
				verticalAlign: "center",
				x:0,
				y:0,
				itemStyle: {
					color: 'var(--text-generic-color)'
				}
			},

			plotOptions: {
			},

			series: [{
				name: "Kilo calorías objetivo",
				data: [3097, 3097, 3097, 3097, 3097, 3097, 3097, 3042, 3498,3034],
				color: 'var(--chart-color03)'
			}, {
				name: "Kilo calorías consumidas",
				data: [1000, 1500, 2959, 980, 2500, 2125, 3000,2980, 3450, 3000],
				color: 'var(--chart-color01)'
			}
			],

			responsive: {
				rules: [{
					condition: {
						maxWidth: 1000
					},
					chartOptions: {
						legend: {
							x: 0,
							y: 0,
							layout: "horizontal",
							align: "right",
							verticalAlign: "right"
						}
					}
				}]
			}

		};
	}
}

		

