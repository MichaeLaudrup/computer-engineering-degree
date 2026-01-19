import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DietRoutingModule } from './diet-routing.module';
import { DietLayoutComponent } from './diet-layout/diet-layout.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ScheduledMealsComponent } from './components/scheduled-meals/scheduled-meals.component';
import { NgrxDietModule } from '@ngrx/ngrx-diet';
import { dailyMealsRegisterResolver } from 'src/app/shared/routing-components/resolvers/daily-register.resolver';
import { NgrxRecommendationsModule } from 'src/app/ngrx-store-modules/ngrx-recommendations/ngrx-recommendations.module';


@NgModule({
  declarations: [
    DietLayoutComponent,
    ScheduledMealsComponent
  ],
  imports: [
    CommonModule,
    DietRoutingModule,
    SharedModule,
    NgrxDietModule.forRoot(),
    NgrxRecommendationsModule.forRoot()
  ],
  providers: [
    dailyMealsRegisterResolver
  ]
})
export class DietModule { }
