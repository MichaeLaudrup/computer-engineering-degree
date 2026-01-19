import { createFeatureSelector, createSelector } from '@ngrx/store';
import { RecommendationState } from './recommendations.reducer';

export const getSharedAppSelector = createFeatureSelector<RecommendationState >('recommendations'); 

export const getRecommendationsSelector = createSelector(
    getSharedAppSelector,  (state:RecommendationState  ) => state.recommendedScheduledMeals
); 

export const getRecommendationsByMealSelector =  (meal: string) => createSelector(
    getSharedAppSelector,  (state:RecommendationState  ) => state.recommendedScheduledMeals.find(recommendation => recommendation.name.toLowerCase() === meal.toLowerCase())
); 


