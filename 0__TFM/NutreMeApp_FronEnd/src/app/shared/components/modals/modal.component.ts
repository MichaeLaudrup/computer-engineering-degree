import { Component, HostBinding, HostListener, Input, OnDestroy, OnInit } from "@angular/core";
import { typeModalSpecialization } from "@shared/enums";
import { createNewMealInSectionModalData, CreateUpdateModalData, deleteModalData, ModalDataTypes, updateMealInSectionModalData } from "../../models/modal.model";
import { Subject, take, takeUntil } from "rxjs";
import { enterAndLeaveModalAnimation, fromModalToFeedbackMood } from "./modal.animations";
import { ModalService } from "./modal.service";
import { SectionMeal } from "../../models/section-meal.model";
import { Aliment } from "../../models/meal.model";
import { UserInterfaceService } from "@core/services";
import { sharedFacadeService } from "@ngrx/ngrx-shared";
import { MealSectionsFacade } from "@ngrx/ngrx-section-meals";

@Component({
    selector: 'modal',
    templateUrl: './modal.component.html',
    styleUrls: ['./modal.component.scss'],
    animations: [
        enterAndLeaveModalAnimation,
        fromModalToFeedbackMood
    ],
    providers: [ModalService]
  })
export class ModalComponent implements OnInit, OnDestroy {
    @HostBinding('@modal-animation') modalAnimation = true; 
    @HostBinding('@feeback-mood') feedBackMood: boolean = false; 
    @Input('modal-status') modalStatus: typeModalSpecialization;
    sectionToUpdate: SectionMeal; 
    wasInside: boolean = true; 
    questionMode: boolean = false; 
    _title: string; 
    _content: string; 
    modalData: ModalDataTypes; 
    itemId: string = ''; 
    loadingMood: boolean = false; 
    meal: Aliment; 
    modalSpecializations = typeModalSpecialization; 
    customCallToActionButton: boolean = false;
    feedBackText: string = '';  
    height: number = 0; 
    private destroySuscriptions$: Subject<any> = new Subject()

    constructor(
      protected sharedFacade: sharedFacadeService,
      private sectionMealsFacade: MealSectionsFacade,
      private modalService: ModalService,
      private userInterfaceService: UserInterfaceService){
        
    }

    ngOnInit(): void {
      this.userInterfaceService.actualHeight$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( newHeight => this.height = newHeight)
      this.getModalData(); 
      this.modalService.loadingListener$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( (showSpinner) => {
        this.loadingMood = showSpinner; 
      });

      this.modalService.showFeedback$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( (showFeedBack) => {
        if(showFeedBack){
          this.loadingMood = false; 
          this.feedBackMood = showFeedBack.show; 
          this.feedBackText = showFeedBack.message;
        }
      });

      this.modalService.hideModalListener$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( ( hideModal) => {
        if(hideModal){
          this.hideModal(); 
        }
      })
    }
    
    ngOnDestroy(): void {
      this.destroySuscriptions$.next({})
      this.destroySuscriptions$.unsubscribe();
      this.modalService.resetAll(); 
  }

    getModalData(){
      this.sharedFacade.modalData$.pipe( takeUntil(this.destroySuscriptions$)).subscribe( ( modalData) => {
        this.modalData = modalData;
        switch(this.modalData.typeOfModalSpecialization){
          case typeModalSpecialization.UpdateMealSection: 
            this.sectionToUpdate = (<CreateUpdateModalData>this.modalData)?.sectionToEdit; 
            this.customCallToActionButton = true; 
            break; 
          case typeModalSpecialization.createMealSection:
            this.customCallToActionButton = true; 
          break; 
          case typeModalSpecialization.CreateMeal_InSection: 
            this.itemId = (<createNewMealInSectionModalData>this.modalData).sectionId; 
            this.customCallToActionButton = true; 
            break; 
          case typeModalSpecialization.Delete: 
          case typeModalSpecialization.DeleteMealSection:
            this.questionMode = true;  
            this.itemId = (<deleteModalData>this.modalData).id; 
            this.customCallToActionButton = true; 
            break; 
          case typeModalSpecialization.UpdateMealInSection: 
            this.itemId = (<updateMealInSectionModalData>this.modalData).sectionId; 
            this.meal = (<updateMealInSectionModalData>this.modalData).meal;
          default: 
        }
      });  
    }


    hideModal() {
        this.sharedFacade.hideModal(); 
    }
    public get title(): string {
        return this._title;
    }
    public set title(value: string) {
        this._title = value;
    }

    public get content(): string {
        return this._content;
    }
    public set content(value: string) {
        this._content = value;
    }

    showFeedBack(feedBackText: string){
      this.feedBackMood = true; 
      this.feedBackText = feedBackText; 
    }

    affirmativeAnswer() {
      switch(this.modalData.typeOfModalSpecialization){
        case this.modalSpecializations.Delete: 
        case this.modalSpecializations.DeleteMealSection: 
          (<deleteModalData>this.modalData).funtionOnDelete(); 
          this.modalService.showLoadingSpinner(true); 
          this.sectionMealsFacade.feedbackListener$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( (feedback) => {
            if(feedback.showFeedBack) {
                this.loadingMood = false; 
                this.feedBackMood = true; 
                this.feedBackText = feedback.feedBackMessage; 
                setTimeout( () => {
                  this.sectionMealsFacade.resetFeedback(); 
                  this.hideModal(); 
                }, 1750); 
            }
          });
          break; 
        default: 
      }
    }



}