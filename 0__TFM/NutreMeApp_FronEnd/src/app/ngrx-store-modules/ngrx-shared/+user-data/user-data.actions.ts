import { createAction, props } from '@ngrx/store';
import { FeedingType } from '@shared/enums';
import { Allergens } from 'src/app/shared/enums/allergens.enum';
import { NutritionTarget } from 'src/app/shared/enums/nutrition-target.enum';
import { FisiologicData, UserData} from 'src/app/shared/models/fisiologicData.model';

/* Se crea la accion de insertar un objetivo a traves del formulario al estado global */
export const setTarget = createAction(
  '[Shared] Introducir objetivo',
  props<{ nutritionalTarget: NutritionTarget }>()
);

export const putFisiologicData= createAction(
  '[NutriApp] Poner datos fisiologicos usuario',
  props<{ fisiologicData: FisiologicData}>() 
)

export const postFeedingType = createAction(
  '[User-data] Insertar tipo de alimentacion del usuario',
  props<{ feedingType: FeedingType}>() 
)

export const uploadUserDataToServer = createAction(
  '[User-data] Subir a servidor datos del usuario',
  props<{ userId:string, userData: UserData}>()
)
export const uploadUserDataToServerSuccess = createAction(
  '[User-data] Exito al subir a servidor datos del usuario',
  props<{ user: UserData}>()
)

export const uploadUserDataToServerFailure = createAction(
  '[User-data] Fallo al subir a servidor datos del usuario',
  props<{ errorPayload: string}>()
)

export const setAllergens = createAction(
  '[User-data] Esblecer alergenos',
  props<{allergens: Allergens[]}>()
)

export const setForbiddenAliments = createAction(
  '[User-data] Establecer alimentos prohibidos',
  props<{forbiddenAliments: string[]}>()
)

export const resetAll = createAction(
  '[User-data] Restablecer a valor inicial datos de usuario',
)
