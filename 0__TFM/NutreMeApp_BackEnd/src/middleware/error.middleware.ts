import { NextFunction, Response, Request } from "express";
import { OperationalError } from "../shared/classes/error.interface";

export const errorMiddleware = ( err: any, req: Request, res: Response, next: NextFunction) => {
    if(process.env.NODE_ENV === 'dev'){
        res.status(err.statusCode || 500).json({
            status:err.status,
            message: err.message,
            error: err,
            stack: err.stack
        })
    }else if(process.env.NODE_ENV === 'production'){
        let finalError = err; 
        if(err['name'] && err['name'] === 'CastError'){
           finalError = handleErrorCastMongoDB(err); 
        }else if(err['code'] && err['code'] === 11000){
           finalError = handleErrorDuplicateUniqueIdentifier(err); 
        }

        if(finalError.isOperational){
            res.status(finalError.statusCode).json({
                status:finalError.status,
                message: finalError.message,
            })
        }else{
            res.status(500).json({
                status: 'error',
                message: 'Something was wrong on the server side! Call to technical team!'
            })
        }
    }
    next(); 
}

const handleErrorCastMongoDB = (err:any) : OperationalError=> {
    const message = `Invalid ${err.path} : ${err.value}`
    return new OperationalError(message, 400); 
}

const handleErrorDuplicateUniqueIdentifier = (err: any): OperationalError => {
    const message = `Duplicate value in an unique field ${err.errmsg.match(/(["'])(?:(?=(\\?))\2.)*?\1/)[0]}`; 
    return new OperationalError(message, 400); 
}