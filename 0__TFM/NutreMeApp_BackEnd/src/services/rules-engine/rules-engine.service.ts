const {Engine} = require('json-rules-engine'); 
import FeedingTypeRules from "./rules/feedingType-rules";
import AllergensRules from './rules/allergens-rules'; 
import MealTypeRules from './rules/mealType-rules'; 
export class RulesEngine {
    engine = new Engine(); 
    constructor(){ 
        FeedingTypeRules.forEach( feedingTyperule => {
            this.engine.addRule(feedingTyperule); 
        });

        AllergensRules.forEach( allergenRule => {
            this.engine.addRule(allergenRule)
        });
        MealTypeRules.forEach( mealTypeRule => {
            this.engine.addRule(mealTypeRule); 
        })  
    }

    async probeFact( fact: any): Promise<any>{
        const {events} =await this.engine.run(fact);
        let complexQuery: {$and: any[]} = { $and: []}
        events.forEach( (event: any) => {
            event.params.filters.forEach( (filter: any) => {
                complexQuery.$and.push(filter)
            })
        }); 
        if(complexQuery.$and.length > 0){
            return complexQuery; 
        }else{
            return {}; 
        }
    }    
}

export default RulesEngine; 