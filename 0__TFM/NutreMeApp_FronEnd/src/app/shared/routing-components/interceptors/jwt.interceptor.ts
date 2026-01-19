import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, EMPTY, Observable, tap } from "rxjs";
import { CentralErrorManager } from "src/app/core/services/central-errors-manager.service";

@Injectable()
export class JWTInterceptor implements HttpInterceptor {
    constructor( private errorManager: CentralErrorManager ){

    }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
        if(sessionStorage.getItem('JWTtoken') ){
            req = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${sessionStorage.getItem('JWTtoken')}`
                }
            })
        }
        return next.handle(req).pipe( tap( evt => {
            if(evt instanceof HttpResponse){
                if(evt.body && evt.body['status'] && evt.body['status'] === 'success'){
                    if(evt.body.data.token){
                        sessionStorage.setItem('JWTtoken', evt.body.data['token'])
                    }
                }
            }
        }), catchError( (err) => {
            this.errorManager.analizeError(err)
            return EMPTY
        })); 
    }
}