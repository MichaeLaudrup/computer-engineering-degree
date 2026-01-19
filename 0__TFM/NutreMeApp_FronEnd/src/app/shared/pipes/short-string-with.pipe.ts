import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'shortStringWith'
})
export class ShortStringWithPipe implements PipeTransform {

  transform(value: string, start: number, finish: number, lastWords: string): string {
    return value.slice(start, finish) + ((value.length >= finish) ? lastWords : '');
  }

}
