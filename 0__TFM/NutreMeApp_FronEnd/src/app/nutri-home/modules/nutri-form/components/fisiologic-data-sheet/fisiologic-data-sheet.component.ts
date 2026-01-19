import { AfterContentChecked, Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FisiologicData, UserData} from '@shared/models';
import { Observable, Subject, takeUntil } from 'rxjs';
import { UserDataFacadeService } from '@ngrx/ngrx-shared';
import { CarrouselService } from '../../servicios/carrousel.service';

@Component({
  selector: 'app-fisiologic-data-sheet',
  templateUrl: './fisiologic-data-sheet.component.html',
  styleUrls: ['./../../shared/shared-nutri-form.styles.scss','./fisiologic-data-sheet.component.scss', '../../shared.style.scss'],
})
export class FisiologicDataSheetComponent implements OnInit, OnDestroy{
  fisiologicForm: FormGroup;  
  fisiologicData$: Observable<UserData>; 

  private destroySuscriptions$: Subject<any> = new Subject(); 

  constructor( 
      private carrouselService: CarrouselService,
      private userFacade: UserDataFacadeService,
      private router: Router) {

    this.fisiologicForm = new FormGroup({
      height: new FormControl(150, Validators.required),
      weight: new FormControl(60, Validators.required),
      age: new FormControl(25, Validators.required),
      gender: new FormControl('Hombre', Validators.required),
      activityIntesity: new FormControl(1.2 ,Validators.required)
    })
  }

  ngOnInit(): void {
    setTimeout(() => this.carrouselService.setPage(1) , 0)
    this.userFacade.fisiologicData$.pipe( takeUntil(this.destroySuscriptions$)).subscribe( ( fisiologicData: FisiologicData) => {
      this.fisiologicForm.patchValue( {...fisiologicData})
    })
  }

  ngOnDestroy(): void {
    this.destroySuscriptions$.next({})
    this.destroySuscriptions$.unsubscribe()
  }

  processData(): void {
    if(this.fisiologicForm.valid){
      this.userFacade.setFisiologicData( {...this.fisiologicForm.value});
      this.router.navigate(['/nutriapp/nutri-form/feeding-type']); 
    }
  }

}
