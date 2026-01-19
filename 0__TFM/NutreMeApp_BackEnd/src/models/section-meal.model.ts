/* import { Aliment } from "./meal.model"; */
/* 
export class SectionMeal {
    private _id?: string 
    name: string
    meals: Aliment[]
    imgPath: string
    public set id( id: string){
        this._id = id; 
    }
} */

import mongoose from "mongoose";

const SectionMealSchema = new mongoose.Schema({
    name: {
        type: String,
        required: [true, 'Una seccion debe tener un nombre']
    },
    meals: {
        type: [mongoose.Schema.Types.ObjectId],
        ref: 'Meal',
        unique: true,
    },
    imgPath: {
        type: String,
        default: 'default-section-img.jpg'
    },
    userId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    }
})

export const SectionMealModel = mongoose.model('SectionMeals', SectionMealSchema, 'Sections-meals')