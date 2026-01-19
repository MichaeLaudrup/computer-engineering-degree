import { InfoModalData } from "@shared/models"; 
import { typeModalSpecialization } from "../enums/type-modals.enum";

export const mbaInfoData: InfoModalData = {
    typeOfModalSpecialization: typeModalSpecialization.infoWithLinksModal,
    title: "¿Qué es el metabolismo basal?",
    subtitle: "MBA",
    content: "La tasa metabólica basal (TMB) es la cantidad mínima de energía que necesita \
              tu cuerpo durante un día. En este gráfico te ilustramos tu tasa metabólica \
              basal en reposo, es decir, sin hacer deporte, además de la tasa metabólica basal \
              haciendo deporte y la necesaria según tu objetivo sea subir, bajar, mantener..etc. tu peso",
    links: [
        {
        title: '¿Qué son las kcal?',
        description: '',
        url: 'https://es.wikipedia.org/wiki/Calor%C3%ADa'
        },
        {
            title: '¿Cuantás calorías debo consumir en un día?',
            description: '',
            url: 'https://es.wikipedia.org/wiki/%C3%8Dndice_metab%C3%B3lico_basal'
        },
    ],
    device: 'Los días que no entrenes intenta conseguir un consumo de kcal similar a tu MBA, los días de entrenamiento \
             intenta conseguir el consumo de kcal propio de realizar actividad física y asociado a tu objetivo.'
}

export const imcInfoData: InfoModalData = {
  typeOfModalSpecialization: typeModalSpecialization.infoWithLinksModal,
    title: "¿Qué es el indice de masa corporal?",
    subtitle: "IMC",
    content: "Clasifica a las personas en infrapeso, peso normal, sobrepeso y obesidad, basándose exclusivamente en la masa del individuo y su altura. \
     No tendría en cuenta la edad, el sexo, el porcentaje de grasa corporal o la masa muscular. Por eso hay que coger la información con precaución ",
    links: [
        {
        title: '¿Que es el índice de masa corporal?',
        description: '',
        url: 'https://es.wikipedia.org/wiki/%C3%8Dndice_de_masa_corporal'
        },
    ],
    device: 'Los días que no entrenes intenta conseguir un consumo de kcal similar a tu MBA, los días de entrenamiento \
             intenta conseguir el consumo de kcal propio de realizar actividad física y asociado a tu objetivo.'
}

export const kcalHistoryInfoData: InfoModalData = {
  typeOfModalSpecialization: typeModalSpecialization.infoWithLinksModal,
    title:'Historial de consumo calórico',
    content:'El historial de consumo calórico controla el número de kilo calorías diarías que consumes (línea de color verde), frente al número de Kcal diarias \
                        que deberías consumir según tus necesidades nutricionales ',
    subtitle: 'MBA', 
    links: [
      {
      title: '¿Qué son las kcal?',
      description: '',
      url: 'https://es.wikipedia.org/wiki/Calor%C3%ADa'
      },
      {
        title: '¿Cuantás calorías debo consumir en un día?',
        description: '',
        url: 'https://es.wikipedia.org/wiki/%C3%8Dndice_metab%C3%B3lico_basal'
      },
    ]
  }; 
