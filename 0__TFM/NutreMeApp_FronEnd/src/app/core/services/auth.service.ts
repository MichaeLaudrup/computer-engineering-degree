import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User } from "@shared/models";
import { catchError, EMPTY, map, Observable } from "rxjs";
import { Jsend } from "src/app/shared/models/jsend.model";
import { environment } from "src/environments/environment";
import { CentralErrorManager } from "./central-errors-manager.service";

@Injectable({
    providedIn:'root'
})
export class authService  {
    constructor( private http: HttpClient, private errorManager: CentralErrorManager){
    }

    public signUp(newUser: User) : Observable<User>{
        return this.http.post<Jsend>(`${environment.apiUrlBase}/users/signup`, {...newUser}).pipe( map( signUpResponde => signUpResponde.data.user))
    }

    public logIn(newUser: User) : Observable<User>{
        return this.http.post<Jsend>(`${environment.apiUrlBase}/users/login`, {...newUser}).pipe( map( signUpResponde => signUpResponde.data.user))
    }

    public getUser() : Observable<User>{
        return this.http.get<{ status: string, data: {user: User}}>(`${environment.apiUrlBase}/users/get-user`)
            .pipe(map( jsendResponse => jsendResponse.data.user),
            catchError( (err) => {
                this.errorManager.analizeError(err); 
                return EMPTY;
            }))
    }
}