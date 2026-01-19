import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListMealsResolver } from 'src/app/shared/routing-components';
import { ListMealsComponent } from './components/list-meals/list-meals.component';
import { MealSectionsComponent } from './components/meal-sections/meal-sections.component';
import { StoreLayoutComponent } from './store-layout/store-layout.component';


const routes: Routes = [
  { path: '', 
    component: StoreLayoutComponent, 
    children: [
      { path: '', component: MealSectionsComponent, data: {animation:{ name_page:'section_meals'}}},
      { path: ':id',
          component: ListMealsComponent,
          data: {animation:{ name_page:'meals'}}
      }
    ]
   }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StoreRoomRoutingModule { }
