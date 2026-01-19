import { Component, EventEmitter,HostListener, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { MealsFacade } from '@ngrx/ngrx-meals';
import { Aliment } from '@shared/models';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'meals-result-list',
  templateUrl: './meals-result-list.component.html',
  styleUrls: ['./meals-result-list.component.scss'],
  providers: [ MealsFacade]
})
export class MealsResultListComponent implements OnInit, OnDestroy {
  @HostListener('document:click') globalClicked() {
    if(!this.clickInside){
      this.hideResults = true; 
    }
    this.clickInside = false; 
  }
  hideResults = true; 
  clickInside = false; 
  private destroySuscriptions$: Subject<any> = new Subject()
  meals : Aliment[]; 
  mealsSelected: Aliment[] = []; 
  mealsSelectedIds: String[] = []; 
  actualItemSelected: number = -1; 
  @Input() maxHeight: number = 270; 
  @Output() selectedMealsUpdated : EventEmitter<Aliment[]> = new EventEmitter(); 


  constructor(private mealsFacadeService: MealsFacade) { }

  ngOnInit(): void {
    this.mealsFacadeService.meals$.pipe(takeUntil(this.destroySuscriptions$)).subscribe((meals) => {
      this.meals = meals; 
    }); 
  }

  ngOnDestroy(): void {
    this.destroySuscriptions$.next({})
     this.destroySuscriptions$.unsubscribe()
}

  searchMealsWithQuery($event){
    this.hideResults = false; 
    console.log($event)
    this.mealsFacadeService.searchMealsWithFieldsAndValue(['name[regex]'],[$event]); 
  }

  addToMealsSelected( meal: Aliment){
    if(this.mealsSelectedIds.includes(meal._id)){
      const index = this.mealsSelectedIds.findIndex( (mealId) => mealId === meal._id );
      this.mealsSelectedIds.splice(index,1);
      
      const indexInMeals = this.mealsSelected.findIndex( (mealSelected) => mealSelected._id == meal._id);
      this.mealsSelected.splice(indexInMeals,1); 
    }else{
      this.mealsSelectedIds.push(meal._id);
      this.mealsSelected.push(meal); 
    }
  }

  sendUpSelectedMeals(){
    this.selectedMealsUpdated.emit(this.mealsSelected); 
  }

}
