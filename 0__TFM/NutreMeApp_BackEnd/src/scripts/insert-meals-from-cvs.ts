import csv from 'csv-parser';
import fs, { writeFile } from 'fs';
import { MealModel } from '../models/meal.model';
let arrayNames: any [] = [];
let arrayMeals: any[] = []; 

const readStream = fs.createReadStream(`${__dirname}/../data_source/food_csv.csv`);
 readStream.pipe(csv()).on('data',async (data) => {
    const tags = ['CARNE','VERDURA','GLUTEN','VEGANO','LACTEO','CEREALES','FRUTA','FRUTOS_SECOS','LEGUMBRES','VEGETALES','MARISCO','PESCADO','ORIGEN_ANIMAL','SEMILLAS','HUEVO']
    const meals = ['DESAYUNO','MEDIO_DIA', 'ALMUERZO', 'MERIENDA', 'PRE_ENTRENO', 'POST_ENTRENO','CENA', 'APERITIVOS','GENERICO']
    const vectorTags: string[] = []; 
    const vectorMeals : string [] = [];
    let newMeal: any = {};  
    for(let propertieName in data ){  
        if(tags.includes(propertieName)){
            if(data[propertieName] === 'true'){
                vectorTags.push(propertieName); 
            }
        }else if(meals.includes(propertieName)){
            if(data[propertieName] === 'true'){
                vectorMeals.push(propertieName); 
            }
        }else{
            if(propertieName.search('mealName') !== -1) {
                newMeal['name'] = data[propertieName]
            }else{
                newMeal[propertieName.replace(/'/g, '')] = data[propertieName]; 
            }
        }   
    }
    newMeal['tags'] = vectorTags; 
    newMeal['recomendedMeals'] = vectorMeals
    arrayMeals.push(newMeal)
}).on('end', () => {
    fs.writeFile(`${__dirname}/../data_source/output`, arrayNames.join('\n'), (err)=> {
        console.log(err);
        arrayMeals.forEach( meal => {
            MealModel.create({
                ...meal,
            }).catch(err => {
                console.log('ERR IN', meal.name, meal.x);
                console.error(err)
            });
        })
    })
}); 