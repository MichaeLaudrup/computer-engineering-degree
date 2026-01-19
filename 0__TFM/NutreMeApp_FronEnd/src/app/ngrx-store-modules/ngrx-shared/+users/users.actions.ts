import { createAction, props } from "@ngrx/store";
import { User } from "src/app/shared/models/user.model";

export const signUp = createAction(
    '[Shared/users] SignUp User',
     props<{ user: User}>())
export const signUpSuccess = createAction(
    '[Shared/users] SignUp User Success',
    props<{user:User}>())
export const signUpFailure = createAction('[Shared/users] SignUp User Failure', );

export const setUser = createAction('[Shared/users] Setting a User', props<{user: User}>())
export const logIn = createAction('[Shared/users] Login User', props<{user:User}>())
export const logInSuccess = createAction('[Shared/users] login User success', props<{user:User}>())
export const logInFailure= createAction('[Shared/users] login User failure');

export const resetLoaded = createAction('[Shared/users] Reset user loaded'); 

export const logout = createAction('[Shared/users] Logout'); 




