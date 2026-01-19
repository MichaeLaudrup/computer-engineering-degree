export const calcMBA = function ( height: number, weight: number, age: number, gender: string ) : number { 
    let mba = 0; 
    if(gender === 'Hombre'){
        mba = Math.trunc(66.5 + (13.76 * weight ) + (5.003 * height) - (6.755 * age)); 
    }else{
        mba = (665 + (9.563 * weight ) + (1.850 * height) - (4.676 * age));; 
    }
    return mba; 
}

export const calcIMC = function (height: number, weight: number) : number {
    return ((weight) / (height/100)**2); 
}


export function calcDiaryWater(weight: number): number {
    return (weight / 7 * 250) / 1000; 
    
}

export function calcMBAWithActivityAndObjective(nutritionalTarget: string, mbaWithActivity: number): any {
    switch(nutritionalTarget){
        case "BAJAR_PESO": 
            return mbaWithActivity * 0.8;
        case "SUBIR_PESO":
            return mbaWithActivity * 1.20; 
        case "DEFINIR": 
            return mbaWithActivity * 0.9;
        case "GANAR_MASA_MUSCULAR": 
            return mbaWithActivity * 1.10; 
        default: 
            return mbaWithActivity;  
    }
}

export const calcMacroNutriensDistribution = (mba: number, nutritionTarget: string): { carbohydrates: number; proteins: number; fats: number; }  =>
{
    // 1.6 -2.5 gr de proteina por kg de peso
    // .5 - 1gr de grasa por kg de peso
    // El resto hidratos
    
    /* let proteinsCal = (83*2)*4; 
    let fatCal = (83*1) * 9;
    let hidrates = mbaWithActivityAndObjetive - proteinsCal -fatCal;   */
    return {
        carbohydrates: +((mba * .55) /4).toFixed(2),
        fats:  +((mba * .25) / 9).toFixed(2),
        proteins: +((mba * .20) / 4).toFixed(2)
    }; 
}

export function calcMBAWithObjective(mba: number, nutritionalTarget: any): any {
    switch(nutritionalTarget){
        case "BAJAR_PESO": 
            return mba * 0.8;
        case "SUBIR_PESO":
            return mba * 1.20; 
        case "DEFINIR": 
            return mba * 0.9;
        case "GANAR_MASA_MUSCULAR": 
            return mba * 1.10; 
        default: 
            return mba;  
    }
}
