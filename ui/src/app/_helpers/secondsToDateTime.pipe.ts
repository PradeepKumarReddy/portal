import { Pipe, PipeTransform } from '@angular/core';
@Pipe( {name: 'secondsToDateTimePipe'} )
export class SecondsToDateTimePipe implements PipeTransform {
transform(value: any) {
return new Date(1970, 0, 1).setSeconds(value);
}
}
