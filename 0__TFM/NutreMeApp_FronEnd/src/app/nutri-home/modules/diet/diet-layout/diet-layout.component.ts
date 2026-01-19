import { OnDestroy } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DailyMealsRegisterFacade } from '@ngrx/ngrx-diet';
import { Aliment, DailyMealsRegister } from '@shared/models';
import { takeUntil } from 'rxjs';
import { Subject } from 'rxjs';
import { toDateInputValue } from 'src/app/shared/utils/convertions.utils';

@Component({
  selector: 'app-diet-layout',
  templateUrl: './diet-layout.component.html',
  styleUrls: ['./diet-layout.component.scss']
})
export class DietLayoutComponent implements OnInit, OnDestroy {
  dailyNutritionalAnalisysForm: FormGroup;  
  scheduledMeals : string[] = ['Desayuno', 'Almuerzo']
  private destroySuscriptions$: Subject<any> = new Subject()
  dailyMealsRegister: DailyMealsRegister; 
  isSomethingChanged = false; 

  constructor(private dailyMealsRegisterFacade: DailyMealsRegisterFacade) { }

  ngOnInit(): void {
    this.dailyMealsRegisterFacade.DailyMealsRegister$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( dailyMealsRegister => {
      
      this.dailyMealsRegister = dailyMealsRegister; 
      if(this.dailyMealsRegister?.scheduledMeals?.length > 0){
        this. dailyMealsRegister = new DailyMealsRegister( dailyMealsRegister._id, dailyMealsRegister.date, dailyMealsRegister.scheduledMeals)
      }

    })
    this.dailyNutritionalAnalisysForm = new FormGroup({
      date: new FormControl(toDateInputValue.bind(this)(), Validators.required)
    })

  }

  ngOnDestroy(): void {
    this.destroySuscriptions$.next({})
    this.destroySuscriptions$.unsubscribe()
  }

  moveFoodToDone($event: {meal: Aliment, numPosition: number}, scheduledPos: number){
    this.isSomethingChanged = true; 
    let mealRecieved = $event.meal
    const newMeal: Aliment = {
      _id: $event.meal._id,
      name: mealRecieved.name,
      carboHydrates: mealRecieved.carboHydrates,
      tags: mealRecieved.tags,
      description: mealRecieved.description,
      weight: mealRecieved.weight,
      kcal: mealRecieved.kcal,
      proteins: mealRecieved.proteins,
      carboWithSugars: mealRecieved.carboWithSugars,
      fats: mealRecieved.fats,
      saturatedFats: mealRecieved.saturatedFats,
      fiber: mealRecieved.fiber,
      recomendedMeals:mealRecieved.recomendedMeals,
      srcImg: mealRecieved.srcImg 
    }
    this.dailyMealsRegister.scheduledMeals[scheduledPos].addOneAliment(newMeal);
    this.dailyMealsRegister.scheduledMeals[scheduledPos].calculateMacronutrients(); 
    this.dailyMealsRegister.calculateTotalMacroAndKcal(); 
  }


  modeFoodToRecomendations($event: { meal: Aliment, numPosition: number}, scheduledPos: number){
    this.isSomethingChanged = true; 
    this.dailyMealsRegister.scheduledMeals[scheduledPos].deleteOneAliment($event.numPosition); 
    this.dailyMealsRegister.calculateTotalMacroAndKcal(); 
  }

  uploadToServer(){
    let oneScheduleWithAliments = false; 
    this.dailyMealsRegister.scheduledMeals.forEach( meal => {
      if(meal.aliments.length > 0) oneScheduleWithAliments = true; 
    })
    if(oneScheduleWithAliments){
      this.dailyMealsRegisterFacade.createOrUploadDailyMealsRegister(this.dailyMealsRegister); 
    }else{

    }


  }

}
