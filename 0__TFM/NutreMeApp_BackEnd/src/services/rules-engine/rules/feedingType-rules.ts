const isVegetarian = {
    conditions: {
        any: [{
                fact:'feedingType',
                operator: 'equal',
                value: 'VEGETARIANO'
            },
        ]   
    },
    event: {
        type: 'not_meat',
        params: {
            filters: [
                {tags:{$ne: 'CARNE'}},
                {tags:{$ne: 'PESCADO'}},
                {tags:{$ne: 'MARISCO'}}
            ]
        }
    },
    priority: 10
}

const isVegan = {
    conditions: {
        any: [{
                fact:'feedingType',
                operator: 'equal',
                value: 'VEGANO'
            },
        ]   
    },
    event: {
        type: 'not_lactose',
        params: {
            filters: [
                {tags:{$ne: 'LACTOSA'}},
                {tags:{$ne: 'ORIGEN_ANIMAL'}}
            ]
        }
    },
    priority: 9
}

export const rules  = [
    isVegan,
    isVegetarian
]

export default rules; 


