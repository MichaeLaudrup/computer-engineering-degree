import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { dailyMealsRegisterResolver } from 'src/app/shared/routing-components/resolvers/daily-register.resolver';
import { DietLayoutComponent } from './diet-layout/diet-layout.component';

const routes: Routes = [
  {path: '', component: DietLayoutComponent, children: [
    
  ], resolve: {dailyRegister: dailyMealsRegisterResolver} }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DietRoutingModule { }
