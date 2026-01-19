import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as SectionMealsReducer from './section-meals.reducer'; 


export const getSharedAppSelector = createFeatureSelector<SectionMealsReducer.SectionMealState>('section_meals'); 

export const getListSections = createSelector(
    getSharedAppSelector,  (state: SectionMealsReducer.SectionMealState) => state.sections
);

export const sectionsAndLoaded = createSelector(
    getSharedAppSelector, (state: SectionMealsReducer.SectionMealState) => ({sections: state.sections, loaded: state.loaded})
); 

export const getSectionById = (id: string)  => createSelector(
    getSharedAppSelector, (state: SectionMealsReducer.SectionMealState) => ( state.sections.find( (section) => section._id === id ))
)

export const getIsLoading = createSelector(
    getSharedAppSelector, (state: SectionMealsReducer.SectionMealState) => ( state.loading)
)

export const getIsLoaded = createSelector(
    getSharedAppSelector, (state: SectionMealsReducer.SectionMealState) => ( state.loaded)
)

export const getIsError = createSelector(
    getSharedAppSelector, (state: SectionMealsReducer.SectionMealState) => ( state.error)
)

export const getFeed = createSelector(
    getSharedAppSelector, (state: SectionMealsReducer.SectionMealState) => ( state.feedBack)
)

