import { Component, Input, OnInit } from '@angular/core';
import { InfoModalData } from '@shared/models';
import { sharedFacadeService } from '@ngrx/ngrx-shared';
@Component({
  selector: 'app-modal-info-activator',
  templateUrl: './info-activator.component.html',
  styleUrls: ['./info-activator.component.scss']
})
export class InfoActivatorComponent implements OnInit {
  @Input() modalInfo: InfoModalData; 
  constructor(private sharedFacade: sharedFacadeService) { }

  ngOnInit(): void {
  }

  showInfoModal(){
  }

}
