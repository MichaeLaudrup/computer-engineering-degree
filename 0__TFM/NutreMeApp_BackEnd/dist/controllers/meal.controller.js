"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.getMealStats = exports.deleteMeal = exports.updateMeal = exports.addNewMeal = exports.getMealById = exports.getAllMeals = void 0;
const query_adaptation_1 = require("../utils/query-adaptation");
const meal_model_1 = require("./../models/meal.model");
/**
 * Este metodo recupera todos los alimentos
 * @param req Requerimiento recibido desde front
 * @param res Respuesta que se enviara
 */
const getAllMeals = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        let queryStr = undefined;
        const queryParams = (0, query_adaptation_1.copyParamsAndDeleteEspecialOperations)(req);
        if ((0, query_adaptation_1.requestContainsParams)(req)) {
            /* Control de que todos los parametros de consulta existen en el modelo Meal*/
            if (!(0, query_adaptation_1.queryFieldsExistInModel)(Object.keys(queryParams), meal_model_1.fieldOfMealModel)) {
                throw 'Some query param doesn\'t exist in meal model';
            }
            /* Adaptacion de la consulta para casos en lo que se filtre con "mayor, mayor igual, menor o menor igual" */
            queryStr = JSON.stringify(req.query);
            queryStr = queryStr.replace(/\b(gte|gt|lte|lt|regex)\b/g, match => (match === 'regex') ? `$options":"i","$${match}` : `$${match}`);
        }
        let query = meal_model_1.MealModel.find((queryStr !== undefined) ? (Object.assign(Object.assign({}, JSON.parse(queryStr)), { $options: "i" })) : Object.assign({}, queryParams));
        /* En caso de que la query tenga ordenacion pues pa lante */
        query = (0, query_adaptation_1.processSortByFields)(req, query, meal_model_1.fieldOfMealModel);
        /* Filtracion de campos */
        query = (0, query_adaptation_1.processFilteredFields)(req, query, meal_model_1.fieldOfMealModel);
        const numItems = yield meal_model_1.MealModel.countDocuments();
        query = (0, query_adaptation_1.processRequestPagination)(req, query, numItems);
        const meals = yield query;
        res.status(201).json({
            status: 'success',
            results: meals.length,
            data: {
                meals
            }
        });
    }
    catch (err) {
        res.status(400).json({
            status: 'fail',
            message: err
        });
    }
});
exports.getAllMeals = getAllMeals;
const getMealById = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const mealSearched = yield meal_model_1.MealModel.findById(req.params['id']);
        return res.status(200).json({
            status: 'success',
            data: {
                meal: mealSearched
            }
        });
    }
    catch (err) {
        res.status(400).json({
            status: 'fail',
            message: err
        });
    }
});
exports.getMealById = getMealById;
const addNewMeal = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const newMeal = yield meal_model_1.MealModel.create(Object.assign({}, req.body));
        res.status(201).json({
            status: 'sucsess',
            data: {
                newMeal
            }
        });
    }
    catch (err) {
        res.status(400).json({
            status: 'fail',
            message: err
        });
    }
});
exports.addNewMeal = addNewMeal;
const updateMeal = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const mealEdited = yield meal_model_1.MealModel.findByIdAndUpdate(req.params['id'], req.body, {
            new: true,
            runValidators: true,
        });
        return res.status(201).json(mealEdited);
    }
    catch (err) {
        res.status(400).json({
            status: 'fail',
            message: err
        });
    }
});
exports.updateMeal = updateMeal;
const deleteMeal = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const mealDeleted = yield meal_model_1.MealModel.findByIdAndDelete(req.params['id']);
        return res.status(204).json(mealDeleted);
    }
    catch (err) {
        res.status(400).json({
            status: 'fail',
            message: err
        });
    }
});
exports.deleteMeal = deleteMeal;
const getMealStats = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
    }
    catch (err) {
        res.status(400).json({
            status: 'fail',
            message: err
        });
    }
});
exports.getMealStats = getMealStats;
