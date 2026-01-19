import { createFeatureSelector, createSelector } from '@ngrx/store';
import { DailyMealsRegister } from '@shared/models';
import { DietAppState } from '../diet.state';

export const getSharedAppSelector = createFeatureSelector<DietAppState>('diet'); 

export const getDailyMealRegister = createSelector(
    getSharedAppSelector,  (state:DietAppState ) => new DailyMealsRegister( state.dailyMealsRegister._id, state.dailyMealsRegister.date, state.dailyMealsRegister.scheduledMeals)
); 


