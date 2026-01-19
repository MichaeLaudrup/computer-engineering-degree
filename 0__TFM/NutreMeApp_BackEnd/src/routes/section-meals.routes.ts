import express from 'express'; 
import accessByLogginProtection from '../middleware/protection-by-is-loged.middleware'; 
import * as SectionMealController from '../controllers/section-meals.controller'; 
import { uploadUserPhotoMiddleware } from '../middleware/upload-image.middleware';

const router = express.Router(); 

router.route('/')
    .get(accessByLogginProtection, SectionMealController.getMySectionMeals)
    .post( accessByLogginProtection, SectionMealController.addSectionMeals); 

router.route('/:id')
    .post( accessByLogginProtection,uploadUserPhotoMiddleware, SectionMealController.attachPhotoToSection)
    .delete(accessByLogginProtection, SectionMealController.deleteSectionMeal)
    .patch(accessByLogginProtection, SectionMealController.updateSecionMeal)

router.route('/:id/add-aliments')
    .post( accessByLogginProtection, SectionMealController.addFoodToSection)

router.route('/:id/delete-aliments')
    .post( accessByLogginProtection, SectionMealController.deleteFoodInSection)

export default router; 