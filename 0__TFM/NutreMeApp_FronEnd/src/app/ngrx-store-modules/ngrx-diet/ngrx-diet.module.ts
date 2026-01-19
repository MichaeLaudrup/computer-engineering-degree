import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModuleWithProviders } from '@angular/core';
import { DailyMealsRegisterFacade } from './+daily-meals-registers/daily-meals-register.facade';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { DailyMealsRegisterEffects } from './+daily-meals-registers/daily-meals-register.effects';
import { dailyMealsRegisterReducer } from './+daily-meals-registers/daily-meals-register.reducer';

@NgModule({
  imports: [
    CommonModule,
    EffectsModule.forFeature([
        DailyMealsRegisterEffects,
    ]),
    StoreModule.forFeature('diet', {
      dailyMealsRegister: dailyMealsRegisterReducer,
    })],    
providers: [] 
})
export class NgrxDietModule {
  static forRoot() : ModuleWithProviders<NgrxDietModule> {
    return {
      ngModule: NgrxDietModule,
      providers: [
        DailyMealsRegisterFacade,
      ]
    }
  }
}
