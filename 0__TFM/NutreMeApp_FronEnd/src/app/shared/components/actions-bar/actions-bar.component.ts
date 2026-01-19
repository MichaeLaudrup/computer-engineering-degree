import { animate, style, transition, trigger } from "@angular/animations";
import { CommonModule } from "@angular/common";
import { Component, EventEmitter, HostListener, Input, Output } from "@angular/core";


@Component({
    selector: 'actions-bar',
    templateUrl: './actions-bar.component.html',
    styleUrls: [
        './actions-bar.component.scss'
    ],
    animations: [
        trigger('subMenuAnimation', [
            transition(':leave', [
                style({ transform: 'translateY(0)'}),
                animate('.5s',style({transform: 'translateY(-100%)'}))
            ])
        ]),
    ]

})
export class ActionsBarComponent {
    editActionsActive: boolean = false;
    clickInside: boolean = false; 
    onEditMode : boolean = false; 
    @Output() createItemClicked = new EventEmitter<any>(); 
    @Output() editModeClicked = new EventEmitter<any>(); 
    @Input() editText = 'Editar elementos'; 
    @Input() createText = 'Insertar elemento'; 
    @HostListener('document:click') clicked () {
        if(!this.clickInside) {
            this.editActionsActive = false; 
        }
        this.clickInside = false; 
    } 

    triggerCreateMode(){
        this.createItemClicked.emit(); 
    }

    triggerEditMode() {
        this.onEditMode = !this.onEditMode; 
        this.editModeClicked.emit(); 
    }
}