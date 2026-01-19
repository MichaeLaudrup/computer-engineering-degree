import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DinamicSvgIconComponent } from './dinamic-svg-icon/dinamic-svg-icon.component';
import { ImageTargetComponent } from './image-target/image-target.component';
import { TextInputComponent } from './forms/text-input/text-input.component';
import { ImgsInputComponent } from './forms/imgs-input/imgs-input.component';
import { GenericButtonComponent } from './forms/generic-button/generic-button.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoadingSppinerComponent } from './loading-sppiner/loading-sppiner.component';
import { RadialChartComponent } from './charts/radial-chart/radial-chart.component';
import { GaugeComponent } from './charts';
import { SliderInputComponent } from './forms/slider-input/slider-input.component';
import { SearchInputComponent } from './forms/search-input/search-input.component';
import { MealsResultListComponent } from './bussiness-logic/meals-result-list/meals-result-list.component';
import { ListMealItemComponent } from './bussiness-logic/list-meal-item/list-meal-item.component';
import { DaySelectorComponent } from './forms/day-selector/day-selector.component';
import { CheckBoxComponent } from './forms/check-box/check-box.component';
import { ScheduledMealItemComponent } from './bussiness-logic/scheduled-meal-item/scheduled-meal-item.component';
import { ProgressBarComponent } from './charts/progress-bar/progress-bar.component';
import { ChangeCssVariableValueDirective } from '../../directives/change-css-variable-value.directive';
import { DialyMacroChartComponent } from './charts/dialy-macro-chart/dialy-macro-chart.component';
import { InputFileComponent } from './forms/input-file/input-file.component';
import { UserResumeComponent } from './user-resume/user-resume.component';
import { UserDataResumeComponent } from './user-data-resume/user-data-resume.component';

const components = [
  DinamicSvgIconComponent,
  ImageTargetComponent,
  TextInputComponent,
  ImgsInputComponent,
  GenericButtonComponent,
  LoadingSppinerComponent, 
  RadialChartComponent,
  GaugeComponent, 
  SliderInputComponent,
  SearchInputComponent,
  MealsResultListComponent,
  ListMealItemComponent,
  DaySelectorComponent, 
  CheckBoxComponent, 
  ScheduledMealItemComponent,
  ProgressBarComponent,
  ChangeCssVariableValueDirective,
  DialyMacroChartComponent,
  InputFileComponent, 
  UserResumeComponent,
  UserDataResumeComponent
]
@NgModule({
  declarations: [...components],
  imports: [
    CommonModule,
    ReactiveFormsModule,
  ],
  exports: components
})
export class GenericComponentsModule { }
