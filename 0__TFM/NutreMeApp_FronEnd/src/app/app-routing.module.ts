import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginRegisterComponent } from './shared/components/auth-section/login-register.component';

const routes: Routes = [ 
  /* En este caso se implementa lazy-load, cuando la ruta sea nutri-form se cargara el modulo de formulario nutricional, con sus rutas...etc. */
  {path:'nutriapp', loadChildren: () => import('./nutri-home/nutri-home.module').then( m =>  m.NutriHomeModule)},
  {path: 'login', component: LoginRegisterComponent},
  {path: '**', redirectTo: 'login'}

 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
