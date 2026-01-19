import { createAction, props } from '@ngrx/store';
import { Aliment, SectionMeal } from '@shared/models';

/* CREATE */
export const addSection = createAction('[Section Meals] Add Section Meal', props<{newSection: SectionMeal, photo?: FormData}>());
export const addSectionSuccess = createAction('[Section Meals] Add Section Meal sucess', props<{mealAdded: SectionMeal}>());
export const addSectionFailure = createAction('[Section Meals] Add section meal failure', props <{payload: any}>());

/* READ */
export const getMeals = createAction('[Section Meals] get Meals section');
export const getMealsSuccess = createAction('[Section Meals] get Meals section sucess', props<{sections: SectionMeal[]}>());
export const getMealsFailure = createAction('[Section Meals] get Meals section failure', props<{ payload: any}>());

/* UPDATE */
export const editSection = createAction('[Section Meals] Edit section meal', props<{id:string, section: SectionMeal}>());
export const editSectionSuccess = createAction('[Section Meals] Edit section meal success', props<{sectionsEdited: SectionMeal[]}>());
export const editSectionFailure = createAction('[Section Meals] Edit section meal failure');

/* DELETE */
export const deleteSection = createAction('[Section Meals] Delete secion meal', props<{id:string}>());
export const deleteSectionSuccess = createAction('[Section Meals] Delete secion meal success', props<{sectionsUpdated: SectionMeal[]}>());
export const deleteSectionFailure = createAction('[Section Meals] Delete secion meal failure');


/*=========== MEALS =================*/
/* CREATE */
export const addMealToSection = createAction('[Section Meals] Add  meal in a section', props<{idSection:string, newMeal:Aliment | Aliment[]}>());
export const addMealToSectionSuccess = createAction('[Section Meals] Add  meal in a section success', props<{idSection: string, newMealWithId:Aliment}>());
export const addMealToSectionError = createAction('[Section Meals] Add  meal in a section failure', props<{idSection:string, newMeal:Aliment}>());

/* UPDATE */
export const editMealInSection = createAction('[Section Meals] edit meal in a section', props<{sectionId:string, mealUpdated:Aliment}>());
export const editMealInSectionSuccess = createAction('[Section Meals] edit meal in a section success', props<{sectionId:string, mealUpdated:Aliment}>());
export const editMealInSectionError = createAction('[Section Meals] edit meal in a section failure', props<{payload: string}>());

/* DELETE */
export const deleteMealInSection = createAction('[Section Meals] delete meal in a section', props<{sectionId:string, mealId: string }>());
export const deleteMealInSectionSuccess = createAction('[Section Meals] delete new meal in a section success', props<{sectionId:string, mealId: string }>());
export const deleteMealInSectionError = createAction('[Section Meals] delete new meal in a section failure');


export const resetFeedback = createAction('[Section Meal] Reset FeedBack'); 
export const resetLoaded = createAction('[Section Meal] Reset loaded'); 

export const addGroupOfAliments = createAction(
    '[Section Meal] Add a group of aliments',
    props<{sectionId: string, aliments: Aliment[]}>()
)