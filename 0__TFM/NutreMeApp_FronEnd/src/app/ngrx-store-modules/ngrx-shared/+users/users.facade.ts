import { Injectable } from "@angular/core";
import { select, Store } from "@ngrx/store";
import { User } from "@shared/models";
import { UserState } from "./users.reducer";
import * as UserActions from './users.actions'; 
import * as UserSelector from './users.selectors'; 

@Injectable()
export class usersFacade {
    constructor( private store: Store<UserState>){

    }
    public signUp(user:User){
        this.store.dispatch(UserActions.signUp({user})); 
    }

    public logIn(user:User){
        this.store.dispatch(UserActions.logIn( {user}))
    }

    public setUser(user:User){
        this.store.dispatch(UserActions.setUser({user}))

    }

    public get $user(){
        return this.store.pipe(select(UserSelector.getUserStatus));
    }

    public get userLoaded$(){
        return this.store.pipe(select(UserSelector.getUserLoaded))
    }

    public resetIsLoaded() {
        this.store.dispatch(UserActions.resetLoaded())
    }

    public logout() {
        this.store.dispatch(UserActions.logout()); 
    }
}