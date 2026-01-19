import { Action, createReducer, on } from "@ngrx/store";
import { ScheduledMeals } from "@shared/models";
import * as RecommendationActions from './recommendations.actions';

export interface RecommendationState {
    recommendedScheduledMeals: ScheduledMeals[]
}
const initialState : RecommendationState = {
    recommendedScheduledMeals: []
}

export const recommendationsReducer = createReducer<RecommendationState, Action>( initialState,
    on(RecommendationActions.requestRecommendation, (state) => ({...state})),
    on(RecommendationActions.requestRecommendationSuccess, ( state, {scheduledMeal}) => ({
        ...state,
        recommendedScheduledMeals: [...state.recommendedScheduledMeals, scheduledMeal] }))
); 
