import { createReducer, on } from '@ngrx/store';
import { typeModalSpecialization } from '@shared/enums';
import { ModalDataTypes } from '@shared/models';
import * as SharedActions from './user-interface.actions';


export interface ModalsAppState {
    isLoadingGlobalScreen: boolean, 
    loadingText : string, 
    modalData: ModalDataTypes,
    modalStatus: typeModalSpecialization,
    realHeight: number
}

export const initialState: ModalsAppState = {
    isLoadingGlobalScreen: false,
    loadingText: '',
    modalData: undefined,
    modalStatus: typeModalSpecialization.NoModalActive,
    realHeight: 0,

}

export const sharedReducer = createReducer(initialState,
    on(SharedActions.displayModal, (state, {typeOfModal, modalData}) => ({ ...state, modalStatus: typeOfModal, modalData, isLoadingGlobalScreen:false, loadingText: '' })), 
    on(SharedActions.hideModal, ( state ) => ({ ...state, modalStatus: typeModalSpecialization.NoModalActive})),
    on(SharedActions.updateRealHeight, (state, {newHeight}) => ({...state, realHeight: newHeight})),
    on(SharedActions.showGlobalLoading, (state, {customText}) => ({...state, isLoadingGlobalScreen: true, loadingText: customText })),
    on(SharedActions.hideGlobalLoading, (state) => ({...state, isLoadingGlobalScreen: false, loadingText: '' }))
);
