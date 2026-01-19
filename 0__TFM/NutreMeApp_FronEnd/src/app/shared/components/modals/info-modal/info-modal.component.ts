import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { InfoModalData } from '@shared/models';

import { sharedFacadeService } from '@ngrx/ngrx-shared';
import { ModalComponent } from '../modal.component';

@Component({
  selector: 'app-info-modal',
  templateUrl: './info-modal.component.html',
  styleUrls: ['./info-modal.component.scss']
})
export class InfoModalComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }
}
