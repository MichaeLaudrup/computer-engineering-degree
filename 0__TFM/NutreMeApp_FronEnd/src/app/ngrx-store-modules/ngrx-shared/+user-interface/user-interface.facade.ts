import { Injectable } from "@angular/core";
import { select, Store } from "@ngrx/store";
import * as sharedActions from './user-interface.actions'; 
import * as sharedSelector from './user-interface.selectors'; 
import { typeModalSpecialization } from "@shared/enums";
import { ModalDataTypes } from "@shared/models";
@Injectable()
export class sharedFacadeService {
    constructor( private store: Store<any> ){}

    hideModal() {
        this.store.dispatch(sharedActions.hideModal()); 
    }

    displayModal( typeOfModalToShow: typeModalSpecialization, modalData: ModalDataTypes){
        this.store.dispatch(sharedActions.displayModal({typeOfModal: typeOfModalToShow, modalData  }))
    }

    updateHeight(newHeight: number){
        this.store.dispatch(sharedActions.updateRealHeight({newHeight}))
    }

    get modalStatus$(){
        return this.store.pipe( select( sharedSelector.getModalStatus))
    }

    get modalData$(){
        return this.store.pipe( select( sharedSelector.getModalData)); 
    }

    get realHeight$() {
        return this.store.pipe( select(sharedSelector.getRealHeight)); 
    }

    





}