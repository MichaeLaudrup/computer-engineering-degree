import { createReducer, on } from "@ngrx/store";
import { User } from "@shared/models";
import * as UsersActions from './users.actions'; 

export interface UserState {
    isLoged: boolean,
    user: User,
}

const initialState: UserState = {
    isLoged: false, 
    user: undefined
}

export const usersReducer = createReducer(initialState, 
    on(UsersActions.signUp, (state, { user}) => ({ ...state})),
    on(UsersActions.signUpSuccess, (state, {user}) => ({...state, user: {...user}, isLoged:true})),
    on(UsersActions.logIn, (state, {user}) => ({ ...state, isLoged: false}) ),
    on(UsersActions.logInSuccess, (state, {user}) => ({ ...state, user: {...user}, isLoged: true}) ),
    on(UsersActions.resetLoaded, (state) =>({...state, isLoged:false})),
    on(UsersActions.setUser, (state, {user}) => ({...state, user: { ...user}, isLoged: true})),
    on(UsersActions.logout, (state ) => ({...initialState}))
)