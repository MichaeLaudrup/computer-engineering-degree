import { Injectable } from "@angular/core";
import { DailyMealsRegisterService, mealsService } from "@core/services";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { catchError, concatMap, EMPTY, map, mergeMap, tap } from "rxjs";

import * as DailyMealsRegisterActions from './daily-meals-register.actions';

@Injectable()
export class DailyMealsRegisterEffects {
    constructor(
        private actions$: Actions,
        private dailyMealsRegisterService: DailyMealsRegisterService
    ){}

    loadDailyMealsRegister$ = createEffect(
        () => this.actions$.pipe(
                ofType( DailyMealsRegisterActions.requestDailyMealsRegister.type),
                mergeMap( ({date}) => this.dailyMealsRegisterService.getMyDailyRegisterMeals(date).pipe(
                    map( dailyMealsRegister => ( { type: DailyMealsRegisterActions.requestDailyMealsRegisterSuccess.type, dailyMealsRegister})),
                    catchError( () => EMPTY)))
                )) 
    createUploadDailyMealsRegister$ = createEffect(
        () => this.actions$.pipe(
                ofType( DailyMealsRegisterActions.addOrUpdateDailyMealsRegister.type),
                concatMap( ({dailyMealsRegister}) => this.dailyMealsRegisterService.addOrUpdateDaily(dailyMealsRegister).pipe(
                    map( dailyMealsRegister => ( { type: DailyMealsRegisterActions.addOrUpdateDailyMealsRegisterSuccess.type, dailyMealsRegister})),
                    catchError( () => EMPTY)))
                )) 
    
}