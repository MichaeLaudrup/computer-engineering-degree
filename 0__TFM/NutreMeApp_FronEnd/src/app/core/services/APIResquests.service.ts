import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ApiRequestService {
    constructor( private http: HttpClient){
        
    }
    getFoodData( query: string) : Observable<any>{
        const urlRequestet = 'https://api.calorieninjas.com/v1/nutrition?query=' + query; 
        return this.http.get(urlRequestet, { headers: { 'X-Api-Key': '6Zv37ju8hduI5Ax+xbx69A==3H2iTeRKPBbpGTho'}})
    }
}