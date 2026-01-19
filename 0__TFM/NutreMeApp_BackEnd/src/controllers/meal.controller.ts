import { NextFunction, Request, Response} from 'express'; 
import { OperationalError } from '../shared/classes/error.interface';
import { copyParamsAndDeleteEspecialOperations, processFilteredFields, processRequestPagination, processSortByFields, queryFieldsExistInModel, requestContainsParams } from '../utils/query-adaptation';
import { fieldOfMealModel, MealModel} from './../models/meal.model'; 

/**
 * Este metodo recupera todos los alimentos
 * @param req Requerimiento recibido desde front
 * @param res Respuesta que se enviara
 */
export const getAllMeals = async (req: Request, res: Response, next: NextFunction) => {
    try {
        let queryStr = undefined; 
        const queryParams = copyParamsAndDeleteEspecialOperations(req); 
        if(requestContainsParams(req)){
            /* Control de que todos los parametros de consulta existen en el modelo Meal*/
            if(!queryFieldsExistInModel(Object.keys(queryParams), fieldOfMealModel)){
                throw 'Some query param doesn\'t exist in meal model'; 
            }
            /* Adaptacion de la consulta para casos en lo que se filtre con "mayor, mayor igual, menor o menor igual" */
            queryStr = JSON.stringify(req.query);
            queryStr = queryStr.replace(/\b(gte|gt|lte|lt|ne|regex)\b/g, match => (match === 'regex') ? `$options":"i","$${match}` : `$${match}` ); 
        }
        let query = MealModel.find((queryStr !== undefined) ? ({...JSON.parse(queryStr ), $options:"i"}) :{ ...queryParams}); 

        /* En caso de que la query tenga ordenacion pues pa lante */
        query = processSortByFields(req, query, fieldOfMealModel); 

        /* Filtracion de campos */
        query = processFilteredFields(req, query, fieldOfMealModel);
        
        const numItems = await MealModel.countDocuments(); 
        query = processRequestPagination(req, query, numItems); 

        const meals = await query; 
        res.status(201).json({
            status:'success',
            results: meals.length,
            data : {
               meals
            }
        })
    }catch(err){
        next(err); 
    }
}

export const getMealById = async (req: Request, res: Response, next: NextFunction) => {
    try{
        const mealSearched = await MealModel.findById(req.params['id']);
        if(!mealSearched) {
            throw( new OperationalError('No meal found for that ID', 404))
        }
        
        return res.status(200).json({
            status: 'success',
            data: {
                meal: mealSearched
            }
        })
    }catch(err){
        next(err); 
    }
}

export const addNewMeal = async (req: Request, res:Response, next: NextFunction) => {
    try{
        const meal = await MealModel.find({name: req.body.name}); 
        if(meal.length > 0){
            throw new OperationalError('No puede haber nombres de alimentos duplicados', 400)
        }
        const newMeal = await MealModel.create({
            ...req.body
        }
        );
        res.status(201).json({
            status: 'sucsess',
            data: {
                newMeal
            }
        }); 
    }catch(err) {
        next(err);
    }
}

export const updateMeal = async(req: Request, res:Response, next: NextFunction) => {
    try{
        const mealEdited = await MealModel.findByIdAndUpdate(req.params['id'], req.body, { 
            new: true, //return de "new" document
            runValidators: true,
        });
        return res.status(201).json(mealEdited); 
    }catch (err) {
        next(err);
    }
}

export const deleteMeal = async(req: Request, res:Response, next: NextFunction) => {
    try{
        const mealDeleted = await MealModel.findByIdAndDelete(req.params['id']); 
        if(!mealDeleted){
            throw new OperationalError(`Can't delete one meal that doesn't exist`, 404); 
        }
        return res.status(204).json(mealDeleted); 
    }catch (err) {
        next(err); 
    }
}

export const getMealStats = async(req: Request, res:Response, next: NextFunction) => {
    try{
        const result = {}
        return res.status(204).json(result)
    }catch (err) {
        next(err); 
    }
}