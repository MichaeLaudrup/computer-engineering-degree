import { Component,  OnDestroy,  OnInit } from '@angular/core';
import { UserInterfaceService } from '@core/services';
import { UserDataFacadeService } from '@ngrx/ngrx-shared';
import { imcInfoData, kcalHistoryInfoData, mbaInfoData } from '@shared/data';
import { DeviceMode } from '@shared/enums';
import {  Subject, takeUntil} from 'rxjs';
import { NutritionTarget } from 'src/app/shared/enums/nutrition-target.enum';
import { FisiologicData} from 'src/app/shared/models/fisiologicData.model';



@Component({
  selector: 'app-dashboard-data',
  templateUrl: './dashboard-data.component.html',
  styleUrls: ['./dashboard-data.component.scss'],
})
export class DashboardDataComponent implements OnInit, OnDestroy {
  fisiologicData: FisiologicData; 
  objetive: NutritionTarget;
  mba: number = 0;
  mbaWithActivity: number = 0; 
  mbaWithActivityAndObjetive: number = 0;  
  modalsInfo: any = {}; 
  deviceMode: DeviceMode; 
  DEVICE_MODE = DeviceMode; 
  
  private destroySuscriptions$: Subject<any> = new Subject()
  
  constructor( private userDataFacade: UserDataFacadeService, private userInterfaceService: UserInterfaceService) {
    this.userDataFacade.fisiologicData$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( ( fisiologicData) => {
      this.fisiologicData = fisiologicData; 
    });

    this.userDataFacade.nutritionTarget$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( (objetive) => {
      this.objetive = objetive; 
    })

    this.userInterfaceService.deviceMode$.pipe(takeUntil(this.destroySuscriptions$)).subscribe((deviceMode) => {
      this.deviceMode = deviceMode; 
    }); 
  }
  
  ngOnInit(): void {
    this.setModalInfoData(); 
  }

  ngOnDestroy(): void {
    this.destroySuscriptions$.next({})
     this.destroySuscriptions$.unsubscribe()
  }


  public setModalInfoData() {
     this.modalsInfo.mbaInfoData = mbaInfoData; 
     this.modalsInfo.imcInfoData = imcInfoData; 
     this.modalsInfo.kcalHistoryInfoData = kcalHistoryInfoData; 
  }




}
