import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Aliment, SectionMeal } from "@shared/models";
import { concatMap, delay, EmptyError, map, Observable, of } from "rxjs";
import { deepCopiesUtils } from "src/app/shared/utils/deep-copies.utils";
import { environment } from "src/environments/environment";
@Injectable({
        providedIn:'root'
})
export class sectionMealsService {


    attachPhotoToSection(sectionId: string, photo: FormData) {
        return this.http.post<{status: string, data: {section: SectionMeal}}>(`${environment.apiUrlBase}/section-meals/${sectionId}`, photo)
    }
    private sectionMeals = [];  
    constructor(private http: HttpClient){}

    public getMealsFromServer(): Observable<SectionMeal[]> {
        return this.http.get<{status: string, data: {sections: SectionMeal[]}}>(`${environment.apiUrlBase}/section-meals`)
            .pipe(map( jsend => jsend.data.sections))
    }

    public addSectionMealToServer( newSectionMeal: SectionMeal): Observable<SectionMeal> {
        return this.http.post<{status:string, data: {section: SectionMeal}}>( `${environment.apiUrlBase}/section-meals`, {...newSectionMeal})
            .pipe( map( jsend => jsend.data.section)) 
    }

    public editSectionMealInServer( id: string, sectionUpdated: SectionMeal): Observable<SectionMeal>{
        return this.http.patch<{status:string, data: {section: SectionMeal}}>(`${environment.apiUrlBase}/section-meals/${id}`, {...sectionUpdated})
            .pipe( map( jsend => jsend.data.section)); 
    }

    public deleteSection(id: string): Observable<any> {
        return this.http.delete(`${environment.apiUrlBase}/section-meals/${id}`)
    }

    public addMealToSection(sectionId: string, newMeal: Aliment): Observable<SectionMeal> {
        return this.http.post<{status:string, data: {newMeal:Aliment}}>(`${environment.apiUrlBase}/meals`, {...newMeal}).pipe( concatMap( jsend => {
            return this.http.post<{status:string, data: {section:SectionMeal}}>(`${environment.apiUrlBase}/section-meals/${sectionId}/add-aliments`, { newMeals:[ jsend.data.newMeal._id]})
                .pipe( map(jsend => jsend.data.section))
        }))
    } 

    public editMealInSection(idSection: string, mealUpdated: Aliment): Observable<{idSec: string, mealUpd: Aliment}> {
        this.sectionMeals =  deepCopiesUtils.copySectionsWithOneMealEdited(this.sectionMeals, idSection, mealUpdated)
        return of({idSec: idSection, mealUpd:mealUpdated}).pipe(delay(1000)); 
    }

    public deleteMealInSection(idSection: string, idMeal: string): Observable<{secId:string, melId:string}>{
        return this.http.post(`${environment.apiUrlBase}/section-meals/${idSection}/delete-aliments`, { deletedMeals: [idMeal]}).pipe( map( () => ({ secId: idSection, melId: idMeal})))  
    }

    addGroupOfAliments(sectionId: string, aliments: Aliment[]) {
        return this.http.post<{status:string, data: {section:SectionMeal}}>(`${environment.apiUrlBase}/section-meals/${sectionId}/add-aliments`, { newMeals:aliments.map(aliment => aliment._id)})
                .pipe( map(jsend => {
                    return jsend.data.section
                }))
    }
}