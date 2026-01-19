import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDataFacadeService } from '@ngrx/ngrx-shared';
import { Allergens } from 'src/app/shared/enums/allergens.enum';
import { CarrouselService } from '../../servicios/carrousel.service';

@Component({
  selector: 'app-select-allergens',
  templateUrl: './select-allergens.component.html',
  styleUrls: ['./../../shared/shared-nutri-form.styles.scss','./select-allergens.component.scss']
})
export class SelectAllergensComponent implements OnInit {
  allergensNames = ['Huevo', 'Pescado', 'Fructosa', 'Gluten', 'Lactosa', 'Frutos secos', 'Marisco', 'Soja']
  allergens = [Allergens.Egg, Allergens.Fish, Allergens.Fructose, Allergens.Gluten, Allergens.Lactose, Allergens.Nuts, Allergens.SeaFood, Allergens.Soy]
  selectedAllergens: string[]= []; 
  selectedAllergensEnum : Allergens[] = []; 
  
  constructor(
    private carrouselService: CarrouselService, 
    private userDataFacadeServices: UserDataFacadeService,
    private router: Router) {
      setTimeout(() => this.carrouselService.setPage(3) , 0); 
    }

  ngOnInit(): void {
  }

  toggleSelectedAlergen(clickedAlergen: Allergens){
    if(this.selectedAllergens.includes(clickedAlergen)){
      let index = this.selectedAllergens.findIndex( alergen => alergen === clickedAlergen);
      this.selectedAllergens.splice(index,1)
      let index2 = this.selectedAllergensEnum.findIndex( alergen => alergen === clickedAlergen); 
      this.selectedAllergensEnum.splice(index,1); 
    }else{
      this.selectedAllergens.push(clickedAlergen.toString());
      this.selectedAllergensEnum.push(clickedAlergen)
    }
  }

  nextPage(){
    this.userDataFacadeServices.setAllergens(this.selectedAllergensEnum)
    this.router.navigate(['/nutriapp/nutri-form/select-forbidden-food'])
  }


}
