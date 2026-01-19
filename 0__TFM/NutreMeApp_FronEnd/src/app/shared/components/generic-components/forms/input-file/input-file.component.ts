import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'input-file',
  templateUrl: './input-file.component.html',
  styleUrls: ['./input-file.component.scss']
})
export class InputFileComponent implements OnInit {
  isActive: boolean = false; 
  imagePreviewUrl: string = undefined;
  fileToUpload: File; 
  @Input() label: string = ''
  @Output() fileLoaded: EventEmitter<File> = new EventEmitter(); 
  constructor() { }

  ngOnInit(): void {
  }

  processDrop(event){
    this.fileToUpload = event.dataTransfer.files[0];
    this.processFile();
    
  }

  processChange(event){
    if(event.target.files.length > 0){
      this.fileToUpload = event.target.files[0];
      this.processFile(); 
    }
  }

  processFile(){
    let fileType = this.fileToUpload.type; 
    let validExtensions = ["image/jpeg", "image/jpg", "image/png"];
    if(validExtensions.includes(fileType)){ 
      let fileReader = new FileReader(); 
      fileReader.onload = ()=>{
        let fileURL = <string>fileReader.result; 
        this.imagePreviewUrl = fileURL; 
      }
      fileReader.readAsDataURL(this.fileToUpload);
      this.fileLoaded.emit(this.fileToUpload); 
    }else{
      alert("This is not an Image File!");
      this.isActive = false; 
      this.imagePreviewUrl = undefined
    }
  }

}
/* 
var $fileInput = $('.file-input');
var $droparea = $('.file-drop-area');


// change inner text
$fileInput.on('change', function() {
  var filesCount = $(this)[0].files.length;
  var $textContainer = $(this).prev();

  if (filesCount === 1) {
    // if single file is selected, show file name
    var fileName = $(this).val().split('\\').pop();
    $textContainer.text(fileName);
  } else {
    // otherwise show number of files
    $textContainer.text(filesCount + ' files selected');
  }
});
 */