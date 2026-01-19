import { createAction, props } from "@ngrx/store";
import { ScheduledMeals } from "@shared/models";

export const requestRecommendation = createAction(
    '[ngrx-diet/recommendations] Request to serve recommendations',
    props<{ typeOfMeal: string}>())

export const requestRecommendationSuccess = createAction(
    '[ngrx-diet/recommendations]  Request to serve recommendations success',
    props<{ scheduledMeal: ScheduledMeals}>())

