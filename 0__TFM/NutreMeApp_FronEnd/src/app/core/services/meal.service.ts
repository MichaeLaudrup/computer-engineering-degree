import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Aliment} from "@shared/models"; 
import { Observable, map, of } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn:'root'
})
export class mealsService {

  constructor( private hhtpClientService : HttpClient){

  }
  aliments= []; 
  public getAllMeals() : Observable<Aliment[]>{
    return this.hhtpClientService
      .get<{status:string, data: {meals:Aliment[]}}>('http://192.168.0.18:8000/api/v1/meals')
      .pipe( map( jsend => jsend?.data.meals)); 
  }

  public getAllMealsFilterByFieldsAndValues(fields:string[], values: string[]) : Observable<Aliment[]> {
    let queryString = '?'
    if(fields.length > 1){
      queryString += fields.map( (fieldName,index) => {
        return `${fieldName}=${values[index]}${index < fields.length -1 ? '&' : ''}`
      }).join('');
    }else{
      queryString += `${fields[0]}=${values[0]}`
    }
    return this.hhtpClientService
      .get<{status:string, data: {meals:Aliment[]}}>(`${environment.apiUrlBase}/meals${queryString}`)
      .pipe( map( jsend => jsend?.data.meals)); 
  }

  public addNewMeal( newMeal: Aliment): Observable<Aliment> {
    return this.hhtpClientService.post<{status: string, data: { newMeal: Aliment}} >(`${environment.apiUrlBase}/meals`,newMeal)
    .pipe( map( jsend => (jsend.data.newMeal)))
  }
}
