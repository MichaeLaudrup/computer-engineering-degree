
import { Request} from 'express'; 

export const copyParamsAndDeleteEspecialOperations = (req: Request) => {
    const specialQueryParams = ['sort', 'page', 'fields', 'limit']
    const queryParamsCopy = {...req.query};
    specialQueryParams.forEach(specialOperator => delete queryParamsCopy[specialOperator]); 
    return queryParamsCopy; 
}

export const queryFieldsExistInModel = ( fieldsOfQuery: string[], fieldsOfModel: string[]) : string | boolean => {
    for(let queryField of fieldsOfQuery ){
        if(!(fieldsOfModel.includes(queryField.replace(/-/, '')))){
            return false; 
        }
    }
    return true; 
}
export const processSortByFields = (req: Request, query: any, fieldsOfModel: string[]) => {
    if(req.query['sort'] && typeof req.query['sort'] === 'string'){
        let sortBy = req.query['sort'].split(',');  
        if(!queryFieldsExistInModel(sortBy, fieldsOfModel)) throw 'You\'re trying to sort in a field that doesn\'t exist in meal model'
        return query.sort(sortBy.join(' ')); 
    }
    return query.sort('name'); 
}

export const processFilteredFields = (req:Request, query: any, fieldsOfModel: string[]) => {
    if(req.query['fields'] && typeof req.query['fields'] === 'string'){
        const fields = req.query.fields.split(','); 
        if(queryFieldsExistInModel(fields, fieldsOfModel)){
            return query.select(fields.join(' '));
        }else{
            throw 'Esta intentando filtrar por un campo que no existe en el modelo';
        }
    }else{
        return query.select('-__v'); 
    }
}

export const requestContainsParams = (req: Request) => {
    return !(Object.keys(req.query).length === 0); 
}

export const processRequestPagination = (req: Request, query: any, numItems: number) => {
    let page: number = 1; 
    let limit: number = 100; 
    if( (req.query['page'] && (typeof req.query['page'] === 'string') && !isNaN(+req.query['page'])) ) {
        page = (+req.query['page']) * 1 || 1; 
    }
    if ( req.query['limit'] && (typeof req.query['limit'] === 'string')  && !isNaN(+req.query['limit'])){
        limit = (+req.query['limit']) * 1 || 100;   
    }
    let skip = (page -1 ) * limit; 
    if( skip > numItems ){
        throw 'No existe una pagina ' + page + ' en la coleccion de elementos, especifique una pagina menor.'
    }else{
        return query.skip(skip).limit(limit); 
    }
}