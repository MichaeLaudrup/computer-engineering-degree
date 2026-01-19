import { createFeatureSelector, createSelector } from '@ngrx/store';
import { SharedAppState } from '../shared.state';

export const getSharedAppSelector = createFeatureSelector<SharedAppState>('shared'); 

export const getModalStatus = createSelector(
    getSharedAppSelector, ( state: SharedAppState) => state.modals.modalStatus
); 

export const getModalData = createSelector(
    getSharedAppSelector, (state: SharedAppState) => state.modals.modalData
)

export const getRealHeight = createSelector(
    getSharedAppSelector, (state: SharedAppState) => state.modals.realHeight
)

