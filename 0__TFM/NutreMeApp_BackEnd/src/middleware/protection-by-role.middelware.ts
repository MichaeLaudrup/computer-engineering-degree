import { NextFunction, Request, Response } from "express"
import { OperationalError } from "../shared/classes/error.interface";


export const routerProtectionByRole = (...roles: string[]) => {
    return (req: Request, res: Response, next: NextFunction) => {
        if(!roles.includes(req.body.user.role)){
            return next(new OperationalError('No tienes permisos para hacer está acción',403)); 
        }
        next(); 
    }
}
