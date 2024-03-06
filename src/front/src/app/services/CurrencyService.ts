import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Currency} from "../models/currensy";

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  private apiUrl = 'http://localhost:9000/api/';

  constructor(private http: HttpClient) { }

  getAllCurrencies(): Observable<Currency[]> {
    return this.http.get<Currency[]>(this.apiUrl);
  }

  getAllCurrenciesByDate(): Observable<Currency[]> {
    return this.http.get<Currency[]>(this.apiUrl + "currenciesByDate");
  }

  getCurrencyHistory(currencyCode: any) {
    const url = `${this.apiUrl}history?currencyCode=${currencyCode}`;
    return this.http.get<string[]>(url);
  }

}
