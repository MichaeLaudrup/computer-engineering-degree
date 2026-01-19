import { Injectable } from "@angular/core";
import { select, Store } from "@ngrx/store";
import { SectionMealState } from "./section-meals.reducer";
import * as SectionMealsActions from './section-meals.actions'; 
import * as SectionMealsSelectors from './section-meals.selector'; 
import { Observable } from "rxjs";
import { Aliment, SectionMeal } from "@shared/models";
@Injectable()
export class MealSectionsFacade {


    constructor( private store: Store<SectionMealState>){}
    
    get sectionMeals$(): Observable<{sections: SectionMeal[], loaded: boolean}> {
        return this.store.pipe( select( SectionMealsSelectors.sectionsAndLoaded)); 
    }

    public sectionMealById$(id: string): Observable<SectionMeal> {
        return this.store.pipe(select(SectionMealsSelectors.getSectionById(id)));
    }
    public requestSectionMeals(){
        this.store.dispatch( SectionMealsActions.getMeals()); 
    }

    public addNewSection( newSection: SectionMeal, photo?: FormData) {
        this.store.dispatch(SectionMealsActions.addSection({ newSection, photo}))
    }

    public editSectionMeal( id: string , section: SectionMeal, photo?: FormData){
        this.store.dispatch(SectionMealsActions.editSection({id, section}))
    }

    public deleteSection(id:string): void{
        this.store.dispatch(SectionMealsActions.deleteSection({id})); 
    }

    public createNewMealInSection(sectionId: string, newMeal: Aliment) {
        this.store.dispatch(SectionMealsActions.addMealToSection({idSection: sectionId, newMeal}))
    }

    public get isLoading$() :Observable<boolean>{
        return this.store.pipe(select( SectionMealsSelectors.getIsLoading)); 
    }

    public isLoaded$() :Observable<boolean>{
        return this.store.pipe(select( SectionMealsSelectors.getIsLoaded)); 

    }

    public isError$() :Observable<boolean> {
        return this.store.pipe(select( SectionMealsSelectors.getIsError)); 
    }

    public resetFeedback(){
        this.store.dispatch( SectionMealsActions.resetFeedback()); 
    }

    get feedbackListener$(): Observable<{showFeedBack: boolean, feedBackMessage: string}> {
        return this.store.pipe(select(SectionMealsSelectors.getFeed)); 
    }

    public editMealInSection(sectionId: string, mealUpdated: Aliment) :  void{
        return this.store.dispatch(SectionMealsActions.editMealInSection({sectionId, mealUpdated}))
    }   
    
    public deleteMealFromSection(sectionId: string, mealId: string): void {
        this.store.dispatch(SectionMealsActions.deleteMealInSection({sectionId, mealId}))
    }
    public resetLoaded() : void {
        this.store.dispatch(SectionMealsActions.resetLoaded())
    }

    addGroupOfAliments(sectionId: string, aliments: Aliment[]) {
        this.store.dispatch(SectionMealsActions.addGroupOfAliments({sectionId,aliments}))
    }
}