import { NextFunction, Request, Response } from "express"
import { dailyMealsRegisterModel } from "../models/daily-meals-register.model"


export const addNewDailyMealsRegister = async(req: Request, res:Response, next: NextFunction) => {
    try{
        let newDailyMealsRegister
        if(req.body._id === 'none'){
            newDailyMealsRegister = await dailyMealsRegisterModel.create({
                date: req.body.date,
                scheduledMeals: [...req.body.scheduledMeals],
                userId: req.body.user._id
            }); 
        }else{
            newDailyMealsRegister = await dailyMealsRegisterModel.findByIdAndUpdate(req.body._id,{
                date: req.body.date,
                scheduledMeals: [...req.body.scheduledMeals],
                userId: req.body.user._id
            }, {
                new: true, //return de "new" document
                runValidators: true,
            }); 
        }
        newDailyMealsRegister = await newDailyMealsRegister.populate('scheduledMeals.aliments')
        res.status(201).json({
            status: 'sucess',
            data:{
                dailyMealsRegister: newDailyMealsRegister
            }
        })
    }catch (err) {
        next(err)
    }
}




export const getAllMyDailyMealsRegister = async(req: Request, res: Response, next: NextFunction) => {
    try{
        const MyDailyRegisters = await dailyMealsRegisterModel.findOne({date: new Date(<string>req.query.date).toISOString(), userId: req.body.user._id}).populate('scheduledMeals.aliments');
        res.status(201).json({
            status: 'success',
            data:{
                dailyRegister: MyDailyRegisters
            }
        })
    }catch(err){
        next(err); 
    }
}