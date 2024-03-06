import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {CommonModule} from "@angular/common";
import { ChartData } from 'chart.js/auto';
import {MatTableModule} from "@angular/material/table";
@Component({
  selector: 'app-chart-modal',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './chart-modal.component.html',
  styleUrl: './chart-modal.component.scss'
})
export class ChartModalComponent {
  // chartData: ChartData = {
  //   labels: ['Currency'],
  //   datasets: [{
  //     label: 'Currency Chart',
  //     data: [this.data.history.currency],
  //     backgroundColor: 'rgba(255, 99, 132, 0.2)',
  //     borderColor: 'rgba(255, 99, 132, 1)',
  //     borderWidth: 1
  //   }]
  // };

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              private dialogRef: MatDialogRef<ChartModalComponent>) { }

  closeModal(): void {
    this.dialogRef.close();
  }
}
