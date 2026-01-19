import { Injectable } from "@angular/core";
import { select, Store } from "@ngrx/store";
import { DailyMealsRegister } from "@shared/models";
import { Observable } from "rxjs";
import * as DailyMealsRegisterActions from './daily-meals-register.actions';
import { DailyMealRegisterState } from "./daily-meals-register.reducer";
import * as DailyMealsRegisterSelectors from './daily-meals-register.selectors'; 
@Injectable()
export class DailyMealsRegisterFacade {
    constructor( private store: Store<DailyMealRegisterState>) {}
    
    public requestDailyMealsRegister(date: Date) {
        this.store.dispatch(DailyMealsRegisterActions.requestDailyMealsRegister({date}))
    }

    public get DailyMealsRegister$ () : Observable<DailyMealsRegister>{
        return this.store.pipe( select( DailyMealsRegisterSelectors.getDailyMealRegister))
    }

    public setDailyMealsRegister ( dailyMealsRegister: DailyMealsRegister ) {
        this.store.dispatch(DailyMealsRegisterActions.setDailyMealsRegister({dailyMealsRegister})); 
    }

    public createOrUploadDailyMealsRegister(dailyMealsRegister: DailyMealsRegister){
        this.store.dispatch(DailyMealsRegisterActions.addOrUpdateDailyMealsRegister({dailyMealsRegister})); 
    }
}