import mongoose from "mongoose";


/* Aqui van los campos por lo que se puede filtrar una busqueda */
export const fieldOfMealModel = ['name', 'weight', 'kcal', 'proteins', 'carboHydrates', 'fats', 'tags']; 

const mealSchema: mongoose.Schema = new mongoose.Schema({
    name: {
      type: String,
      required:[true, 'A meal must have a name'],
      unique:true,
      dropDups:true,
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
    carboWithSugars: {
      type: Number,
    },
    fats: {
      type: Number,
      require: [true, 'A meal must have fats quantitie']
    },
    saturatedFats: {
      type: Number,
    },

    fiber: {
      type: Number,
    },
    salt: {
      type: Number
    },
    tags: {
      type: [String],
      enum: {
        values: [
          'CARNE',
          'VERDURA',
          'GLUTEN',
          'VEGANO',
          'LACTEO',
          'CEREALES',
          'FRUTA',
          'FRUTOS_SECOS',
          'LEGUMBRES',
          'VEGETALES',
          'MARISCO',
          'PESCADO',
          'ORIGEN_ANIMAL',
          'SEMILLAS',
          'HUEVO',

        ],
        message: '{VALUE} is a Tag not supported'
      }
    },
    recomendedMeals: {
      type: [String],
      enum : {
        values: ['DESAYUNO','MEDIO_DIA', 'ALMUERZO', 'MERIENDA', 'PRE_ENTRENO', 'POST_ENTRENO','CENA', 'APERITIVOS','GENERICO']
      }
    },
    srcImg: String,
    createAt: {
      type:Date,
      default: Date.now(),
      select: false
    }
});

export const MealModel = mongoose.model('Meal', mealSchema, 'meals')

