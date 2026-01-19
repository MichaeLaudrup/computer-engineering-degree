
import { Action, createReducer, on } from '@ngrx/store';
import { SectionMeal } from '@shared/models';
import { deepCopiesUtils } from 'src/app/shared/utils/deep-copies.utils';
import * as MealSectionsActions from './section-meals.actions'; 

export interface SectionMealState {
    sections: SectionMeal[],
    loading: boolean,
    loaded: boolean,
    error: any,
    feedBack: { 
        showFeedBack: boolean,
        feedBackMessage: string
    }
}

export const initialState: SectionMealState = {
    sections: [],
    loading: false,
    loaded: false,
    error: false,
    feedBack: { 
        showFeedBack: false,
        feedBackMessage: ''
    }

}

export const sectionMealsReducer = createReducer<SectionMealState, Action> (initialState,
    on( MealSectionsActions.getMeals, (state) => ({
        ...state,
        loading: true,
    })),
    on(MealSectionsActions.getMealsSuccess, (state, {sections}) => ({
        ...state,
        loading: false,
        loaded: true,
        sections
    })),

    on(MealSectionsActions.addSection, (state) => ({
        ...state,
        feedBack: {
            showFeedBack: false,
            feedBackMessage: ''
        },
        loading: true,
        loaded: false
    })),
    on(MealSectionsActions.addSectionSuccess, (state, {mealAdded}) => { 
        return {
        ...state,
        loading: false,
        loaded: true,
        feedBack: { 
            showFeedBack: true,
            feedBackMessage: 'Seccion insertada con exito'
        },
        sections: [
            ...state.sections, 
            mealAdded
        ]
    };
    }),
    on(MealSectionsActions.editSection, (state, _) => ({
        ...state,
        loading: true,
    })),
    on(MealSectionsActions.editSectionSuccess, (state, {sectionsEdited}) => {
        return {
            ...state,
            loaded:true,
            loading: false,
            sections: sectionsEdited,
            feedBack: { 
                showFeedBack: true,
                feedBackMessage: 'Sección editada con exito'
            },
        }
    }),

    on(MealSectionsActions.deleteSection, (state) => ({...state, loading: true, loaded: false})), 
    on(MealSectionsActions.deleteSectionSuccess, (state) => ({ 
        ...state, 
        loaded: true,
        loading: false,
        feedBack: {
            showFeedBack: true,
            feedBackMessage: 'Eliminado con éxito'
        },
    })),

    on(MealSectionsActions.addMealToSection, (state) => ({
        ...state,
        loading: true,
        loaded: false
    })), 
    on(MealSectionsActions.addMealToSectionSuccess, (state, {idSection, newMealWithId}) => ({
        ...state,
        loading: false,
        loaded: true,
        feedBack: { 
            showFeedBack: true,
            feedBackMessage: 'Nuevo alimento insertado con exito'
        },
    })),
    on(MealSectionsActions.editMealInSection, (state, {sectionId, mealUpdated}) => ({
        ...state,
        loading: true,
        loaded: false, 
        feedBack: { 
            showFeedBack: false,
            feedBackMessage: ''
        },

    })),

    on(MealSectionsActions.editMealInSectionSuccess, (state, {sectionId, mealUpdated}) => ({
        ...state,
        loading: false,
        loaded: true, 
        feedBack: { 
            showFeedBack: true,
            feedBackMessage: 'alimento editado con exito'
        },
        sections: deepCopiesUtils.copySectionsWithOneMealEdited(state.sections, sectionId, mealUpdated)
    })),


    on(MealSectionsActions.deleteMealInSection,((state) => ({
        ...state,
        isLoading: true,
        loaded: false,
        error: false,
        feedBack: {
            showFeedBack: false,
            feedBackMessage: ''
        }
    }))),
    on(MealSectionsActions.deleteMealInSectionSuccess,((state, { mealId,sectionId}) => ({
        ...state,
        isLoading: false,
        loaded: true, 
        error: false,
        feedBack: {
            showFeedBack: true,
            feedBackMessage: 'Eliminado con éxito'
        },
        sections: deepCopiesUtils.copySectionsWithOneMealDeleted(state.sections,sectionId,mealId)
    }))),


    on(MealSectionsActions.resetFeedback, (state) => ({
        ...state,
        feedBack: { 
            showFeedBack: false,
            feedBackMessage: ''
        },

    })),
    on(MealSectionsActions.resetLoaded, (state) => ({
        ...state, 
        loaded: false
    })),
    on(MealSectionsActions.addGroupOfAliments, (state) => ({...state}))
);
