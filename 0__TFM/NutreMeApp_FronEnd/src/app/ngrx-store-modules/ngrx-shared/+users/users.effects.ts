import { Injectable } from "@angular/core";
import { authService } from "@core/services";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { catchError, EMPTY, map, mergeMap, tap } from "rxjs";
import * as UserActions from "./users.actions";

@Injectable()
export class userEffects {
    constructor(
        private actions$: Actions,
        private authService: authService
    ){}

    userSignUp$ = createEffect(
        () => this.actions$.pipe(
                ofType( UserActions.signUp.type),
                mergeMap( ({user}) => this.authService.signUp(user).pipe(
                    map( user => ( { type: UserActions.signUpSuccess.type, user})),
                    catchError( () => EMPTY)))
                ))
    
    userLogIn$ = createEffect(
        () => this.actions$.pipe(
                ofType( UserActions.logIn),
                mergeMap( ({user}) => this.authService.logIn(user).pipe(
                    map( user => ( { type: UserActions.logInSuccess.type, user})),
                    catchError( () => EMPTY)))
                ))
}