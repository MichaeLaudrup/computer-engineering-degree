import { CommonModule } from "@angular/common";
import { ModuleWithProviders, NgModule } from "@angular/core";
import { EffectsModule } from "@ngrx/effects";
import { StoreModule } from "@ngrx/store";
import { userDataEffects } from "./+user-data/user-data.effects";
import { userDataReducer } from "./+user-data/user-data.reducer";
import { sharedFacadeService } from "./+user-interface/user-interface.facade";
import { sharedReducer } from "./+user-interface/user-interface.reducer";
import { userEffects } from "./+users/users.effects";
import { usersReducer } from "./+users/users.reducer";

@NgModule({
    imports: [
        CommonModule,
        StoreModule.forFeature('shared',  {
            modals: sharedReducer,
            user: usersReducer,
            userData: userDataReducer
        }),
        EffectsModule.forFeature([ userEffects, userDataEffects])
    ]
})
export class NgRxSharedModule{
     static forRoot(): ModuleWithProviders<NgRxSharedModule> {
        return {
            ngModule: NgRxSharedModule,
            providers: [sharedFacadeService]
        }
     }
}