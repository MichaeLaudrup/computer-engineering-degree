import { createAction, props } from "@ngrx/store";
import { Aliment } from "@shared/models";

export const addMeal = createAction('[Meals] Add meal'); 
export const addMealSuccess = createAction(
    '[Meals] Add meal success',
    props<{ meal: Aliment}>())
export const getMeals = createAction('[Meals] Get Meals'); 
export const getMealsSucess = createAction(
    '[Meals] Get Meals Success',
    props<{ list_meals: Aliment[]}>()); 
export const getMealsError = createAction(
    '[Meals] Get Meals Error',
    props<{errorPayload: any}>()); 

export const mealsByFieldsAndValues = createAction(
    '[Meals] Get meals by fields with some expresions',
    props<{fields: string[], values:string[]}>()
)


export const mealsByFieldsAndValuesSuccess = createAction(
    '[Meals] Get meals by fields with some values Success',
    props<{mealsResult: Aliment[]}>()
)
