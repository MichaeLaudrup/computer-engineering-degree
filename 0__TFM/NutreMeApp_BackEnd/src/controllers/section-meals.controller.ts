import { NextFunction, Request, Response } from "express"
import mongoose from "mongoose"
import { SectionMealModel } from "../models/section-meal.model"
import { OperationalError } from "../shared/classes/error.interface"

export const addSectionMeals = async(req: any, res:Response, next: NextFunction) => {
    try{
        const section = await SectionMealModel.create({
            name: req.body.name,
            meals: [...req.body?.meals] ,
            imgPath: req.file?.filename ?? undefined,
            userId: req.body.user._id
        })

        res.status(201).json({
            status: 'success',
            data: {
                section
            }
        })
    }catch (err) {
        next(err)
    }
}

export const getMySectionMeals = async(req: Request, res:Response, next: NextFunction) => {
    try{
        const sections = await SectionMealModel.find({userId: req.body.user._id}).populate('meals');
        res.status(201).json({
            status: 'success',
            data: {
                sections
            }
        })
    }catch (err) {
        next(err)
    }
}

export const attachPhotoToSection = async(req: any, res:Response, next: NextFunction) => {
    try{
        const section = await SectionMealModel.findByIdAndUpdate(req.params['id'], {
            imgPath: req.file.filename
        },{ 
            new: true, //return de "new" document
            runValidators: true,
        }); 
        res.status(200).json({
            status: 'success',
            data: {
                section
            }
        })
    }catch (err) {
        next(err)
    }
}
export const deleteSectionMeal = async(req: Request, res:Response, next: NextFunction) => {
    try{
        if(req.body.user){
            const sectionToDelete = await SectionMealModel.findOne({_id: req.params['id'], userId: req.body.user._id}); 
            if(sectionToDelete){
                sectionToDelete.delete(); 
                return res.status(204).json({
                    status: 'success',
                })
            }else{
              throw new OperationalError('La seccion de alimentos especificada no existe', 404)  
            }
        }else{
            throw new OperationalError('Errores en los datos de usuario')
        }
    }catch (err) {
        next(err)
    }
}

export const updateSecionMeal = async(req: Request, res:Response, next: NextFunction) => {
    try{
        const section = await SectionMealModel.findOneAndUpdate({_id: req.params['id'], userId: req.body.user._id}, {
            name: req.body.name
        },{ 
            new: true, //return de "new" document
            runValidators: true,
        }); 
        return res.status(201).json({
            status: 'success',
            data: {
                section
            }
        })
            
        
    }catch (err) {
        next(err)
    }
}

export const addFoodToSection = async(req: Request, res:Response, next: NextFunction) => {
    try{
        let sectionMeal = await SectionMealModel.findByIdAndUpdate(req.params['id'], {
            $push: {"meals": [...req.body.newMeals]}
        },{new: true, upsert:true}); 
        return res.status(201).json({
            status: 'success',
            data: {
                section: sectionMeal
            }
        }); 
    }catch (err) {
        next(err)
    }
}

export const deleteFoodInSection = async(req: Request, res:Response, next: NextFunction) => {
    try{
        let sectionMeal = await SectionMealModel.findByIdAndUpdate(req.params['id'], {
            $pullAll: {'meals':[ ...req.body.deletedMeals]}
        }, { new: true, upsert: true});

        res.status(204).json({
            status: 'success',
            section: sectionMeal
        })
    }catch (err) {
        next(err)
    }
}