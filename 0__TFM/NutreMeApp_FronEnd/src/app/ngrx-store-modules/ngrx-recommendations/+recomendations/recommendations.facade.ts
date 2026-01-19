import { Injectable } from "@angular/core";
import { select, Store } from "@ngrx/store";
import * as recommendationActions from './recommendations.actions';
import { RecommendationState } from "./recommendations.reducer";
import * as recommendationSelectors from './recommendations.selectors'; 
@Injectable()
export class RecommendationsFacade {
    constructor( private store: Store<RecommendationState>) {}
    
    public get recomendations$() {
        return this.store.pipe(select(recommendationSelectors.getRecommendationsSelector)); 
    }

    public getRecommendationsOfMeal(meal :string){
        return this.store.pipe(select(recommendationSelectors.getRecommendationsByMealSelector(meal))); 
    }

    public requestRecomendation( typeOfMeal : string){
        this.store.dispatch(recommendationActions.requestRecommendation({typeOfMeal})); 
    }
}