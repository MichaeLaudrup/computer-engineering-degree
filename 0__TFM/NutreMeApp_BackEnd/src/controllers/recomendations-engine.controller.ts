import { NextFunction, Request, Response } from "express"
import { MealModel } from "../models/meal.model";
import { UserDataModel } from "../models/user-data.model";
import RulesEngine from "../services/rules-engine/rules-engine.service"
import { OperationalError } from "../shared/classes/error.interface";

export const runEngine = async(req: Request, res:Response, next: NextFunction) => {
    try{
        const recommendedMeal = (<string>req.query['for']).toUpperCase() ?? 'GENERICO'; 
        const engine = new RulesEngine(); 
        const userData = await UserDataModel.findOne({userId: req.body.user._id}); 
        if(userData){
            const complexQuery = await engine.probeFact({feedingType: userData.feedingType, allergens: userData.allergens, recommendedMeal});
            console.log(JSON.stringify(complexQuery))
            const aliments = await MealModel.find({...complexQuery})
            res.status(200).json({
                status: 'success',
                length: aliments.length,
                data: {
                    name: recommendedMeal,
                    aliments
                }
                    
            })
        }else{
            throw new OperationalError('Este usuario no tienes datos asociados'); 
        }
    }catch (err) {
        next(err)
    }
}

