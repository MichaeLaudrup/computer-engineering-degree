import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { UserInterfaceService } from './core/services/user-interface.service';
import { DeviceMode } from './shared/enums/device-mode.enum'; 
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy{
  title = 'nutremeApp';

  @HostListener('window:resize', ['$event']) onResize(event ?: Event) {
    this.userInterfaceService.setHeight(window.innerHeight);
    const windowWidth = window.innerWidth; 

    if(windowWidth < 320){
      this.userInterfaceService.setDeviceMode(DeviceMode.NoSupport); 
    }else if(windowWidth >= 320 && windowWidth <= 420){
      this.userInterfaceService.setDeviceMode(DeviceMode.ExtraSmall); 
    }else if(windowWidth > 420 && windowWidth <= 576){
      this.userInterfaceService.setDeviceMode(DeviceMode.Small);
    }else if(windowWidth > 576 && windowWidth <= 768){
      this.userInterfaceService.setDeviceMode(DeviceMode.Tablet); 
    }else if(windowWidth > 767 && windowWidth <= 1024){
      this.userInterfaceService.setDeviceMode(DeviceMode.Laptop); 
    }else if(windowWidth > 1024 ){
      this.userInterfaceService.setDeviceMode(DeviceMode.BigLaptop); 
    }
  }

  constructor( private store: Store<any>, private userInterfaceService: UserInterfaceService){
  }
  ngOnInit() {
    this.onResize(); 
  }

  ngOnDestroy(): void {
  }


}
