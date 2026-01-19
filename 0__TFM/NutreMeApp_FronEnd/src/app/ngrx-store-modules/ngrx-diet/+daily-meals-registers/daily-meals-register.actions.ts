import { createAction, props } from "@ngrx/store";
import { DailyMealsRegister } from "@shared/models";

export const requestDailyMealsRegister = createAction(
    '[ngrx-diet/daily-meals-register] Request to serve a Daily Meal Register',
    props<{ date: Date}>())

export const requestDailyMealsRegisterSuccess = createAction(
    '[ngrx-diet/daily-meals-register] Request to serve a Daily Meal Register Success',
    props<{ dailyMealsRegister: DailyMealsRegister}>())

export const setDailyMealsRegister= createAction(
    '[ngrx-diet/daily-meals-register] Set daily meals register',
    props<{ dailyMealsRegister: DailyMealsRegister}>())

export const addOrUpdateDailyMealsRegister = createAction(
    '[ngrx-diet/daily-meals-register] add or update daily meal register',
    props<{ dailyMealsRegister: DailyMealsRegister}>()
)

export const addOrUpdateDailyMealsRegisterSuccess = createAction(
    '[ngrx-diet/daily-meals-register] add or update daily meal register success',
    props<{ dailyMealsRegister: DailyMealsRegister}>()
)