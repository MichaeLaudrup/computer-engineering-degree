import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import {  FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MealSectionsFacade } from '@ngrx/ngrx-section-meals';
import { Aliment } from '@shared/models';
import { Subject, takeUntil } from 'rxjs';
import { ModalService } from '../../modal.service';


@Component({
  selector: 'meal-form',
  templateUrl: './meal-form.component.html',
  styleUrls: ['./meal-form.component.scss']
})
export class MealFormComponent implements OnInit, OnDestroy {
  mealForm: FormGroup;
  
  @Input() searchMode = false; 
  @Input() editMode = false;
  @Input() sectionId = '';
  @Input() meal: Aliment = {
    name: '',
    description: '',
    weight: 0,
    kcal: 500,
    proteins: 50,
    carboHydrates: 50,
    fats: 50,
    srcImg: 'assets/food-images/generic-img.jpg',
    tags: []
  }; 
  private destroySuscriptions$: Subject<any> = new Subject()

  constructor(
    private sectionMealsFacade: MealSectionsFacade,
    private modalService: ModalService
  ) { 

  }

  ngOnDestroy(): void {
    this.destroySuscriptions$.next({})
    this.destroySuscriptions$.unsubscribe();
}

  ngOnInit(): void {
    this.mealForm = new FormGroup({
      name: new FormControl(this.meal.name, Validators.required),
      description: new FormControl(this.meal.description),
      weight: new FormControl(this.meal.weight),
      kcal: new FormControl(this.meal.kcal), 
      proteins: new FormControl(this.meal.proteins),
      carboHydrates: new FormControl(this.meal.carboHydrates),
      fats: new FormControl(this.meal.fats),
      srcImg: new FormControl(this.meal.srcImg),
      tags: new FormArray([], Validators.required)
    })

    this.sectionMealsFacade.isLoading$.pipe( takeUntil(this.destroySuscriptions$)).subscribe( (isLoading) => {
      if(isLoading){
        this.modalService.showLoadingSpinner(true); 
      }else{
        this.modalService.showLoadingSpinner(); 
      }
    });

    this.sectionMealsFacade.feedbackListener$.pipe(takeUntil( this.destroySuscriptions$)).subscribe(({ showFeedBack, feedBackMessage}) => {
      if(showFeedBack){
        this.modalService.showFeedBackMessage(showFeedBack, feedBackMessage)
        setTimeout( () => {
          this.sectionMealsFacade.resetFeedback(); 
          this.modalService.hideModalNow(true); 
        }, 2500 )
      }
    })
  }

  createMeal(){
    const newMeal: Aliment = this.mealForm.value; 
    this.sectionMealsFacade.createNewMealInSection(this.sectionId, newMeal)
  } 

  editMeal() {
    const mealUpdated : Aliment = {
      _id: this.meal._id, 
      ...this.mealForm.value
    }
    this.sectionMealsFacade.editMealInSection(this.sectionId, mealUpdated); 
  }

  addGroupOfAliments(aliments: Aliment[]){
    this.sectionMealsFacade.addGroupOfAliments(this.sectionId, aliments)
  }

}
