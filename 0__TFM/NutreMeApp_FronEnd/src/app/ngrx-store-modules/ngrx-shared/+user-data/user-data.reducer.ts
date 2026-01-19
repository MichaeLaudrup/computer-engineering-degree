import { state } from '@angular/animations';
import { createReducer, on } from '@ngrx/store';
import { FeedingType, NutritionTarget } from '@shared/enums';
import { UserData} from 'src/app/shared/models/fisiologicData.model';
import * as userDataActions from './user-data.actions';

export interface UserDataState {
    userData: UserData
}

export const initialState: UserDataState = {
    userData:  undefined
}

export const userDataReducer = createReducer(initialState,
    on(userDataActions.setTarget, (state, {nutritionalTarget}) => ({
        ...state,
        userData: {
            ...state.userData,
             nutritionalTarget
        }
    })),

    on(userDataActions.putFisiologicData, (state, {fisiologicData}) => ({
        ...state,
        userData: {
            ...state.userData,
            fisiologicData: {...fisiologicData}
        }
    })),
    on(userDataActions.postFeedingType, (state, {feedingType} ) => ({ ...state, userData: { ...state.userData, feedingType}})),
    on(userDataActions.uploadUserDataToServer, (state, { userId, userData}) => ({...state})),
    on(userDataActions.uploadUserDataToServerSuccess, (state, { user}) => ({
        ...state,
        userData: {
            nutritionalTarget: user.nutritionalTarget,
            fisiologicData: {...user.fisiologicData},
            feedingType: user.feedingType
        }
    })),
    on(userDataActions.setAllergens, (state, {allergens}) => ({...state, userData: { ...state.userData, allergens} })),
    on(userDataActions.setForbiddenAliments, (state, {forbiddenAliments}) => ({...state, userData: {...state.userData, forbiddenAliments: [...forbiddenAliments]}})),
    on(userDataActions.uploadUserDataToServerFailure, (state, { errorPayload}) => ({...state})),
    on(userDataActions.resetAll, (state) => ({ ...initialState}))
);