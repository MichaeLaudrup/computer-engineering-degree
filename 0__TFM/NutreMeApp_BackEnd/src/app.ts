import express, { Application, NextFunction, Request, Response} from 'express'; 
import { errorMiddleware } from './middleware/error.middleware';

/* ROUTERS IMPORTS */
import mealsRouter from './routes/meals.routes'; 
import userRouter from './routes/users.routes'; 
import userDataRouter from './routes/user-data.routes'; 
import dailyMealsRegisterRouter from './routes/daily-meals-register.routes'; 
import sectionMealRouter from './routes/section-meals.routes'
import recomendationsEngineRouter from './routes/recomendations-engine.routes'; 
/* END-ROUTERS */
import { OperationalError } from './shared/classes/error.interface';
require('./utils/error-handlers'); 
const cors = require('cors'); 
const dotenv = require('dotenv'); 
dotenv.config({path: `${__dirname}/../config.env`});
require('./database');



const app : Application = express();

const whiteList = [
    'http://localhost:4200',
]; 
app.use(cors({
    origin: whiteList
})); 


const path = require('path').join(__dirname, 'public')
app.use('/static', express.static(path))

const port = process.env.SERVER_PORT_NUMBER || 5000; 

app.use(express.json()); 
const server = app.listen(port, () => { 
    console.log('listening at port 3000 on ' + (process.env.NODE_ENV) + ' mode');  
}); 

const baseRoute = process.env.BASE_API_ROUTE_ACCESS ||'/api/v1'; 


app.use(`${baseRoute}/meals`, mealsRouter ); 
app.use(`${baseRoute}/users`, userRouter);
app.use(`${baseRoute}/user-data`, userDataRouter); 
app.use(`${baseRoute}/daily-meals-registers`, dailyMealsRegisterRouter); 
app.use(`${baseRoute}/section-meals`, sectionMealRouter); 
app.use(`${baseRoute}/recommendations-engine`, recomendationsEngineRouter)
app.all('*', (req: Request, res: Response, next: NextFunction) => {
    next(new OperationalError( `Can't find the url ${req.originalUrl} on this server!`, 404 )); 
})

app.use( errorMiddleware); 

import csv from 'csv-parser';
import fs, { writeFile } from 'fs';
import { MealModel } from './models/meal.model'; 

let arrayNames: any [] = [];

let arrayMeals: any[] = []; 


const readStream = fs.createReadStream(`${__dirname}/../data_source/food_csv.csv`);

/* readStream.pipe(csv()).on('data',async (data) => {
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
}); */