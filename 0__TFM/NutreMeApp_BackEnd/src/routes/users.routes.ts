import express from 'express'; 
import * as AuthController from './../controllers/auth.controller';
import accessRouteProtector from '../middleware/protection-by-is-loged.middleware'; 
import * as UsersController from '../controllers/user.controller'; 
import { routerProtectionByRole } from '../middleware/protection-by-role.middelware';
const router = express.Router(); 

router.post('/signup', AuthController.singup); 
router.post('/login', AuthController.logIn); 
router.post('/forgotPassword', AuthController.forgotPassword); 
router.post('/resetPassword', AuthController.resetPassword); 
router.get('/get-user', accessRouteProtector, AuthController.getUser)


router.get('/', accessRouteProtector, routerProtectionByRole('superadmin', 'admin'), UsersController.getAllUsers);
export default router; 