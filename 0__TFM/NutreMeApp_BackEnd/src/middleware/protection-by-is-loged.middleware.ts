import { NextFunction, Request, Response } from "express";
import { OperationalError } from "../shared/classes/error.interface";
import { promisify } from "util";
import { UserModel } from "../models/user.model";
const jwt = require('jsonwebtoken'); 
export const accessRouteProtector = async (req: Request, res: Response, next: NextFunction) => {
    try{ 
        if(req.headers.authorization && req.headers.authorization.startsWith('Bearer')){
            const token = req.headers.authorization.split(' ')[1]; 
            const decoded = await promisify(jwt.verify)(token, process.env.JWT_SECRET); 
            const user: any = await UserModel.findById(decoded.id).select('+role'); 
            if(user){
                if(user.passwordChangedAt && user.changedPasswordAfter(decoded.iat)){
                    throw new OperationalError('La contraseña ha sido cambiada después de la generación de este token, vuelva a iniciar sesión.')
                }else{
                    req.body.user = user; 
                    next(); 
                }
            }else{
                throw new OperationalError('No user finded to this token')

            }
        }else{
            throw new OperationalError('Error en la obtención del token', 401); 
        }
    }catch(err){
        next(err)
    }
} 

export default accessRouteProtector; 