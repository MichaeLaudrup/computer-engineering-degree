import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { catchError, EMPTY, map, mergeMap, tap } from "rxjs";
import { UserDataService } from "src/app/core/services/user-data.service";
import * as UserDataActions from "./user-data.actions";

@Injectable()
export class userDataEffects {
    constructor(
        private actions$: Actions,
        private userDataService: UserDataService
    ){}

    userSignUp$ = createEffect(
        () => this.actions$.pipe(
                ofType( UserDataActions.uploadUserDataToServer),
                mergeMap( ({userId, userData}) => this.userDataService.postUserData(userId, userData).pipe(
                    map( user => ( { type: UserDataActions.uploadUserDataToServerSuccess.type, user})),
                    catchError( () => EMPTY)))
                ))
}