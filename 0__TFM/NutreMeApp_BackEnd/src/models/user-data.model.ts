import mongoose from "mongoose"; 
import * as NutritionCalculatorService from "../services/nutri-calculator.service";

const schemaOptions = {
    toJSON: {
        virtuals:true
    }
}

const UserDataSchema = new mongoose.Schema({
    userProfile: {
        type: mongoose.Schema.Types.ObjectId,
        ref:'User',
        require: [true, 'Los datos de usuario deben pertenecer a algun usuario creado previamente'],
        unique: true
    },
    nutritionalTarget: {
        type: String,
        enum: ['SUBIR_PESO', 'BAJAR_PESO', 'MANTENER_PESO', 'DEFINIR', 'GANAR_MASA_MUSCULAR'],
        message: `{VALUE} Objetivo nutricional no soportado`,
        required: true
    },
    age:{
        type:Number,
        minimun: 18,
        maximum: 80, // Edad en la que se considera necesidades nutricionales que una tecnologia generica no puede tratar
        required: [true, 'Debe proporcionar una edad']
    },
    weight: {
        type:Number,
        required: [true, 'Debe proporcionar un peso']
    },
    height: {
        type: Number,
        required: [true, 'Debe proporcionar una altura']
    },
    feedingType: {
        type: String,
        enum: {
            values: ['OMNIVORO', 'VEGANO', 'VEGETARIANO']
        }, 
    },
    gender: {
        type: String,
        enum: {
            values: ['Hombre', 'Mujer'],
            message: '${VALUE} no soportado'
        },
        required: true
    },
    forbiddenAliments: {
        type: [mongoose.Schema.Types.ObjectId],
        ref: 'Meals'
    },
    activityIntesity: {
        type: Number,
        default: 1 // Si no se provee actividad fisica se pone a uno para que no tenga efecto en los calculos matemáticos
    },
    allergens: {
        type:[String],
        enum: {
            values: ['HUEVO','FRUTOS_SECOS','GLUTEN','SOJA', 'LACTOSA', 'MARISCO', 'FRUCTOSA', 'PESCADO' ],
            message: ''
        } 
    },
    photo: String
}, schemaOptions); 

/* PROPIEDADES VIRTUALES (SOLO SE CALCULAN AL OBTENER UN USUARIO DATA) */
UserDataSchema.virtual('mba').get( function() {
    return NutritionCalculatorService.calcMBA(this.height, this.weight, this.age, this.gender )
});

UserDataSchema.virtual('imc').get( function() {
    return NutritionCalculatorService.calcIMC(this.height, this.weight); 
})

UserDataSchema.virtual('diaryWater').get( function() {
    return NutritionCalculatorService.calcDiaryWater(this.weight); 
});

UserDataSchema.virtual('mbaWithObjetive').get( function(this:any){
    return NutritionCalculatorService.calcMBAWithObjective(this['mba'], this['nutritionalTarget']); 
})


UserDataSchema.virtual('mbaWithActivityAndObjetive').get( function(this:any) {
    return this['mbaWithObjetive'] * this.activityIntesity; ; 
});

UserDataSchema.virtual('rulesEngineLastResult', function() {
    return {noMeat: true}; 
})

UserDataSchema.virtual('macrosInRepose').get(function(this:any){
    return NutritionCalculatorService.calcMacroNutriensDistribution( this['mba'] ,this['nutritionalTarget'])

})

UserDataSchema.virtual('macrosWithActivity').get(function(this:any){
    return NutritionCalculatorService.calcMacroNutriensDistribution( this['mbaWithActivityAndObjetive'] ,this['nutritionalTarget'])
})

export const UserDataModel = mongoose.model('UserData', UserDataSchema, 'usersData')

