import { AfterViewInit, ChangeDetectorRef, Component,OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { UserInterfaceService } from '../core/services/user-interface.service';
import { DeviceMode } from '@shared/enums';
import { typeModalSpecialization } from '../shared/enums/type-modals.enum';
import { sharedFacadeService } from '@ngrx/ngrx-shared';
import { RouterOutlet } from '@angular/router';
import { delayDesaparition } from '../shared/animations/animation';

@Component({
  selector: 'app-nutri-home',
  templateUrl: './nutri-global-layout.component.html',
  styleUrls: ['./nutri-global-layout.component.scss'],
  animations: [delayDesaparition]
})
export class NutriGlobalLayoutComponent implements OnInit, OnDestroy, AfterViewInit {
  DeviceModee = DeviceMode; 
  typeModalSpecialization = typeModalSpecialization; 
  infoModalOpened: boolean = false; 
  createUpdateModalOpened: boolean = false; 
  typeModal: string = ''; 
  destroySuscriptions$: Subject<any> = new Subject(); 
  modalStatus: typeModalSpecialization;
  height: number = 0; 
  deviceMode: DeviceMode; 
  actualRute: string = ''

  

  constructor( private sharedFacade: sharedFacadeService,
                private userInterfaceService: UserInterfaceService,
                private changeDetector: ChangeDetectorRef){}
  ngOnInit() {
    this.sharedFacade.modalStatus$.pipe(takeUntil( this.destroySuscriptions$)).subscribe( ( modalStatus: typeModalSpecialization) => {
      this.modalStatus = modalStatus; 
    }); 

    this.userInterfaceService.deviceMode$.pipe( takeUntil( this.destroySuscriptions$)).subscribe( (deviceMode: DeviceMode) => {
      this.deviceMode = deviceMode; 
    })

    this.userInterfaceService.actualHeight$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( (height) => {
      this.height = height; 
    })
  }
  ngAfterViewInit(): void {
    this.changeDetector.detectChanges(); 
  }

  ngOnDestroy(): void {
    this.destroySuscriptions$.next({});
    this.destroySuscriptions$.unsubscribe();  
  }
  processOutlet(event: any, outlet: RouterOutlet){
    this.actualRute = outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];

  }


}
