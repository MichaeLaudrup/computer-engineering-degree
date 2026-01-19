import { NextFunction, Request, Response } from "express";
import { UserModel } from "../models/user.model";
import { OperationalError } from "../shared/classes/error.interface";
const jwt = require('jsonwebtoken'); 

const signToken = (userId:any) => {
    return jwt.sign({id:userId}, process.env.JWT_SECRET, {
        expiresIn: process.env.JWT_EXPIRES_IN
}); }


export const singup = async (req: Request, res: Response, next: NextFunction) => {
    try{
        const newUser = await  UserModel.create({
            name: req.body.name,
            email: req.body.email,
            photo: req.body.photo,
            password: req.body.password,
            passwordConfirm: req.body.passwordConfirm,
            passwordChangedAt: req.body.passwordChangedAt,
            role: req.body.role
        }); 
        
        const token = signToken(newUser._id)
        res.status(201).json({
            status: 'success',
            data: {
                user: {
                    name: newUser.name,
                    email: newUser.email,
                    id: newUser.id
                },
                token
            }
        }); 
    }catch(err){
        next(err); 
    }
}

export const logIn = async(req: Request, res:Response, next: NextFunction) => {
    try{
               // 1) Check if email and password exist in request
       if(!req.body.email || !req.body.password){
            throw(new OperationalError('Bad request! Email or password not provided!!', 400)); 
       }else{
        const {email, password} = req.body;
        //2) check if email and password exist in Data base
        const user: any = await UserModel.findOne({email}).select('+password');
        const correct = await user?.correctPassword(password, user.password); 
        if(!user || !correct){
            throw new OperationalError('Email o contraseñas incorrectos', 401); 
        }
        const token = signToken(user._id); 
        res.status(200).json({
            status: 'success',
            data: {
                user: {
                    name:user.name,
                    email: user.email,
                    id: user._id
                },
                token
            }
        })
       }  

    }catch (err) {
        next(err); 
    }
}

export const getUser = async(req: Request, res:Response, next: NextFunction) => {
    try{
        res.status(201).json({
            status: 'success',
            data: {
                user: {
                    id: req.body.user.id,
                    name: req.body.user.name,
                    email: req.body.user.email
                }
            }
        })
    }catch (err) {
        next(err)
    }
}

export const forgotPassword = async(req: Request, res:Response, next: NextFunction) => {
    try{
        const user : any = await UserModel.findOne({ email: req.body.email})
        if(!user){
            return next(new OperationalError('User not found', 404))
        }else{
            const resetToken = user.createPasswordResetToken(); 
            await user.save({ validateBeforeSave: false}); 
            res.status(201).json({
                status:'success'
            }) 
        }
    }catch (err) {
        next(err); 
    }
}

export const resetPassword = async(req: Request, res:Response, next: NextFunction) => {
    try{
    }catch (err) {
        next(err); 
    }
}