import { NextFunction, Request, Response } from "express";
import { UserModel } from "../models/user.model";
import { OperationalError } from "../shared/classes/error.interface";


export const getAllUsers = async(req: Request, res:Response, next: NextFunction) => {
    try{
        const users = await UserModel.find(); 
        res.status(200).json({
            status: 'success',
            data: users
        })
    }catch (err) {
        return next(err); 
    }
}