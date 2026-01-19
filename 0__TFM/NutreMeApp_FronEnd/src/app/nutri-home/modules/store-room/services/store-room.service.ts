import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable()
export class StoreRoomService {
    private _hasBeenClickedEditMode: BehaviorSubject<boolean> = new BehaviorSubject(false);
    public readonly editModeClicked$ = this._hasBeenClickedEditMode.asObservable();  
    public _hasBeenClickedCreateItem: BehaviorSubject<boolean> = new BehaviorSubject(false); 
    public readonly createItemClicked$ = this._hasBeenClickedCreateItem.asObservable(); 

    public _actionsTextEmited : BehaviorSubject<string[]> = new BehaviorSubject([]);  
    public readonly actionsTextEmited = this._actionsTextEmited.asObservable(); 

    public setClickedCreate( hasBeenClicked: boolean) {
        this._hasBeenClickedCreateItem.next(hasBeenClicked); 
    }

    public setClickedEditmode( hasBeenClicked: boolean){
        this._hasBeenClickedEditMode.next(hasBeenClicked); 
    }

    public setActionsText(actionTexts: string[]){
        this._actionsTextEmited.next(actionTexts); 
    }
}