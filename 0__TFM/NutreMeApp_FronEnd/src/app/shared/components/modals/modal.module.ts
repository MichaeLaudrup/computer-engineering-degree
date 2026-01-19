import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { GenericComponentsModule } from "../generic-components/generic-components.module";
import { MealFormComponent } from "./create-update-modal/meal-form/meal-form.component";
import { CreationOfSectionMealComponent } from "./create-update-modal/section-meal-form/section-meal-form.component";
import { InfoModalComponent } from "./info-modal/info-modal.component";
import { ModalComponent } from "./modal.component";
import { InfoActivatorComponent } from "./modalActivators/info-activator/info-activator.component";

const components = [
    ModalComponent,
    CreationOfSectionMealComponent,
    MealFormComponent,
    InfoModalComponent,
    InfoActivatorComponent,
]
@NgModule({
    declarations: [components],
    exports: [components],
    imports: [
         CommonModule,
         ReactiveFormsModule,
         GenericComponentsModule
        ]
})
export class ModalsModule {

}