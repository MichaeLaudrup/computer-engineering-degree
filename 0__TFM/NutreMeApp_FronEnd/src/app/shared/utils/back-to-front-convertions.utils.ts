import { UserData } from "../models/fisiologicData.model"

export const userDataFromBackToFront = (userDataFromBackEnd: any): UserData => {
    const userDataCasted: UserData = {
        nutritionalTarget: userDataFromBackEnd.nutritionalTarget,
        feedingType: userDataFromBackEnd.feedingType,
        fisiologicData: {
            age: userDataFromBackEnd.age,
            weight: userDataFromBackEnd.weight,
            height: userDataFromBackEnd.height,
            gender: userDataFromBackEnd.gender,
            activityIntesity: userDataFromBackEnd.activityIntesity,
            mba: userDataFromBackEnd.mba,
            imc: userDataFromBackEnd.imc,
            diaryWater: userDataFromBackEnd.diaryWater,
            mbaWithObjective: userDataFromBackEnd.mbaWithObjetive,
            mbaWithActivityAndObjetive: userDataFromBackEnd.mbaWithActivityAndObjetive,
            macrosInRepose: userDataFromBackEnd.macrosInRepose,
            macrosWithActivity: userDataFromBackEnd.macrosWithActivity
        }
    }
    return userDataCasted; 
}