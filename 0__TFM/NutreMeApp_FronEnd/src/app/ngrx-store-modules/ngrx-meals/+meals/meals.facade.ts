import { Injectable } from "@angular/core";
import { select, Store } from "@ngrx/store";
import { Aliment } from "@shared/models";
import { Observable } from "rxjs";
import * as MealsActions from './meals.actions'; 
import { MealsState } from "./meals.reducer";
import * as MealsSelectors from "./meals.selectors"; 
@Injectable()
export class MealsFacade {
    constructor( private store: Store<MealsState>) {}
    public getMeals(){
        this.store.dispatch( MealsActions.getMeals()); 
    }

    public searchMealsWithFieldsAndValue(fields: string[], values:string[]){
        this.store.dispatch(MealsActions.mealsByFieldsAndValues({fields, values}))
    }

    public get meals$ (): Observable<Aliment[]>{
        return this.store.pipe( select( MealsSelectors.getListMeals));
    }

    

}