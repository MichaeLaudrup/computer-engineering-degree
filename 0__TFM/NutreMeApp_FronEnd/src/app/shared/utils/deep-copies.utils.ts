import { Aliment, SectionMeal } from "@shared/models";

export class deepCopiesUtils {

    static copyMealsWithOneDelete(meals: Aliment[], idToDelete:string){
        const newMeals : Aliment[] = meals.filter(meal =>  meal._id !== idToDelete).map((meal) => {
            return ({
                ...meal,
            });
        });
        return newMeals;  
    }

    static copySectionsWithOneMealDeleted(sections: SectionMeal[], idSection: string, idMeal: string): SectionMeal[]{
        return sections.map(section => ({
            ...section,
            _id: section._id, 
            meals: (idSection !== section._id) ? [...section.meals] : [...section.meals.filter(meal => meal._id !== idMeal)]
        }))
    }

    static copySectionsWithOneMealEdited(sections: SectionMeal[], sectionId: string, mealUpdated: Aliment): SectionMeal[] {
        const x = sections.map(section => { 
            return {
                ...section,
                id: section._id,
                meals: (section._id !== sectionId) ? [...section.meals] : [...section.meals.map((meal) => {
                    return (meal._id === mealUpdated._id) ? {...mealUpdated} : meal })], 

            }
        })
        return x; 
    }
}