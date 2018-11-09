import { Component, OnInit } from '@angular/core';
import { ConfirmationComponent } from '../confirmation/confirmation.component';

import { CanDeactivate } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal';

export interface ComponentCanDeactivate {
  canDeactivate: () => boolean | Observable<boolean>;
}

@Injectable()
export class DeactivateGuard implements CanDeactivate<ComponentCanDeactivate> {

  modalRef: BsModalRef;

  constructor(private modalService: BsModalService) {};

  canDeactivate(component: ComponentCanDeactivate): boolean | Observable<boolean> {
  return component.canDeactivate ? component.canDeactivate() : true;
  }

  openConfirmDialog() {
    this.modalRef = this.modalService.show(ConfirmationComponent);
    return this.modalRef.content.onClose.map(result => {
        return result;
    });
  }
}