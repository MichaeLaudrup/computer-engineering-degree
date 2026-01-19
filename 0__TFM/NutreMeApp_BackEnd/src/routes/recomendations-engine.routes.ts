import express from 'express'; 
import accessByLogginProtection from '../middleware/protection-by-is-loged.middleware'; 
import * as RecommendationsEngineController from '../controllers/recomendations-engine.controller'; 


const router = express.Router(); 

router.route('/')
    .get(accessByLogginProtection, accessByLogginProtection,RecommendationsEngineController.runEngine); 
export default router; 