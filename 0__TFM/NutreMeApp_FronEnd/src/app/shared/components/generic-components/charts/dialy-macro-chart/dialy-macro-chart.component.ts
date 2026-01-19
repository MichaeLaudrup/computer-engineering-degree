import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { DailyMealsRegisterFacade } from '@ngrx/ngrx-diet';
import { UserDataFacadeService } from '@ngrx/ngrx-shared';
import { DailyMealsRegister, FisiologicData } from '@shared/models';
import { Subject, takeUntil } from 'rxjs';
import { expandedAnimation } from 'src/app/shared/animations/animation';
import { Macronutrients } from 'src/app/shared/models/macronutrients.model';

@Component({
  selector: 'dialy-macro-chart',
  templateUrl: './dialy-macro-chart.component.html',
  styleUrls: ['./dialy-macro-chart.component.scss'],
  animations: [expandedAnimation]
})
export class DialyMacroChartComponent implements OnInit, OnDestroy{
  private repose: boolean = false; 
  public macroNutrientsLimits : Macronutrients = {
    carbohydrates: 0,
    proteins: 0,
    fats: 0
  }; 
  totalKcal: number = 0; 
  doneKcal: number = 0; 
  @Input() bindedToNgrx = true;
  @Input() dailyMealsRegister: DailyMealsRegister = new DailyMealsRegister('none', new Date(), []); 
  constructor( 
    private userDataFacadeService: UserDataFacadeService,
    private dailyMealsRegisterFacade: DailyMealsRegisterFacade) { }
    private destroySuscriptions$: Subject<any> = new Subject()


  ngOnInit(): void {
    this.userDataFacadeService.fisiologicData$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( (fisiologicData: FisiologicData) => {
      if(fisiologicData.macrosWithActivity){
        this.macroNutrientsLimits = fisiologicData.macrosWithActivity;
        this.totalKcal = fisiologicData.mbaWithActivityAndObjetive; 
      }
    })
    if(this.bindedToNgrx){
      this.dailyMealsRegisterFacade.DailyMealsRegister$.pipe(takeUntil(this.destroySuscriptions$)).subscribe((dailyMealsRegister: DailyMealsRegister) => {
        if(dailyMealsRegister){
          this.dailyMealsRegister = dailyMealsRegister; 
          if(this.dailyMealsRegister?.scheduledMeals?.length > 0){
              this.dailyMealsRegister = new DailyMealsRegister( dailyMealsRegister._id, dailyMealsRegister.date, dailyMealsRegister.scheduledMeals);
              this.doneKcal = this.dailyMealsRegister.totalKcal
          }
        }
      })
    }
  }

  ngOnDestroy(): void {
    this.destroySuscriptions$.next({})
     this.destroySuscriptions$.unsubscribe()
  }
}
