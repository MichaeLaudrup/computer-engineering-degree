const nodemailer = require('nodemailer'); 

export const sendEmail = (options: {}) => {
    const transporter = nodemailer.createTransport({
        service: 'Gmail',
        auth: {
            
        }
    })
}