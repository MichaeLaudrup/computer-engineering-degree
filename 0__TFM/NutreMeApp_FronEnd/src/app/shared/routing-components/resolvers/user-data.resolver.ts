import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from "@angular/router";
import { MealSectionsFacade } from "@ngrx/ngrx-section-meals";
import { UserDataFacadeService, usersFacade } from "@ngrx/ngrx-shared";
import { concatMap, Observable, take, of, map, mergeMap } from "rxjs";
import { UserDataService } from "src/app/core/services/user-data.service";
import { UserData } from "../../models/fisiologicData.model";
import { SectionMeal } from "../../models/section-meal.model";


@Injectable()
export class UserDataResolver implements Resolve<UserData>{
    constructor( 
        private router: Router,
         private userDataFacade: UserDataFacadeService,
         private userDataService: UserDataService,
         private userProfileFacada: usersFacade){}

        resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): UserData | Observable<UserData> | Promise<UserData> {
            return this.userDataFacade.userData$.pipe(take(1), concatMap( userData => {
                if(userData && userData.feedingType && userData.fisiologicData && userData.nutritionalTarget){
                    return of(userData); 
                }else{
                    return this.userDataService.getUserData().pipe( map( userData => {
                        this.userDataFacade.setFeedingType(userData.feedingType);
                        this.userDataFacade.setFisiologicData(userData.fisiologicData);
                        this.userDataFacade.setObjective(userData.nutritionalTarget); 
                        return userData; 
                    }))
                }
            }))
        }
    
}