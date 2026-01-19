import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { usersFacade } from "@ngrx/ngrx-shared";
import { map, mergeMap, Observable, of } from "rxjs";
import { authService } from "@core/services";

@Injectable()
export class userLogedGuard implements CanActivate, CanActivateChild{
    constructor(private userFacade: usersFacade, private router: Router, private authService: authService){
         
    }
    canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        return this.comprobeUserExist();
    }
    canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        return this.comprobeUserExist(); 
    }

    comprobeUserExist(){
        return this.userFacade.$user.pipe( mergeMap( user => {
            if(user === undefined){
                if(sessionStorage.getItem('JWTtoken')){
                    return this.authService.getUser().pipe( map(user => {
                        if(user){ 
                            this.userFacade.setUser(user);
                            return true;
                        } else {
                            return this.router.createUrlTree(['login'])
                        }; 
                    }))
                }else{
                    return of(this.router.createUrlTree(['login'])); 
                }
            }else{
                return of(true);
            }
        })); 
    }



}