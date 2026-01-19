import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Aliment, ScheduledMeals } from "@shared/models";
import { catchError, EMPTY, map, Observable, of, scheduled } from "rxjs";
import { DailyMealsRegister } from "src/app/shared/models/daily-meals-register.model";
import { DateToString } from "src/app/shared/utils/convertions.utils";
import { environment } from "src/environments/environment";

@Injectable({providedIn: 'root'})
export class DailyMealsRegisterService{
    constructor(private http: HttpClient){
    }

    getMyDailyRegisterMeals(date: Date) : Observable<DailyMealsRegister>{
        const dateToString = DateToString(date); 
        return this.http.get<{status:string, data: {dailyRegister: DailyMealsRegister}}>(`${environment.apiUrlBase}/daily-meals-registers?date=${dateToString}`)
            .pipe(map( (jsendResponse) => (new DailyMealsRegister(jsendResponse.data.dailyRegister._id, jsendResponse.data.dailyRegister.date, jsendResponse.data.dailyRegister.scheduledMeals)))); 
    }

    getRecommendation(typeOfMeal: string):  Observable<ScheduledMeals>{
        return this.http.get<{status:string, length: number, data:{aliments: Aliment[], name: string}}>(`${environment.apiUrlBase}/recommendations-engine?for=${typeOfMeal}`)
            .pipe( map((jsendResponse) => ( new ScheduledMeals(jsendResponse.data.name, jsendResponse.data.aliments)) ) )
    }

    addOrUpdateDaily( dailyMealsRegister: DailyMealsRegister): Observable<DailyMealsRegister>{ 
        const dailyMealRegisterSimplified = {
            _id: dailyMealsRegister._id,
            date: new Date(dailyMealsRegister.date).toISOString().slice(0,10),
            scheduledMeals: dailyMealsRegister.scheduledMeals.map(( scheduledMeal: ScheduledMeals) => ({
                name: scheduledMeal.name,
                aliments: scheduledMeal.aliments.map( aliment => aliment._id) 
            }))
        }
        return this.http.post<{status:string, data: { dailyMealsRegister: DailyMealsRegister}}>(`${environment.apiUrlBase}/daily-meals-registers`, {...dailyMealRegisterSimplified})
            .pipe( map( jsend => jsend.data.dailyMealsRegister), catchError( err => { console.log(err); return of()}))


    }

}