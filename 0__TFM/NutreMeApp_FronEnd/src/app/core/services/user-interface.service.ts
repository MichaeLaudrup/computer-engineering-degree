import { Injectable } from "@angular/core";
import { DeviceMode } from "@shared/enums";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserInterfaceService {
  private actualHeight : BehaviorSubject<number> = new BehaviorSubject(100);
  public actualHeight$: Observable<number> = this.actualHeight.asObservable(); 


  private deviceMode: BehaviorSubject<DeviceMode> = new BehaviorSubject(DeviceMode.Laptop); 
  public deviceMode$: Observable<DeviceMode> = this.deviceMode.asObservable(); 

  public setHeight(newHeight: number){
      this.actualHeight.next(newHeight); 
  }

  public setDeviceMode( deviceMode: DeviceMode){
    this.deviceMode.next( deviceMode); 
  }
}