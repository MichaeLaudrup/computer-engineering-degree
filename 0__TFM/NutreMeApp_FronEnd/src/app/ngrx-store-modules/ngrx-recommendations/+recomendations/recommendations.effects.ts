import { Injectable } from "@angular/core";
import { DailyMealsRegisterService, mealsService } from "@core/services";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { catchError, EMPTY, map, mergeMap, tap } from "rxjs";

import * as RecommendationActions from './recommendations.actions';

@Injectable()
export class RecommendationsEffects {
    constructor(
        private actions$: Actions,
        private dailyMealsRegisterService: DailyMealsRegisterService
    ){}

    loadDailyMealsRegister$ = createEffect(
        () => this.actions$.pipe(
                ofType( RecommendationActions.requestRecommendation.type),
                mergeMap( ({typeOfMeal}) => this.dailyMealsRegisterService.getRecommendation(typeOfMeal).pipe(
                    map(scheduledMeal => ( { type: RecommendationActions.requestRecommendationSuccess.type,scheduledMeal})),
                    catchError( () => EMPTY)))
                )) 
    
}