export class OperationalError extends Error {
    private _status : string; 
    public isOperational = true; 

    constructor(message: string, public statusCode : number = 500) {
        super(message); 
        this._status = `${this.statusCode}`.startsWith('4') ? 'fail': 'error' ;
    }

    public get status(){ return this._status}; 

}