import { createFeatureSelector, createSelector } from '@ngrx/store';
import { SharedAppState } from '../shared.state';
import { UserState } from './users.reducer';

export const getUserAppSelector = createFeatureSelector<SharedAppState>('shared'); 
export const getUserStatus = createSelector(
    getUserAppSelector, ( state: SharedAppState) => state.user.user
); 
export const getUserLoaded = createSelector(
    getUserAppSelector, ( state: SharedAppState) => state.user.isLoged
); 