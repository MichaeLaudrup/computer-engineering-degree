import express from 'express'; 
import * as MealsController from './../controllers/meal.controller'; 
import accessRouteProtector from '../middleware/protection-by-is-loged.middleware'; 
import { routerProtectionByRole } from '../middleware/protection-by-role.middelware';

const router = express.Router(); 
router.route('/').get(accessRouteProtector, MealsController.getAllMeals).post( MealsController.addNewMeal);
router.route('/:id')
    .get(MealsController.getMealById)
    .patch(MealsController.updateMeal)
    .delete(accessRouteProtector, routerProtectionByRole('superadmin', 'admin') ,MealsController.deleteMeal)
export default router; 