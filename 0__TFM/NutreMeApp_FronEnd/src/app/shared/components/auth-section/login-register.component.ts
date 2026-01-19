import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserInterfaceService } from '@core/services';
import { usersFacade } from '@ngrx/ngrx-shared';
import { Subject, take, takeUntil } from 'rxjs';
import { DeviceMode } from '../../enums/device-mode.enum';
import { User } from '../../models/user.model';
import { messageAnimations } from './animations';

@Component({
  selector: 'login-register',
  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.scss'],
  animations: [ messageAnimations]
})
export class LoginRegisterComponent implements OnInit {
  moodLogin: boolean = true; 
  height: number = 0;  
  signUpForm: FormGroup; 
  logInForm: FormGroup; 
  isLoginMode: boolean = true; 
  deviceMode: DeviceMode; 
  DeviceMode = DeviceMode; 
  smallMode = false; 
  private destroySuscriptions$: Subject<any> = new Subject()

  constructor( private enrutador: Router,private uiService: UserInterfaceService, private userFacade: usersFacade) {
    this.signUpForm = new FormGroup({
      name: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      passwordConfirm: new FormControl('', Validators.required),
    })
    this.logInForm = new FormGroup({
      email : new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    })

  }

  ngOnInit(): void {
    sessionStorage.clear(); 
    this.uiService.deviceMode$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( (device: DeviceMode) => {
      this.smallMode = device === DeviceMode.Small || device === DeviceMode.ExtraSmall || device === DeviceMode.NoSupport; 
    })
    this.uiService.actualHeight$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( height => this.height = height);
    
    this.userFacade.userLoaded$.pipe(takeUntil(this.destroySuscriptions$)).subscribe( isLoaded => {
      if(isLoaded){
        this.userFacade.resetIsLoaded(); 
        this.enrutador.navigate(['/nutriapp'])
      }
    })
  }
  ngOnDestroy(): void {
    this.destroySuscriptions$.next({})
     this.destroySuscriptions$.unsubscribe()
}

  goTo(){
    this.enrutador.navigate(['nutriapp'])
  }
  processSignUp(){
    const newUser: User= {
      ...this.signUpForm.value
    }
    this.userFacade.signUp(newUser); 
  }

  processLogIn(){
    const user :User = {
      ...this.logInForm.value
    }
    this.userFacade.logIn(user); 
  }

}
