import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModuleWithProviders } from '@angular/core';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { RecommendationsFacade } from './+recomendations/recommendations.facade';
import { recommendationsReducer } from './+recomendations/recommendations.reducer';
import { RecommendationsEffects } from './+recomendations/recommendations.effects';

@NgModule({
  imports: [
    CommonModule,
    EffectsModule.forFeature([
        RecommendationsEffects
    ]),
    StoreModule.forFeature('recommendations', recommendationsReducer)],    
providers: [] 
})
export class NgrxRecommendationsModule {
  static forRoot() : ModuleWithProviders<NgrxRecommendationsModule> {
    return {
      ngModule: NgrxRecommendationsModule,
      providers: [
        RecommendationsFacade
      ]
    }
  }
}