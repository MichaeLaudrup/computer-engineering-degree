const mongoose = require('mongoose'); 

const scheduledMealSchema = new mongoose.Schema({
    name: {
        type: String,
        required: [true, 'Una comida programada debe tener un nombre'], 
        maxLength: 25
    },
    aliments: {
        type: [mongoose.Schema.Types.ObjectId],
        ref: 'Meal'
    }
})

const dailyMealsRegisterSchema = new mongoose.Schema({
    date: {
        type: Date,
        default: (new Date()).toISOString()
    },
    scheduledMeals: {
        type: [scheduledMealSchema],
    },

    userId: {
        type: [mongoose.Schema.Types.ObjectId],
        ref:'User'
    }
},{
    toJSON: {
        virtuals:true
    }
});

export const dailyMealsRegisterModel = mongoose.model('DailyMealsRegister', dailyMealsRegisterSchema, 'DailyMealsRegister')