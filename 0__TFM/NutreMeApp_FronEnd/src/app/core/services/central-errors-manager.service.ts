import { HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";

@Injectable({
    providedIn: 'root'
})
export class CentralErrorManager {
    constructor(private router: Router) {

    }

    public analizeError(err: HttpErrorResponse) {
        console.log(err.error.error.name); 
        switch(err.error.error.name){
            case 'TokenExpiredError':
                this.router.navigate(['/login'])
                break; 
            default: 
        }
    }
}
    