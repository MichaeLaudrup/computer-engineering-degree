import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable()
export class CarrouselService{
    actualPage = new BehaviorSubject<number>(0); 
    actualPage$ = this.actualPage.asObservable(); 
    uploadDataToServer = new BehaviorSubject<boolean>(false); 
    uploadDataToServerListener$= this.uploadDataToServer.asObservable(); 
    setPage(newPage: number){
        this.actualPage.next(newPage); 
 
    }

    uploadDataToServerTrigger(){
        this.uploadDataToServer.next(true);
    }

    resetUploadToServer(){
        this.uploadDataToServer.next(false)
    }
}