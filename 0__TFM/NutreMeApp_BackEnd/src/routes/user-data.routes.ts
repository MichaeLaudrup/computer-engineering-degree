import express from 'express'; 
import accessByLogginProtection from '../middleware/protection-by-is-loged.middleware'; 
import * as UserDataController from '../controllers/user-data.controller'; 
const router = express.Router(); 


router.route('/').get(accessByLogginProtection, UserDataController.getUserData).post(accessByLogginProtection, UserDataController.newUserData)
router.route('/daily-macros').get( accessByLogginProtection, UserDataController.getDailyMacronutriens); 
export default router; 