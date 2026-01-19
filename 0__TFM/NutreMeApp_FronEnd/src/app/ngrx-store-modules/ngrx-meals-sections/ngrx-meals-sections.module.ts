import { CommonModule } from "@angular/common";
import { ModuleWithProviders, NgModule } from "@angular/core";
import { EffectsModule } from "@ngrx/effects";
import { StoreModule } from "@ngrx/store";
import { SectionMealsEffects } from "./+section_meals/section-meals.effects";
import { MealSectionsFacade } from "./+section_meals/section-meals.facade";
import { sectionMealsReducer } from "./+section_meals/section-meals.reducer";

@NgModule({
    imports: [
        CommonModule,
        StoreModule.forFeature('section_meals', sectionMealsReducer),
        EffectsModule.forFeature([
            SectionMealsEffects
        ]) 
    ],
    providers: [
        MealSectionsFacade
    ], 
})
export class NgRxMealsSectionModule{
    static forRoot(): ModuleWithProviders<NgRxMealsSectionModule> {
        return {
            ngModule: NgRxMealsSectionModule,
            providers: [
                MealSectionsFacade
            ]
        }
    }
}