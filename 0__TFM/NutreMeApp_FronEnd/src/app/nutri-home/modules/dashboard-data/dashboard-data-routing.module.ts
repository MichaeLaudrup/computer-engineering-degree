import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { dailyMealsRegisterResolver } from 'src/app/shared/routing-components/resolvers/daily-register.resolver';
import { UserDataResolver } from 'src/app/shared/routing-components/resolvers/user-data.resolver';
import { DashboardDataComponent } from './dashboard-data.component';

const routes: Routes = [
    {path:'', component: DashboardDataComponent, resolve: {
      userData: UserDataResolver,
      dailyMealsRegister: dailyMealsRegisterResolver
    } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardDataRoutingModule {

}
