import { Pipe, PipeTransform } from "@angular/core";
import { Allergens } from "../enums/allergens.enum";


const getSvgIconName = ( mealTagType:Allergens) => {
    switch(mealTagType){
        case Allergens.Egg: 
            return 'egg';
        case Allergens.Fish: 
            return 'fish'; 
        case Allergens.Fructose: 
            return 'fructose'; 
        case Allergens.Gluten: 
            return 'gluten'; 
        case Allergens.Lactose: 
            return 'lactose'; 
        case Allergens.Nuts: 
            return 'nuts';
        case Allergens.SeaFood: 
            return 'seafood'; 
        case Allergens.Soy: 
            return 'soy'; 
        default: 
            return 'generic_icon';  
    }
}; 

@Pipe({
    name: 'fromAlergenToSvgIcon'
})
export class toSvgAlergenIcon implements PipeTransform {
    transform(tag: Allergens) {
        return getSvgIconName(tag); 
    }
}