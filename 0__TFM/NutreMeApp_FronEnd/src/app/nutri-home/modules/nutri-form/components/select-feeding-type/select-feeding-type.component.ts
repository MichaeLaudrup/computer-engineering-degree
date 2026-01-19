import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FeedingType} from '@shared/enums';
import { UserDataFacadeService } from '@ngrx/ngrx-shared';
import { CarrouselService } from '../../servicios/carrousel.service';

@Component({
  selector: 'app-select-feeding-type',
  templateUrl: './select-feeding-type.component.html',
  styleUrls: ['./../../shared/shared-nutri-form.styles.scss','./select-feeding-type.component.scss']
})
export class SelectFeedingTypeComponent implements OnInit {
  FeedingType = FeedingType;
  constructor(
    private carrouselService: CarrouselService, private userDataFacadeServices: UserDataFacadeService, private router: Router) {
      setTimeout(() => this.carrouselService.setPage(2) , 0); 
    }

  ngOnInit(): void {
  }
  selectFeedingType(feedingType: FeedingType): void {
    this.userDataFacadeServices.setFeedingType(feedingType);
    this.router.navigate(['/nutriapp/nutri-form/select-allergens']); 
  }
}
