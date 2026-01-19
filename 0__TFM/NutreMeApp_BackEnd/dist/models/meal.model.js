"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.TagModel = exports.MealModel = exports.mealSchema = exports.fieldOfMealModel = void 0;
const mongoose_1 = __importDefault(require("mongoose"));
/* Aqui van los campos por lo que se puede filtrar una busqueda */
exports.fieldOfMealModel = ['name', 'weight', 'kcal', 'proteins', 'carboHydrates', 'fats', 'tags'];
const TagSchema = new mongoose_1.default.Schema({
    tag: {
        type: String,
        enum: {
            values: ['VEGANO', 'CARNE', 'CEREAL'],
            message: 'Must be '
        }
    }
});
exports.mealSchema = new mongoose_1.default.Schema({
    name: {
        type: String,
        required: [true, 'A meal must have a name'],
        unique: true,
        trim: true,
    },
    description: {
        type: String,
        default: 'No hay ninguna descripción especificada para este alimento. ',
        trim: true
    },
    weight: {
        type: Number,
        default: 100,
    },
    kcal: {
        type: Number,
        require: [true, 'A meal must have kcal quantitie']
    },
    proteins: {
        type: Number,
        require: [true, 'A meal must have proteins quantitie']
    },
    carboHydrates: {
        type: Number,
        require: [true, 'A meal must have carbohydrates quantitie']
    },
    fats: {
        type: Number,
        require: [true, 'A meal must have fats quantitie']
    },
    tags: {
        type: [String],
        enum: {
            values: ['CARNE', 'VERDURA'],
            message: '{VALUE} is a Tag not supported'
        }
    },
    srcImg: String,
    createAt: {
        type: Date,
        default: Date.now(),
        select: false
    }
});
exports.MealModel = mongoose_1.default.model('Meal', exports.mealSchema, 'meals');
exports.TagModel = mongoose_1.default.model('Tag', TagSchema, 'Tags');
