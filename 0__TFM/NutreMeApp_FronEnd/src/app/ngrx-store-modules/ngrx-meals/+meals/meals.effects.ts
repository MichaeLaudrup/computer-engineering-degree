import { Injectable } from "@angular/core";
import { mealsService } from "@core/services";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { catchError, EMPTY, map, mergeMap, tap } from "rxjs";
import * as MealsActions from "./meals.actions";

@Injectable()
export class mealsEffects {
    constructor(
        private actions$: Actions,
        private mealsService: mealsService
    ){}

    loadMeals$ = createEffect(
        () => this.actions$.pipe(
                ofType( MealsActions.getMeals.type),
                mergeMap( (_) => this.mealsService.getAllMeals().pipe(
                    map( list_meals => ( { type: MealsActions.getMealsSucess.type, list_meals})),
                    catchError( () => EMPTY)))
                ))
    
    getMealsFiltered$ = createEffect(
        () => this.actions$.pipe(
            ofType( MealsActions.mealsByFieldsAndValues.type),
            mergeMap( ({fields, values}) => this.mealsService.getAllMealsFilterByFieldsAndValues(fields, values).pipe(
                map( mealsResult => ( { type: MealsActions.mealsByFieldsAndValuesSuccess.type,mealsResult})),
                catchError( () => EMPTY)))
            ))
}