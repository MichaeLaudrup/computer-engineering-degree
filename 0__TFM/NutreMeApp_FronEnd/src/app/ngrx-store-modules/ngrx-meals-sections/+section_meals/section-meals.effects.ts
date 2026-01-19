import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { catchError, concatMap, map, mergeMap, of, switchMap, take } from "rxjs";
import { sectionMealsService } from "src/app/core/services/section-meals.service";
import * as SectionMealsActions from "./section-meals.actions"; 
@Injectable()
export class SectionMealsEffects {
    constructor(
        private actions$: Actions,
        private sectionMealsService: sectionMealsService
    ){}

    loadSectionsMeal$ = createEffect(
        () => this.actions$.pipe(
            ofType( SectionMealsActions.getMeals.type),
            mergeMap( (_) => this.sectionMealsService.getMealsFromServer().pipe(
                map( sections => ({ type: SectionMealsActions.getMealsSuccess.type, sections})),
                catchError( (payload) => of(({ type: SectionMealsActions.getMealsFailure.type, payload})))
                ))
        )
    ); 

    createNewSectionMeal$ = createEffect(
        () => this.actions$.pipe(
            ofType(SectionMealsActions.addSection.type),
            concatMap( ( {newSection, photo}) => this.sectionMealsService.addSectionMealToServer( newSection).pipe(
                concatMap( 
                    newSectionWithId => {
                        if (photo) {
                            return this.sectionMealsService.attachPhotoToSection(newSectionWithId._id, photo)
                                .pipe( concatMap(section => [
                                    ({type: SectionMealsActions.addMealToSectionSuccess.type, newSectionWithId}),
                                    { type: SectionMealsActions.getMeals.type}
                                ]))
                        } else {
                            return [
                                {type: SectionMealsActions.addMealToSectionSuccess.type, newSectionWithId},
                                { type: SectionMealsActions.getMeals.type}
                            ]
                        }
                    }
                )
            ))
        )
    );
    
    editMealSection$ = createEffect(
        () => this.actions$.pipe(
            ofType(SectionMealsActions.editSection.type),
            mergeMap(({ id, section}) => this.sectionMealsService.editSectionMealInServer(id, section).pipe(
                map( (sectionsEdited) => ({type: SectionMealsActions.editSectionSuccess.type, sectionsEdited})))
            )
        )
    )

    deleteMealSection$ = createEffect(
        () => this.actions$.pipe(
            ofType(SectionMealsActions.deleteSection.type),
            concatMap(({id}) => this.sectionMealsService.deleteSection(id).pipe(
                concatMap( () => [
                    {type: SectionMealsActions.deleteSectionSuccess.type},
                    { type: SectionMealsActions.getMeals.type}
                ]),
            ),
        )
    ))

    putMealInSection$ = createEffect(
        () => this.actions$.pipe(
            ofType(SectionMealsActions.addMealToSection.type),
            concatMap(({idSection, newMeal}) => this.sectionMealsService.addMealToSection(idSection, newMeal).pipe(
                concatMap((newMealWithId) => [
                    { type: SectionMealsActions.addMealToSectionSuccess.type, newMealWithId, idSection},
                    { type: SectionMealsActions.getMeals.type}
                ])
            ))
        )
    )

    updateMealInSection$ = createEffect(
       () => this.actions$.pipe(
            ofType(SectionMealsActions.editMealInSection.type),
            concatMap(({sectionId, mealUpdated}) => this.sectionMealsService.editMealInSection(sectionId,mealUpdated).pipe(
                concatMap(({idSec, mealUpd}) => [
                    ({ type: SectionMealsActions.editMealInSectionSuccess.type, sectionId: idSec, mealUpdated: mealUpd}),
                    { type: SectionMealsActions.getMeals.type}
                ])
            ))
       ) 
    )

    deleteMealInSection$ = createEffect(
        () => this.actions$.pipe(
            ofType(SectionMealsActions.deleteMealInSection.type),
            mergeMap(({sectionId, mealId}) => this.sectionMealsService.deleteMealInSection(sectionId,mealId).pipe(
                map( ({secId, melId}) => ({type: SectionMealsActions.deleteMealInSectionSuccess.type, mealId: melId, sectionId: secId })))
            ),
        )
    )

    addGroupOfMeals$ = createEffect(
        () => this.actions$.pipe(
            ofType(SectionMealsActions.addGroupOfAliments.type),
            mergeMap(({sectionId, aliments}) => this.sectionMealsService.addGroupOfAliments(sectionId,aliments).pipe(
                map( ( section) => ({type: SectionMealsActions.getMeals.type})))
            ),
        )
    )
}