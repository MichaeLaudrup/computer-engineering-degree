import { Injectable } from "@angular/core";
import { select, Store } from "@ngrx/store";
import { FisiologicData, UserData} from "@shared/models";
import { Observable } from "rxjs";
import { UserDataState } from "./user-data.reducer";
import * as USER_DATA_SELECTOR from './user-data.selectors'; 
import * as USER_DATA_ACTIONS from './user-data.actions'; 
import { FeedingType, NutritionTarget } from "@shared/enums";
import { Macronutrients } from "src/app/shared/models/macronutrients.model";
import { Allergens } from "src/app/shared/enums/allergens.enum";
@Injectable()
export class UserDataFacadeService {
    resetAll() {
        this.store.dispatch( USER_DATA_ACTIONS.resetAll())
    }
    constructor( private store: Store<UserDataState>) {
    }

    get fisiologicData$ () : Observable<FisiologicData>{
        return this.store.pipe( select( USER_DATA_SELECTOR.getDatosFisio)); 
    }

    get nutritionTarget$ () : Observable<NutritionTarget> {
        return this.store.pipe( select(USER_DATA_SELECTOR.getObjetivo)); 
    }

    get userData$() : Observable<UserData> {
        return this.store.pipe( select(USER_DATA_SELECTOR.getUserData))
    }

    public userMacronutriensData$(inReposeMacro: boolean) : Observable<Macronutrients> {
        return this.store.pipe( select(USER_DATA_SELECTOR.getUserDataMacro(inReposeMacro)))
    }

    public setFisiologicData( newFisiologicData: FisiologicData){
        this.store.dispatch(USER_DATA_ACTIONS.putFisiologicData({fisiologicData: newFisiologicData})); 
    }

    public setObjective(nutritionalTarget: NutritionTarget){
        this.store.dispatch(USER_DATA_ACTIONS.setTarget({nutritionalTarget})); 
    }

    public setFeedingType(feedingType: FeedingType){
        this.store.dispatch(USER_DATA_ACTIONS.postFeedingType({feedingType})); 
    }

    public uploadToServerUserData( userId: string, userData: UserData){
        this.store.dispatch(USER_DATA_ACTIONS.uploadUserDataToServer({userId, userData})); 
    }

    public setAllergens(allergens: Allergens[]){
        this.store.dispatch(USER_DATA_ACTIONS.setAllergens({allergens}))
    }

    public setForbiddenAliments(forbiddenAliments: string[]){
        this.store.dispatch(USER_DATA_ACTIONS.setForbiddenAliments({forbiddenAliments}))
    }



}