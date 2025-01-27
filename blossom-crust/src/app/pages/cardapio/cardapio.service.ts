import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Pizza } from '../model/pizza.model';

@Injectable({
  providedIn: 'root'
})
export class CardapioService {

  constructor(private http: HttpClient) {}

  public buscarTodas(): Observable<Pizza[]> {
    return this.http.get<{ pizzas: Pizza[] }>('http://localhost:8080/pizzas/todas').pipe(
      map(response => response.pizzas)
    );
  }
  
}
