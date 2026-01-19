
import { AfterViewInit, ChangeDetectorRef, Component, OnDestroy, OnInit } from "@angular/core";
import {  Router, RouterOutlet } from "@angular/router";
import { UserDataFacadeService, usersFacade } from "@ngrx/ngrx-shared";
import { DeviceMode } from "@shared/enums";
import { UserData } from "@shared/models";
import { take } from "rxjs";
import { Subject, takeUntil } from "rxjs";
import { UserInterfaceService } from "src/app/core/services/user-interface.service";
import { sliderAnimations } from "./animations";
import { CarrouselService } from "./servicios/carrousel.service";

@Component({
    selector: 'nutri-form-layout',
    templateUrl: './nutri-form-layout.component.html',
    styleUrls: ['./nutri-form-layout.component.scss'],
    animations:[ ...sliderAnimations]
})
export class nutriFormLayout implements OnInit, AfterViewInit, OnDestroy {
    actualPage = -1; 
    progressValue = 0; 
    routes = ['select-objective', 'fisiologic-data', 'feeding-type', 'select-allergens', 'select-forbidden-food' ]; 
    height = 0; 
    isInMobileMood = false; 
    private destroySuscriptions$: Subject<any> = new Subject<any>(); 
    currentRoute: string = 'none';
    userData: UserData; 
    constructor(
        private carrouselService: CarrouselService,
        private changeDetector: ChangeDetectorRef,
        private router: Router,
        private userInterfaceService: UserInterfaceService,
        private userProfileFacadeService: usersFacade,
        private userDataFacadeService: UserDataFacadeService){ 
    }

    ngOnInit(): void {
        this.carrouselService.actualPage$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( (actualPage) => {
            this.actualPage = actualPage; 
            this.progressValue = (this.actualPage * 100) /(this.routes.length -1 );
        }); 
        
        this.carrouselService.uploadDataToServerListener$.pipe(takeUntil(this.destroySuscriptions$)).subscribe((mustUpload) => {
            if(mustUpload){
                this.carrouselService.resetUploadToServer(); 
                this.uploadFormDataToServer(); 
            }
        })
        this.userInterfaceService.actualHeight$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( actualHeight => {
            this.height = actualHeight; 
        });

        this.userInterfaceService.deviceMode$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( deviceMode => {
            if(deviceMode === DeviceMode.Small){
                this.isInMobileMood = true; 
            } 
        });

        this.userDataFacadeService.userData$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( userData => {
            this.userData = userData; 
        })
    }

    ngAfterViewInit(): void {
        this.changeDetector.detectChanges(); 
    }

    ngOnDestroy(): void {
        this.destroySuscriptions$.next({})
        this.destroySuscriptions$.unsubscribe()
    }

    previousPage(){
        if(this.actualPage !== 0){
            this.router.navigate( ['/nutriapp/nutri-form/' +this.routes[this.actualPage-1]])
        }
    }

    nextPage() {
        if(this.actualPage < (this.routes.length - 1)){
            this.router.navigate(['/nutriapp/nutri-form/' +this.routes[this.actualPage+1]])
        }
    }

    processOutlet(event: any, outlet: RouterOutlet){
        this.currentRoute = outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation']; 
    }

    uploadFormDataToServer(){
        this.userProfileFacadeService.$user.pipe( take(1)).subscribe(user => {
            this.userDataFacadeService.uploadToServerUserData('631b2304df093a140ac6730f', this.userData); 
        })
    }
}