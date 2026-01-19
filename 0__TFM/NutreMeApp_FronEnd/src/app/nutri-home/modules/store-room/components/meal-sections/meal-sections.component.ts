import { Component, HostBinding, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CreateUpdateModalData, deleteModalData, SectionMeal } from '@shared/models';
import { sharedFacadeService } from '@ngrx/ngrx-shared';
import { Subject, takeUntil } from 'rxjs';
import { typeModalSpecialization } from '@shared/enums';
import { StoreRoomService } from '../../services/store-room.service';
import { MealSectionsFacade } from '@ngrx/ngrx-section-meals';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-meal-sections',
  templateUrl: './meal-sections.component.html',
  styleUrls: ['./meal-sections.component.scss'],
})
export class MealSectionsComponent implements OnInit, OnDestroy{
  mealSections: SectionMeal[] = []; 
  editMood = false; 
  destroy$: Subject<any> = new Subject<any>();

  urlSectionsImages = environment.staticSectionsImagesURL; 
  constructor(
      private router:Router,
      private mealSectionsFacade: MealSectionsFacade,
      private sharedFacade: sharedFacadeService,
      private storeService: StoreRoomService) { 
    
  }

  ngOnInit(): void {
    this.mealSectionsFacade.requestSectionMeals(); 
    this.mealSectionsFacade.sectionMeals$.pipe( takeUntil( this.destroy$)).subscribe(( {sections, loaded}) => {
      if(loaded){
        this.mealSections = sections; 
        this.mealSectionsFacade.resetLoaded(); 
      }
      
    }); 
    

    this.storeService.editModeClicked$.pipe(takeUntil(this.destroy$)).subscribe((editMode) => {
      this.editMood = editMode; 
    });


    this.storeService.editModeClicked$.pipe(takeUntil(this.destroy$)).subscribe((editMode) => {
      this.editMood = editMode; 
    });

    this.storeService.setActionsText(['Crear sección', 'Modificar sección']); 

    this.storeService.createItemClicked$.pipe(takeUntil(this.destroy$)).subscribe((createModeClicked) => {
      if(createModeClicked){
        this.showCreateUpdateModal(); 
      }
    })
  }

  ngOnDestroy(): void {
    this.destroy$.next(true); 
    this.destroy$.unsubscribe(); 
  }

  navigateToListMeals(id:string){
    this.router.navigate([`nutriapp/nutri-store/${id}`])
  }

  showCreateUpdateModal() {
    const modalData: CreateUpdateModalData = {
      title: 'Crear nueva sección',
      typeOfModalSpecialization: typeModalSpecialization.createMealSection,
    } 
    this.sharedFacade.displayModal(typeModalSpecialization.createMealSection, modalData); 
  }

  enableEditMode( mealSection: SectionMeal){
    const modalData: CreateUpdateModalData = {
      title: 'Editar sección',
      typeOfModalSpecialization: typeModalSpecialization.UpdateMealSection,
      sectionToEdit: {...mealSection, _id:mealSection._id}
    };
    this.sharedFacade.displayModal(typeModalSpecialization.UpdateMealSection, modalData)
  }

  enableDeleteMode( id: string){
    const modalData: deleteModalData = {
      id,
      title: '¿Estas seguro de que desea eliminar la sección?',
      typeOfModalSpecialization: typeModalSpecialization.DeleteMealSection,
      funtionOnDelete: this.deleteItem.bind(this, id)
    }
    this.sharedFacade.displayModal(typeModalSpecialization.Delete, modalData); 
  }

  public deleteItem(id:string): void {
    this.mealSectionsFacade.deleteSection(id); 
  }
}
