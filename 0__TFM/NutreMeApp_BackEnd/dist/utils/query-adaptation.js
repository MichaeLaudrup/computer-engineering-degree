"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.processRequestPagination = exports.requestContainsParams = exports.processFilteredFields = exports.processSortByFields = exports.queryFieldsExistInModel = exports.copyParamsAndDeleteEspecialOperations = void 0;
const copyParamsAndDeleteEspecialOperations = (req) => {
    const specialQueryParams = ['sort', 'page', 'fields', 'limit'];
    const queryParamsCopy = Object.assign({}, req.query);
    specialQueryParams.forEach(specialOperator => delete queryParamsCopy[specialOperator]);
    return queryParamsCopy;
};
exports.copyParamsAndDeleteEspecialOperations = copyParamsAndDeleteEspecialOperations;
const queryFieldsExistInModel = (fieldsOfQuery, fieldsOfModel) => {
    for (let queryField of fieldsOfQuery) {
        if (!(fieldsOfModel.includes(queryField.replace(/-/, '')))) {
            return false;
        }
    }
    return true;
};
exports.queryFieldsExistInModel = queryFieldsExistInModel;
const processSortByFields = (req, query, fieldsOfModel) => {
    if (req.query['sort'] && typeof req.query['sort'] === 'string') {
        let sortBy = req.query['sort'].split(',');
        if (!(0, exports.queryFieldsExistInModel)(sortBy, fieldsOfModel))
            throw 'You\'re trying to sort in a field that doesn\'t exist in meal model';
        return query.sort(sortBy.join(' '));
    }
    return query.sort('name');
};
exports.processSortByFields = processSortByFields;
const processFilteredFields = (req, query, fieldsOfModel) => {
    if (req.query['fields'] && typeof req.query['fields'] === 'string') {
        const fields = req.query.fields.split(',');
        if ((0, exports.queryFieldsExistInModel)(fields, fieldsOfModel)) {
            return query.select(fields.join(' '));
        }
        else {
            throw 'Esta intentando filtrar por un campo que no existe en el modelo';
        }
    }
    else {
        return query.select('-__v');
    }
};
exports.processFilteredFields = processFilteredFields;
const requestContainsParams = (req) => {
    return !(Object.keys(req.query).length === 0);
};
exports.requestContainsParams = requestContainsParams;
const processRequestPagination = (req, query, numItems) => {
    let page = 1;
    let limit = 100;
    if ((req.query['page'] && (typeof req.query['page'] === 'string') && !isNaN(+req.query['page']))) {
        page = (+req.query['page']) * 1 || 1;
    }
    if (req.query['limit'] && (typeof req.query['limit'] === 'string') && !isNaN(+req.query['limit'])) {
        limit = (+req.query['limit']) * 1 || 100;
    }
    let skip = (page - 1) * limit;
    if (skip > numItems) {
        throw 'No existe una pagina ' + page + ' en la coleccion de elementos, especifique una pagina menor.';
    }
    else {
        return query.skip(skip).limit(limit);
    }
};
exports.processRequestPagination = processRequestPagination;
