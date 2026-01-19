import { CommonModule } from "@angular/common";
import { ModuleWithProviders, NgModule } from "@angular/core";
import { EffectsModule } from "@ngrx/effects";
import { StoreModule } from "@ngrx/store";
import { mealsEffects } from "./+meals/meals.effects";
import { MealsFacade } from "./+meals/meals.facade";
import { mealsReducer } from "./+meals/meals.reducer";

@NgModule({
    imports: [
        CommonModule,
        EffectsModule.forFeature([
            mealsEffects
        ]),
        StoreModule.forFeature('meals', mealsReducer)],    
    providers: [] 
})
export class NgrxMealsModule{
    static forRoot(): ModuleWithProviders<NgrxMealsModule> {
        return {
            ngModule: NgrxMealsModule,
            providers: [
                MealsFacade
            ]
        }; 
    }
} 