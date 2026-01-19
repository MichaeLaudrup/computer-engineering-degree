import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserInterfaceService } from '@core/services';
import { UserDataFacadeService } from '@ngrx/ngrx-shared';
import { Aliment } from '@shared/models';
import { CarrouselService } from '../../servicios/carrousel.service';

@Component({
  selector: 'app-select-forbidden-food',
  templateUrl: './select-forbidden-food.component.html',
  styleUrls: ['./../../shared/shared-nutri-form.styles.scss','./select-forbidden-food.component.scss']
})
export class SelectForbiddenFoodComponent implements OnInit {

  
  constructor(
    private carrouselService: CarrouselService, 
    private userDataFacadeServices: UserDataFacadeService,
    private userInterfaceService: UserInterfaceService ,
    private router: Router) {
      setTimeout(() => this.carrouselService.setPage(4) , 0); 
    }
  
    ngOnInit( ): void {
      
    }

    processForbiddenMeals(events: Aliment[]){
      const filterIds: string[] = events.map( meal => meal._id); 
      this.userDataFacadeServices.setForbiddenAliments(filterIds)
      this.carrouselService.uploadDataToServerTrigger(); 
      this.router.navigate(['/nutriapp/nutri-data'])
    }
  }
