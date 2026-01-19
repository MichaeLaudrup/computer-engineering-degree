import { Action, createReducer, on } from "@ngrx/store";
import { Aliment } from "@shared/models";
import * as MealsActions from './meals.actions'; 

export interface MealsState {
    list_meals: Aliment[],
    loaded: boolean, 
    loading: boolean,
    error: any
}

const initialState = {
    list_meals: [],
    loaded: false, 
    loading: false,
    error: undefined
}

export const mealsReducer = createReducer<MealsState, Action>( initialState,
    on(MealsActions.getMeals, (state) => ({ ...state, loading: true})),
    on(MealsActions.getMealsSucess, (state, {list_meals}) => ({ 
        ...state, 
        list_meals: list_meals, 
        loading:false,
        loaded: true})),
    on(MealsActions.getMealsError, (state, {errorPayload}) => ({ 
        ...state, 
        loading:false,
        loaded: false,
        error: errorPayload
        })),
    on(MealsActions.mealsByFieldsAndValues, (state, {fields, values}) => {
        return {
            ...state,
            loaded: false,
            loading: true,
            error: undefined,
        }
    }),
    on(MealsActions.mealsByFieldsAndValuesSuccess, (state, {mealsResult}) => {
        return {
            ...state,
            list_meals:mealsResult,
            loading: false,
            loaded: true,
            error: undefined
        }
    })); 