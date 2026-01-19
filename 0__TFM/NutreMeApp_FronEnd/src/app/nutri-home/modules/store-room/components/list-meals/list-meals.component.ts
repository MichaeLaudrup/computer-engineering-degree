import { animate, keyframes, query, stagger, style, transition, trigger } from '@angular/animations';
import { Component, HostBinding, HostListener, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DeviceMode, typeModalSpecialization} from '@shared/enums';
import { Aliment, createNewMealInSectionModalData, deleteModalData, ModalData, SectionMeal, updateMealInSectionModalData } from '@shared/models';
import { sharedFacadeService } from '@ngrx/ngrx-shared';
import { Subject, take, takeUntil } from 'rxjs';
import { StoreRoomService } from '../../services/store-room.service';

import { getMealTag} from '@shared/enums';
import { MealSectionsFacade } from '@ngrx/ngrx-section-meals';
import { UserInterfaceService } from '@core/services';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-list-meals',
  templateUrl: './list-meals.component.html',
  styleUrls: ['./list-meals.component.scss'],
  animations: [
    trigger('listMealsAnimation',
      [ transition('* => true', [
        query('.list-item', [
          style({
            transform: 'translateX(-100%)',
            opacity: 0
          }),
          stagger('.2s', 
          animate('1s ease-in-out',
            keyframes([
              style({
                transform: 'translateX(-100%)',
                opacity: 0,
                offset: 0
              }),
              style({
                transform: 'translateX(10%)',
                opacity: .4, 
                offset: 0.8
              }),
              style({
                transform: 'translateX(0%)',
                opacity: 1,
                offset: 1
              })
            ]))),
          
        ], {optional: true}),
      ])] ),
      trigger('list-item-animation', [
        transition('no-status => to-expanded', [
          style({}),
          animate('.7s', style({}))
        ]),
        transition('to-expanded => no-status', [
          style({}),
          animate('.7s', style({}))
        ])
      ])
      
  ]
})
export class ListMealsComponent implements OnInit, OnDestroy {
  @HostBinding('@listMealsAnimation') animation = false; 
  @HostListener('document:click') globalClicked() {
    if(!this.clickInside){
      this.activeListItem = -1;
    }
    this.clickInside = false; 
  }
  showExpanded = false; 
  foodImagesURL = environment.staticMealsImagesURL; 
  getMealTag = getMealTag; 
  animationExpanded: string = 'no-status'; 
  destroySucriptions$: Subject<boolean> = new Subject<boolean>(); 
  aliments : Aliment[] = [  ];
  activeListItem: number = -1; 
  options = {} 
  sectionId = ''; 
  clickInside = false;
  constructor( 
    private sharedFacade: sharedFacadeService,
    private sectionMealsFacade: MealSectionsFacade
     ,private route: ActivatedRoute,
        private storeRoomService: StoreRoomService,
        private userInterfaceService: UserInterfaceService) { }

  ngOnInit(): void {
    this.storeRoomService.setClickedCreate(false); 
    this.sectionId = this.route.snapshot.params['id']; 
    this.sectionMealsFacade.requestSectionMeals(); 
    this.sectionMealsFacade.sectionMealById$(this.sectionId).pipe(takeUntil( this.destroySucriptions$)).subscribe( ( sectionMeal: SectionMeal) => {
      if(!!sectionMeal){
        this.animation = true; 
        this.aliments = sectionMeal.meals; 
      }
    });

    this.userInterfaceService.deviceMode$.pipe(takeUntil(this.destroySucriptions$)).subscribe( deviceMode => {
      if(deviceMode === DeviceMode.BigLaptop || deviceMode === DeviceMode.Laptop){
        this.showExpanded = true; 
      }else{
        this.showExpanded = false; 
      }
    })

    this.storeRoomService.createItemClicked$.pipe(takeUntil(this.destroySucriptions$)).subscribe( (createMode) => {
      if(createMode){
        const newModal: createNewMealInSectionModalData= {
          typeOfModalSpecialization: typeModalSpecialization.CreateMeal_InSection,
          sectionId: this.sectionId,
          title: 'Crear nuevo alimento'
        }
        this.sharedFacade.displayModal( typeModalSpecialization.CreateMeal_InSection, newModal); 
        this.storeRoomService.setClickedCreate(false); 
      }
    })
  }

    

  ngOnDestroy(): void {
    this.destroySucriptions$.next(true);
    this.destroySucriptions$.unsubscribe();  
  }

  conmuteListItemStatus( i: number){
    this.activeListItem = i; 
  }

  activeEditMode(index:number){
    const modalData: updateMealInSectionModalData = {
      sectionId: this.sectionId, 
      meal: this.aliments[index],
      title:'Editar alimento o receta',
      typeOfModalSpecialization: typeModalSpecialization.UpdateMealInSection
    }

    this.sharedFacade.displayModal(typeModalSpecialization.EditMealInSection, modalData)
  }

  activeDeleteMode(){
    const newModal: deleteModalData = {
      id: this.aliments[this.activeListItem]._id +'',
      funtionOnDelete: this.deleteItem.bind(this, this.sectionId, this.aliments[this.activeListItem]._id),
      typeOfModalSpecialization: typeModalSpecialization.Delete,
      title: `Desea eliminar ${this.aliments[this.activeListItem].name}`
    }
    this.activeListItem = -1; 
    this.sharedFacade.displayModal(typeModalSpecialization.Delete, newModal); 
  }

  deleteItem(sectionId: string, mealId:string){
    this.sectionMealsFacade.deleteMealFromSection(sectionId, mealId); 
  }
}
