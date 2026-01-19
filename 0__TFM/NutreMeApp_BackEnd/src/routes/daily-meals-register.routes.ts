import express from 'express'; 
import * as DailyMealsRegister from './../controllers/daily-meals-register.controller'; 
import accessRouteProtector from '../middleware/protection-by-is-loged.middleware'; 

const router = express.Router(); 

router.route('/')
    .post(accessRouteProtector, DailyMealsRegister.addNewDailyMealsRegister)
    .get(accessRouteProtector, DailyMealsRegister.getAllMyDailyMealsRegister); 
export default router; 