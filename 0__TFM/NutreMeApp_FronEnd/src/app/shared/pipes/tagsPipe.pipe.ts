import { Pipe, PipeTransform } from "@angular/core";
import { mealTag } from "../enums/tag.enum";


const getSvgIconName = ( mealTagType:mealTag) => {
    switch(mealTagType){
        case mealTag.Vegetarian: 
            return 'vegan'; 
        case mealTag.Fruit: 
            return 'fruit';
        case mealTag.Lactose: 
            return 'lactose';
        case mealTag.GlutenFree: 
            return 'gluten_free'; 
        case mealTag.Meat: 
            return 'meat2'; 
        case mealTag.AnimalOrigin:
            return 'animal_origin';
        case mealTag.Fish: 
            return 'fish'  
        case mealTag.Nuts: 
            return 'nuts'
        case mealTag.Vegan: 
            return 'vegan2';
        case mealTag.Beans:
            return 'beans'; 
        case mealTag.Cereal:
            return 'cereal';
        case mealTag.Gluten: 
            return 'gluten'
        case mealTag.Vegetable: 
            return 'vegetables'
        default: 
            return 'generic_icon';  
    }
}; 


@Pipe({
    name: 'fromTagToSvgIcon'
})
export class toTagSvgIconPipe implements PipeTransform {
    transform(tag: mealTag) {
        return getSvgIconName(tag); 
    }
}

@Pipe({
    name: 'fromTagToText'
})
export class fromTagToText implements PipeTransform {
    transform(tag: mealTag) {
        switch(tag){
            case mealTag.Vegetarian: 
                return 'Vegano'; 
            case mealTag.Fruit: 
                return 'fruit';
            case mealTag.Lactose: 
                return 'Laceo';
            case mealTag.GlutenFree: 
                return 'gluten_free'; 
            case mealTag.Meat: 
                return 'Carne'; 
            case mealTag.AnimalOrigin:
                return 'Origen animal';
            case mealTag.Fish: 
                return 'Pescado'; 
            case mealTag.Nuts: 
                return 'Frutos secos'
            case mealTag.Vegan:
                return 'Vegano'
            case mealTag.Beans:
                return 'Legumbres';
            case mealTag.Cereal: 
                return 'Cereales';
            case mealTag.Gluten: 
                return 'Gluten';  
            case mealTag.Vegetable: 
                return 'Vegetales'
            default: 
                return tag;  
        } 
    }
}