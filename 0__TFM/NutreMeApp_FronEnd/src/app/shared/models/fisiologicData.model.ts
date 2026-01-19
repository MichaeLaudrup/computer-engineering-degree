import { FeedingType } from "../enums/feeding-type.enum";
import { NutritionTarget } from "../enums/nutrition-target.enum";
import { Macronutrients } from "./macronutrients.model";


export interface FisiologicData {
    height: number,
    weight: number,
    age: number,
    gender: string, 
    activityIntesity: number,
    mba?: number,          
    mbaWithObjective?:number,
    imc?: number,
    mbaWithActivityAndObjetive?: number,           
    diaryWater?: number,
    macrosInRepose?: Macronutrients,
    macrosWithActivity?: Macronutrients
}

export class UserData{
    constructor(public fisiologicData: FisiologicData,
                public nutritionalTarget: NutritionTarget,
                public feedingType: FeedingType, 
                public forbiddenAliments?: string[],
                public allergens?: string[]
                 ){}
}

