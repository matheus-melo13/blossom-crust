import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { CardapioService } from './cardapio.service';
import { Pizza } from '../model/pizza.model';
import { NgFor } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';  // Certifique-se de que o HttpClientModule está importado

@Component({
  selector: 'app-cardapio',
  standalone: true,
  imports: [HeaderComponent, NgFor, HttpClientModule],  // HttpClientModule está aqui
  templateUrl: './cardapio.component.html',
  styleUrls: ['./cardapio.component.scss']
})
export class CardapioComponent {
  
  pizzas: Pizza[] = [];

  constructor(
    private cardapioService: CardapioService
  ) {}

  ngOnInit() {
    this.cardapioService.buscarTodas().subscribe(pizzas => {
      pizzas.shift();
      this.pizzas = pizzas;
    });
  }
}
