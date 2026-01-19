import mongoose from "mongoose";
const crypto = require('crypto'); 
const bcrypt = require('bcryptjs'); 
const validator = require('validator'); 

export const publicFieldsUser = []; //Aquellos campos que se puede acceder desde fuera para filtrar, consultar, ordenar...etc.

export const UserSchema = new mongoose.Schema({
    name: {
        type:String,
        require: [true, 'Please, inserta a name']      
    },
    email: {
        type:String,
        require:[true, 'Insert an email'],
        unique: true, 
        validate: [validator.isEmail,'Please! Fill a correct email'],
        lowecase: true
    },
    photo: {
        type:String
    },
    password: {
        type:String,
        require:true,
        minlength: 8,
        select: false

    },
    passwordConfirm: {
        type:String, 
        required: [true, 'Not putted'],
        select:false, 
        //This only works on CREATE or SAVE!!
        validate: [function(this: any, el:string) {
            return el === (<string>this.password) 
        },'Try to put both passwords fine']
    },
    role: {
        type: String,
        enum: {
            values: ['admin', 'superadmin', 'user'],
            validate: ['A role must be admin, superadmin or user']
        },
        default: 'user',
        select: false
    },
    passwordChangedAt: {
        type: Date
    },
    passwordResetToken: String,
    passwordResetExpired: String
}); 

UserSchema.pre('save', async function(this:any, next){
    if(!this.isModified('password')) return next(); 
    this.password = await bcrypt.hash(this.password, 12); 
    this.passwordConfirm = undefined; 
});

UserSchema.methods.correctPassword = async function(candidatePassword: string, userPassword: string) {
    return await bcrypt.compare(candidatePassword, userPassword); 
}

UserSchema.methods.changedPasswordAfter = function(JWTTimestamp: any) {
    const timeStamp = parseInt(`${this.passwordChangedAt.getTime() / 1000}` , 10); 
    if(this.passwordChangedAt){
        return JWTTimestamp < timeStamp; 
    }else{
        return false; 
    }
}

UserSchema.methods.createPasswordResetToken = function() {
    const resetToken = crypto.randomBytes(32).toString('hex');
    this.passwordResetToken = crypto.createHash('sha256').update(resetToken).digest('hex');
    this.passwordResetExpired = Date.now() + 10 * 60 * 1000; 
    return resetToken; 

}

export const UserModel = mongoose.model('User', UserSchema, 'users'); 