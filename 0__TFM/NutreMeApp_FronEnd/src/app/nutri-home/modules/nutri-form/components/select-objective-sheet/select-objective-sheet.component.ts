import {  ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { Subject} from 'rxjs';
import { CarrouselService } from '../../servicios/carrousel.service';

import { NutritionTarget } from '@shared/enums';
import { UserDataFacadeService } from '@ngrx/ngrx-shared';
@Component({
  selector: 'app-select-objective-sheet',
  templateUrl: './select-objective-sheet.component.html',
  styleUrls: ['./../../shared/shared-nutri-form.styles.scss','./select-objective-sheet.component.scss', '../../shared.style.scss'],
})
export class SelectObjectiveSheetComponent implements OnInit, OnDestroy {
  NutritionTarget = NutritionTarget; 
  private destroySuscriptions$: Subject<any> = new Subject()
 
  constructor( 
    private carrouselService: CarrouselService,
    private router: Router,
    private userFacadeServices: UserDataFacadeService) { }

  ngOnInit(): void {
    setTimeout(() => this.carrouselService.setPage(0) , 0) 
  }
  



  ngOnDestroy(): void {
    this.destroySuscriptions$.next({})
    this.destroySuscriptions$.unsubscribe()
  }

  selectObjective(target: NutritionTarget): void {
    this.userFacadeServices.setObjective(target); 
    this.router.navigate(['/nutriapp/nutri-form/fisiologic-data']); 
  }


}
