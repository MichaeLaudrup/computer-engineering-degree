export const toDateInputValue = function() {
    let today = new Date(); 
    return `${today.getFullYear()}-${('0' +(today.getMonth() +1)).slice(-2)}-${('0' +(today.getDate())).slice(-2)}`
};

export const DateToString = function (date:Date){
    return `${date.getFullYear()}-${('0' +(date.getMonth() +1)).slice(-2)}-${('0' +(date.getDate())).slice(-2)}`
}
