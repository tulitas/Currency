import { Injectable } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ChartModalComponent} from "../chart-modal/chart-modal.component";

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  constructor(private dialog: MatDialog) { }

  openChartModal(history: any[]): void {
    console.log(history)
    this.dialog.open(ChartModalComponent, {
      width: '500px',
      data: { history: history }
    });
  }
}
