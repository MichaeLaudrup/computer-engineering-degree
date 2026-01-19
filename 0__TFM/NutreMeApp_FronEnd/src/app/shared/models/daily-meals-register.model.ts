import { Macronutrients } from "./macronutrients.model";
import { ScheduledMeals } from "./scheduled-meals.model"

export class DailyMealsRegister {
    _id: string
    date: Date
    scheduledMeals: ScheduledMeals[];
    totalMacro: Macronutrients = {
        carbohydrates:0,
        fats: 0,
        proteins: 0
    }; 
    totalKcal: number = 0; 

    constructor(id: string, date: Date, scheduledMeals: ScheduledMeals[]) {
        this._id = id;
        this.date = date; 
        this.scheduledMeals = scheduledMeals.map( item => new ScheduledMeals(item.name, item.aliments));
        this.calculateTotalMacroAndKcal() 
    }

    public calculateTotalMacroAndKcal(){
        const nutriData= this.scheduledMeals.reduce( (acc: {carbohydrates: number, proteins: number, fats: number, kcal: number}, scheduledMeal) => {
            return {
                carbohydrates: acc.carbohydrates + scheduledMeal.totalMacronutrients.carbohydrates,
                proteins: acc.proteins + scheduledMeal.totalMacronutrients.proteins,
                fats: acc.fats + scheduledMeal.totalMacronutrients.fats,
                kcal: acc.kcal + scheduledMeal.totalKcal,
            }
        }, {carbohydrates: 0, proteins: 0, fats: 0, kcal: 0})
        this.totalMacro.carbohydrates = nutriData.carbohydrates
        this.totalMacro.proteins = nutriData.proteins
        this.totalMacro.fats = nutriData.fats
        this.totalKcal = nutriData.kcal

    }
}