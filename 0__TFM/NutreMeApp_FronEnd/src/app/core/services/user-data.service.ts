import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User, UserData } from "@shared/models";
import { catchError, EMPTY, map, Observable } from "rxjs";
import { userDataFromBackToFront } from "src/app/shared/utils/back-to-front-convertions.utils";
import { environment } from "src/environments/environment";
import { CentralErrorManager } from "./central-errors-manager.service";

@Injectable({ providedIn: 'root'})
export class UserDataService {
    constructor( private http: HttpClient, private centralErrorManager: CentralErrorManager){}
    /**
     * Este metodo introduce por primera vez los datos de usuario
     * @param userId Identificador de usuario
     * @param userData Informacion fisiologica del usuario
     */
    postUserData( userId: string, userData: UserData): Observable<UserData>{
        return this.http.post<{status: string, data: {userData: any}}>(`${environment.apiUrlBase}/user-data`, { userId, userData})
            .pipe( map(jsendResponse => { 
                const data = jsendResponse.data.userData; 
                const userDataParse: UserData = {
                    feedingType : data.feedingType,
                    nutritionalTarget: data.nutritionalTarget,
                    fisiologicData: {
                        activityIntesity: data.activityIntesity,
                        age: data.age,
                        weight: data.weight,
                        height: data.height,
                        gender: data.gender,
                        mba: data.mba,
                        mbaWithObjective: data.mbaWithObjetive,
                        mbaWithActivityAndObjetive: data.mbaWithActivityAndObjetive,
                        diaryWater: data.diaryWater,
                        imc: data.imc,
                        macrosInRepose: data.macrosInRepose,
                        macrosWithActivity: data.macrosWithActivity
                    }
                }
                return userDataParse
            }), catchError( err => {
                this.centralErrorManager.analizeError(err)
                return EMPTY; 
            }))
    }

    updateUserData(userId: string, userDataUpdate: UserData){

    }

    getUserData(): Observable<UserData>{
        return this.http.get<{status:string, data: {userData: any}}>(`${environment.apiUrlBase}/user-data`).pipe( map( jsendResponse => {
            return userDataFromBackToFront(jsendResponse.data.userData); 
        }))
    }
}