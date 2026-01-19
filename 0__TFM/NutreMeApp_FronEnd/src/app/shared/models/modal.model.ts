import { typeModalSpecialization } from "../enums/type-modals.enum";
import { Aliment } from "./meal.model";
import { SectionMeal } from "./section-meal.model";

export type ModalDataTypes =  InfoModalData | CreateUpdateModalData | deleteModalData |createNewMealInSectionModalData; 


export interface ModalData {
    title: string,
    subtitle?: string,
    typeOfModalSpecialization: typeModalSpecialization,
    functionOnButtonClicked?: () => void,
}

export interface InfoModalData extends ModalData{
    content: string, 
    links?:Array< {
        title: string,
        url: string, 
        description: string
    }>
    device?: string,
}


export interface CreateUpdateModalData extends ModalData {
    sectionToEdit?: SectionMeal;
}

export interface deleteModalData extends ModalData {
    id: string;
    sectionId?: string, 
    funtionOnDelete(): void
}

export interface createNewMealInSectionModalData extends ModalData {
    sectionId: string, 
}

export interface updateMealInSectionModalData extends ModalData{
    sectionId: string,
    meal: Aliment
}