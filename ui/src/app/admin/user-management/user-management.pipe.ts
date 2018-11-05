import { Pipe, PipeTransform } from '@angular/core';

import { User } from '../../_models/index';

@Pipe({ name: 'enabledUsers' })
export class EnabledUsersPipe implements PipeTransform {
  transform(allUsers: User[], filterEnabled: boolean) {
  if(!allUsers) return [];
  	if (filterEnabled) {
    	return allUsers.filter(user => user.enabled);
  	} else {
  		return allUsers;
  	}
  }
}