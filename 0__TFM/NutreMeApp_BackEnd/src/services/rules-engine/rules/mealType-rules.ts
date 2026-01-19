const recommendedMealBreakfast = {
    conditions: {
        any: [{
                fact:'recommendedMeal',
                operator: 'equal',
                value: 'DESAYUNO'
            },
        ]   
    },
    event: {
        type:"mealTypeFiltering",
        params: {
            filters: [
                {recomendedMeals:{$eq: 'DESAYUNO'}}
            ]
        }
    },
}

const recommendedMidday = {
    conditions: {
        any: [{
                fact:'recommendedMeal',
                operator: 'equal',
                value: 'MEDIODIA'
            },
        ]   
    },
    event: {
        type:"mealTypeFiltering",
        params: {
            filters: [
                {recomendedMeals:{$eq: 'MEDIODIA'}}
            ]
        }
    },
}

const recommendedMealLunch = {
    conditions: {
        any: [{
                fact:'recommendedMeal',
                operator: 'equal',
                value: 'ALMUERZO'
            },
        ]   
    },
    event: {
        type:"mealTypeFiltering",
        params: {
            filters: [
                {recomendedMeals:{$eq: 'ALMUERZO'}}
            ]
        }
    },
}


const recommendedAfternoonSnack = {
    conditions: {
        any: [{
                fact:'recommendedMeal',
                operator: 'equal',
                value: 'MERIENDA'
            },
        ]   
    },
    event: {
        type:"mealTypeFiltering",
        params: {
            filters: [
                {recomendedMeals:{$eq: 'MERIENDA'}}
            ]
        }
    },
}

export const recommendedDinner = {
    conditions: {
        any: [{
                fact:'recommendedMeal',
                operator: 'equal',
                value: 'CENA'
            },
        ]   
    },
    event: {
        type:"mealTypeFiltering",
        params: {
            filters: [
                {recomendedMeals:{$eq: 'CENA'}}
            ]
        }
    },
}

export const recommendedPreTraining = {
    conditions: {
        any: [{
                fact:'recommendedMeal',
                operator: 'equal',
                value: 'PRE_ENTRENO'
            },
        ]   
    },
    event: {
        type:"mealTypeFiltering",
        params: {
            filters: [
                {recomendedMeals:{$eq: 'PRE_ENTRENO'}}
            ]
        }
    },
}

export const recommendedPostTraining = {
    conditions: {
        any: [{
                fact:'recommendedMeal',
                operator: 'equal',
                value: 'POST_ENTRENO'
            },
        ]   
    },
    event: {
        type:"mealTypeFiltering",
        params: {
            filters: [
                {recomendedMeals:{$eq: 'POST_ENTRENO'}}
            ]
        }
    },
}

const recommendedAppetizers = {
    conditions: {
        any: [{
                fact:'recommendedMeal',
                operator: 'equal',
                value: 'APERITIVOS'
            },
        ]   
    },
    event: {
        type:"mealTypeFiltering",
        params: {
            filters: [
                {recomendedMeals:{$eq: 'APERITIVOS'}}
            ]
        }
    },
}

export const recommendedGeneric = {
    conditions: {
        any: [{
                fact:'recommendedMeal',
                operator: 'equal',
                value: 'GENERICO'
            },
        ]   
    },
    event: {
        type:"mealTypeFiltering",
        params: {
            filters: [
                {recomendedMeals:{$eq: 'GENERICO'}}
            ]
        }
    },
}

export const rules  = [
    recommendedMealLunch,
    recommendedMealBreakfast,
    recommendedMidday,
    recommendedAfternoonSnack,
    recommendedDinner,
    recommendedPreTraining,
    recommendedPostTraining,
    recommendedAppetizers,
    recommendedGeneric
]

export default rules; 