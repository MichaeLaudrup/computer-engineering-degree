import { Macronutrients } from "./macronutrients.model";
import { Aliment } from "./meal.model"

export class ScheduledMeals {
    totalMacronutrients: Macronutrients = {
        carbohydrates:0,
        proteins: 0,
        fats: 0
    };
    totalKcal: number; 

    constructor(public name: string, public aliments: Aliment[]){
        this.aliments = aliments; 
        this.calculateMacronutrients()
    }

    calculateMacronutrients(): void {
        const macroData = this.aliments.reduce((acc: {carbo:number, proteins:number, fats: number, kcal: number}, aliment) => {
            return {
                carbo: acc.carbo + aliment.carboHydrates,
                proteins: acc.proteins + aliment.proteins,
                fats: acc.fats + aliment.fats,
                kcal: acc.kcal + aliment.kcal
            }
        }, {carbo: 0, proteins: 0, fats:0, kcal:0})
        this.totalMacronutrients.carbohydrates = macroData.carbo; 
        this.totalMacronutrients.fats = macroData.fats; 
        this.totalMacronutrients.proteins = macroData.proteins; 
        this.totalKcal = macroData.kcal
    }

    public deleteOneAliment(alimentIndex: number){
        this.aliments =<Aliment[]>[ ...this.aliments.slice(0, alimentIndex), ...this.aliments.slice(alimentIndex +1)]; 
        this.calculateMacronutrients(); 
    }

    public addOneAliment(aliment: Aliment){
        this.aliments = <Aliment[]>[...this.aliments, aliment]; 
        this.calculateMacronutrients(); 
    }
}