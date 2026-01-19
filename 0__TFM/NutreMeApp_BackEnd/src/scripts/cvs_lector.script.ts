/* 

import csv from 'csv-parser'; 
import fs, { writeFile } from 'fs'; 
import { MealModel } from './models/meal.model';

let arrayNames: any [] = []; 
const readStream = fs.createReadStream(`${__dirname}/../data_source/food-composition.csv`);
readStream.pipe(csv()).on('data',async (data) => {
    let tags: String[] = []; 
    for(let propertieName in data ){
        if((propertieName !== 'Nombre') && 
            (propertieName !== 'Calorias') && 
            (propertieName !== 'Carbohidratos') &&
            (propertieName !== 'Proteinas') &&
            (propertieName !== 'Grasas')){
            if(data[propertieName] === 'true'){
                tags.push(propertieName.toUpperCase())
            }
        }
    }

    arrayNames.push({
        x: data.x,
        name: data.Nombre.replace(/'/g, ''),
        description: '',
        weight: 100,
        kcal: +data.Calorias,
        proteins: +data.Proteinas,
        carboHydrates: +data.Carbohidratos,
        fats: +data.Grasas,
        srcImg: 'default-image',
        tags
    }); 
}).on('end', () => {
    fs.writeFile(`${__dirname}/../data_source/output`, arrayNames.join('\n'), (err)=> {
        console.log(err); 
        arrayNames.forEach( meal => {
            MealModel.create({
                ...meal
            }).catch(err => {

            });
        })
    })
});  */