import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as mealsReducer from './meals.reducer'; 


export const getSharedAppSelector = createFeatureSelector<mealsReducer.MealsState>('meals'); 

export const getListMeals = createSelector(
    getSharedAppSelector,  (state: mealsReducer.MealsState) => state.list_meals
);


