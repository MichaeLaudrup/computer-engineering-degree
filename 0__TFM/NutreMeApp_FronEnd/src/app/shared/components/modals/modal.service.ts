import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable()
export class ModalService {
    private showLoading = new BehaviorSubject<boolean>(false); 
    public loadingListener$ = this.showLoading.asObservable(); 
    
    private hideModal = new BehaviorSubject<boolean>(false);
    public hideModalListener$ = this.hideModal.asObservable(); 

    private showFeedback = new BehaviorSubject<{ show:boolean, message:string}>({show:false, message: ''});
    public  showFeedback$ = this.showFeedback.asObservable(); 

    constructor(){}

    public showLoadingSpinner( show: boolean = false) {
        this.showLoading.next(show); 
    }

    public showFeedBackMessage(show: boolean = false, message: string ){
        this.showFeedback.next({show, message}); 
    }

    public hideModalNow(bool:boolean = false) {
        this.hideModal.next(bool); 
    }

    public resetAll() {
        this.showFeedBackMessage(false,''); 
        this.hideModalNow(false); 
        this.showLoadingSpinner(false); 
    }
}