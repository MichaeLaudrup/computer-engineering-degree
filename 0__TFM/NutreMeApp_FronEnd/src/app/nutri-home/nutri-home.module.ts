import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

/* MODULOS MIOS */
import { SharedModule } from '../shared/shared.module';

import { NutriHomeRoutingModule } from './nutri-home-routing.module';
import { NutriGlobalLayoutComponent } from './nutri-global-layout.component';
import { userLogedGuard } from '../shared/routing-components/guards/auth.guard';
import { sharedFacadeService, UserDataFacadeService } from '@ngrx/ngrx-shared';
import { UserDataResolver } from '../shared/routing-components/resolvers/user-data.resolver';



@NgModule({
  declarations: [
    NutriGlobalLayoutComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    NutriHomeRoutingModule,
  ],
  providers: [
    sharedFacadeService,
    userLogedGuard,
    UserDataResolver,
    UserDataFacadeService
  ]
})
export class NutriHomeModule { }
