import { Component, HostBinding, Input, OnInit } from '@angular/core';
import { UserDataFacadeService } from '@ngrx/ngrx-shared';
import { UserData } from '@shared/models';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'user-data-resume',
  templateUrl: './user-data-resume.component.html',
  styleUrls: ['./user-data-resume.component.scss']
})
export class UserDataResumeComponent implements OnInit {
  userData: UserData; 
  @HostBinding('style.backgroundColor') backGround = 'var(--elements-bg-primary)';
  @Input() bgColor = ''
  private destroySuscriptions$: Subject<any> = new Subject()
  ngOnDestroy(): void {
      this.destroySuscriptions$.next({})
       this.destroySuscriptions$.unsubscribe()
  }


  constructor( private userDataFacade: UserDataFacadeService) { }

  ngOnInit(): void {
    if(this.bgColor !== ''){
      this.backGround = this.bgColor; 
    }
    this.userDataFacade.userData$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( userData => {
      this.userData = userData; 
    })
  }

}
