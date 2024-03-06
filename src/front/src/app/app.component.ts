import {Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Currency} from "./models/currensy";
import {CurrencyService} from "./services/CurrencyService";
import {CommonModule} from "@angular/common";
import { MatTableModule } from '@angular/material/table'
import {map} from 'rxjs/operators';
import {Observable} from "rxjs";
import {ModalService} from "./services/ModalService";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, AppComponent, CommonModule, MatTableModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{
  title = 'Currency';
  currencies: Currency[] = [];
  amount: number = 0;
  selectedCurrencyFrom: string = '';
  selectedCurrencyTo: string = '';
  result: number = 0;

  constructor(private currencyService: CurrencyService,
     private modalService: ModalService) {

  }

  ngOnInit(): void {
    this.getAllCurrencies();
  }

  getAllCurrencies(): void {
    this.currencyService.getAllCurrenciesByDate()
      .pipe(
        map((currencies: Currency[]) => {
          return currencies.map(currency => ({
            ...currency,
            flagUrl: `https://restcountries.com/v3.1/currency/${currency.currency}`
          }));
        })
      )
      .subscribe((currenciesWithFlags: Currency[]) => {
        this.currencies = currenciesWithFlags;
      });
  }

  onRowClicked(currency: any) {
    const currencyCode = currency.currency;
    this.openChartModal(this.currencyService.getCurrencyHistory(currencyCode));
  }

  private openChartModal(currencyHistory: Observable<string[]>): void {
    currencyHistory.subscribe(history => {
      this.modalService.openChartModal(history);
    });
  }

  calculate(): void {

    if (this.amount && this.selectedCurrencyFrom && this.selectedCurrencyTo) {
      const currencyFrom = this.currencies.find(currency => currency.currency === this.selectedCurrencyFrom);
      const currencyTo = this.currencies.find(currency => currency.currency === this.selectedCurrencyTo);
      if (currencyFrom && currencyTo) {
        this.result = parseFloat(((parseFloat(this.amount.toString()) * parseFloat(currencyFrom.rate.toString())) / parseFloat(currencyTo.rate.toString())).toFixed(2));
      }
    }
  }
}
