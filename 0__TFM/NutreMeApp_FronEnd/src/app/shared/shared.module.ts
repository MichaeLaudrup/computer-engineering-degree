import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { ConnectFormDirective } from './directives/connect-form.directive';
import { GenericComponentsModule } from './components/generic-components/generic-components.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ActionsBarComponent } from './components/actions-bar/actions-bar.component';
import { ModalsModule } from './components/modals/modal.module';
import { ShortStringWithPipe } from './pipes/short-string-with.pipe';
import { fromTagToText, toTagSvgIconPipe } from './pipes/tagsPipe.pipe';
import { AsideBarMenuComponent } from './layout/asidebar-menu/asidebar-menu.component';
import { RouterModule } from '@angular/router';
import { GetRoutePipe } from './pipes/get-route.pipe';
import { LoginRegisterComponent } from './components/auth-section/login-register.component';
import { MealSectionsFacade } from '../ngrx-store-modules/ngrx-meals-sections';
import { NgRxSharedModule, usersFacade } from '../ngrx-store-modules/ngrx-shared';
import { toSvgAlergenIcon } from './pipes/allergens.pipe';



const components = [
  HeaderComponent,
  FooterComponent,
  ConnectFormDirective,
  ActionsBarComponent,
  toTagSvgIconPipe,
  AsideBarMenuComponent,
  GetRoutePipe,
  LoginRegisterComponent,
  ShortStringWithPipe,
  toSvgAlergenIcon,
  fromTagToText
]

@NgModule({
  declarations: [
    components
  ],
  imports: [
    CommonModule,
    RouterModule,
    GenericComponentsModule,
    ReactiveFormsModule,
    ModalsModule,
    NgRxSharedModule.forRoot()

  ],
  exports: [
    components,
    GenericComponentsModule,
    ModalsModule
  ],

  providers: [
    MealSectionsFacade,
    usersFacade,
  ]
})
export class SharedModule {

}
