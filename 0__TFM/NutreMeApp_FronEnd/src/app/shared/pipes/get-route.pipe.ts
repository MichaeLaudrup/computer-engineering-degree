import { Pipe, PipeTransform } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Pipe({
  name: 'getRoute'
})
export class GetRoutePipe implements PipeTransform {

  transform(value: RouterOutlet, ...args: unknown[]): unknown {
    return value; 
  }

}
