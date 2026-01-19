import { createAction, props } from '@ngrx/store';
import { typeModalSpecialization } from '@shared/enums';
import { ModalDataTypes } from '@shared/models';





/* Se crea la accion de insertar un objetivo a traves del formulario al estado global */
export const displayModal = createAction(
  '[Shared] Show a modal',
  props<{ typeOfModal: typeModalSpecialization, modalData: ModalDataTypes ;   }>()
);

export const hideModal = createAction(
  '[Shared] Hide Modal'
)

export const updateRealHeight = createAction(
  '[Shared] Update Height',
  props<{newHeight: number}>()
)

export const showGlobalLoading = createAction(
  '[Shared] Show Global Loading',
  props<{customText: string}>()
)

export const hideGlobalLoading = createAction(
  '[Shared] Hide Global Loading',
)


