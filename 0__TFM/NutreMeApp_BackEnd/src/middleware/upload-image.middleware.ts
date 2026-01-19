import { Request } from "express";

/* MIDDLE WARE ENCARGADO DE LA CAPTACION DE IMAGENES AL SUBIRSE DESDE FRONT */
const multer = require('multer'); 

const selectDestinationByURL = function(requestFullUrl: string) : string {
    const urlFragments = requestFullUrl.split('/'); 
    const parentUrlFragment = urlFragments[urlFragments.length -2 ]; 
    switch(parentUrlFragment){
        case 'section-meals': 
            return 'sections-images'
        default:
            return ''
    } 
}



const multerStorage = multer.diskStorage({
    destination: (req:Request,file:any, cb:any) => {
        const folderName = selectDestinationByURL(req.originalUrl); 
        cb(null, `${__dirname}/../public/${folderName}`)
    },
    filename: (req:any, file:any, cb:any) => {
        const folderName = selectDestinationByURL(req.originalUrl); 
        const ext = file.mimetype.split('/')[1]; 
        const pathName = `${folderName}-${req.params['id'] ?? req.body.user._id}.${ext}`;
        cb(null, pathName ); 
    }
}); 

const multerFilter = (req:any, file:any, cb:any) => {
    if(file.mimetype.startsWith('image')){
        cb(null, true); 
    }else{
        cb(new Error('Tipo de archivo no soportado, introduzca una imagen'), false)
    }
}

const upload = multer({
    storage: multerStorage,
    fileFilter: multerFilter
}); 

/* Middle ware que se encarga de capturar la imagen y almacenarla en servidor */
export const uploadUserPhotoMiddleware = upload.single('photo')