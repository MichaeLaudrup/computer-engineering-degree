import { state } from "@angular/animations";
import { Action, createReducer, on } from "@ngrx/store";
import { DailyMealsRegister, ScheduledMeals } from "@shared/models";
import { Macronutrients } from "src/app/shared/models/macronutrients.model";
import * as DailyMealsRegisterActions from './daily-meals-register.actions';

export interface DailyMealRegisterState {
    _id: string,
    date: Date,
    scheduledMeals: ScheduledMeals[],
    totalMacro: Macronutrients,
    totalKcal: number
}
const initialState : DailyMealRegisterState = {
    _id: 'none',
    date: new Date(),
    scheduledMeals: [
        new ScheduledMeals('desayuno', []),
        new ScheduledMeals('almuerzo', []),
        new ScheduledMeals('cena', [])
    ],
    totalMacro: {carbohydrates: 0, fats: 0, proteins: 0},
    totalKcal: 0
}

export const dailyMealsRegisterReducer = createReducer<DailyMealRegisterState, Action>( initialState,
    on(DailyMealsRegisterActions.requestDailyMealsRegister, (state) => ({...state})),
    on(DailyMealsRegisterActions.requestDailyMealsRegisterSuccess, (state, {dailyMealsRegister}) => ( dailyMealsRegister ? new DailyMealsRegister(dailyMealsRegister._id, dailyMealsRegister.date, dailyMealsRegister.scheduledMeals):{...state})),
    on(DailyMealsRegisterActions.setDailyMealsRegister, (state, {dailyMealsRegister}) => {
        let dailyMealsRegisterToClass;
        if(dailyMealsRegister){
            dailyMealsRegisterToClass = new DailyMealsRegister( dailyMealsRegister._id, dailyMealsRegister.date, dailyMealsRegister.scheduledMeals);
        }
        return dailyMealsRegisterToClass; 
    }),
    on(DailyMealsRegisterActions.addOrUpdateDailyMealsRegister, (state) => ({...state})),
    on(DailyMealsRegisterActions.addOrUpdateDailyMealsRegisterSuccess, (state, { dailyMealsRegister}) => ( new DailyMealsRegister(dailyMealsRegister._id, dailyMealsRegister.date, dailyMealsRegister.scheduledMeals)))
    ); 
