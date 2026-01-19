const isAllergicToLactose = {
    conditions: {
        any: [{
                fact:'allergens',
                operator: 'contains',
                value: 'LACTOSA'
            },
        ]   
    },
    event: {
        type: 'not_lactose',
        params: {
            filters: [
                {tags:{$ne: 'LACTEO'}},
            ]
        }
    },
}

const isAllergicToEgg = {
    conditions: {
        any: [{
                fact:'allergens',
                operator: 'contains',
                value: 'HUEVO'
            },
        ]   
    },
    event: {
        type: 'not_egg',
        params: {
            filters: [
                {tags:{$ne: 'HUEVO'}},
            ]
        }
    },
}


const isAllergicToFish = {
    conditions: {
        any: [{
                fact:'allergens',
                operator: 'contains',
                value: 'PESCADO'
            },
        ]   
    },
    event: {
        type: 'not_fish',
        params: {
            filters: [
                {tags:{$ne: 'PESCADO'}},
            ]
        }
    },
}

const isAllergicToFructose = {
    conditions: {
        any: [{
                fact:'allergens',
                operator: 'contains',
                value: 'FRUCTOSA'
            },
        ]   
    },
    event: {
        type: 'not_fructose',
        params: {
            filters: [
                {tags:{$ne: 'FRUCTOSA'}},
            ]
        }
    },
}

const isAllergicToGluten = {
    conditions: {
        any: [{
                fact:'allergens',
                operator: 'contains',
                value: 'GLUTEN'
            },
        ]   
    },
    event: {
        type: 'not_gluten',
        params: {
            filters: [
                {tags:{$ne: 'GLUTEN'}}, 
            ],
            mostPreferency: [
                {tags: {$eq: 'GLUTEN-FREE'}}
            ]
        }
    },
}

const isAllergicToNuts = {
    conditions: {
        any: [{
                fact:'allergens',
                operator: 'contains',
                value: 'FRUTOS_SECOS'
            },
        ]   
    },
    event: {
        type: 'not_nuts',
        params: {
            filters: [
                {tags:{$ne: 'FRUTOS_SECOS'}},
            ]
        }
    },
}

const isAllergicToSeaFood = {
    conditions: {
        any: [{
                fact:'allergens',
                operator: 'contains',
                value: 'MARISCO'
            },
        ]   
    },
    event: {
        type: 'not_seafood',
        params: {
            filters: [
                {tags:{$ne: 'MARISCO'}},
            ]
        }
    },
}

const isAllergicToSeaSoya = {
    conditions: {
        any: [{
                fact:'allergens',
                operator: 'contains',
                value: 'SOJA'
            },
        ]   
    },
    event: {
        type: 'not_soya',
        params: {
            filters: [
                {tags:{$ne: 'SOJA'}},
            ]
        }
    },
}


export const rules  = [
    isAllergicToLactose,
    isAllergicToEgg,
    isAllergicToFish,
    isAllergicToFructose,
    isAllergicToGluten,
    isAllergicToNuts,
    isAllergicToSeaFood,
    isAllergicToSeaSoya,
]


export default rules; 