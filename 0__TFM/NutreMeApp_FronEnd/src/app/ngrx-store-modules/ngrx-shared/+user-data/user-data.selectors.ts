import { createFeatureSelector, createSelector } from '@ngrx/store';
import { SharedAppState } from '../shared.state';

export const getSharedSelector = createFeatureSelector<SharedAppState>('shared'); 

export const getObjetivo = createSelector(
    getSharedSelector,  (state: SharedAppState) => state['userData'].userData.nutritionalTarget
);

export const getDatosFisio = createSelector(
    getSharedSelector, (state: SharedAppState) => state['userData'].userData.fisiologicData
); 

export const getUserData = createSelector(
    getSharedSelector, (state: SharedAppState) => state['userData'].userData
); 

export const getUserDataMacro = ( isRepose?: boolean) => { 
    return createSelector(getSharedSelector, 
        (state: SharedAppState) => isRepose ? state.userData.userData.fisiologicData.macrosInRepose : state.userData.userData.fisiologicData.macrosWithActivity)
}
