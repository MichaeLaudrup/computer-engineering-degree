import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from "@angular/router";
import { MealSectionsFacade } from "@ngrx/ngrx-section-meals";
import { concatMap, Observable, take, of } from "rxjs";
import { SectionMeal } from "../../models/section-meal.model";


@Injectable()
export class ListMealsResolver implements Resolve<SectionMeal[]>{
    constructor( private router: Router, private sectionMealFacade: MealSectionsFacade){}
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): SectionMeal[] | Observable<SectionMeal[]> | Promise<SectionMeal[]> {
        return null; 
    }
    
}