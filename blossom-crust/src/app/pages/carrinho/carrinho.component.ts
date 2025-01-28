import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HeaderComponent } from '../../components/header/header.component';

@Component({
  selector: 'app-carrinho',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.scss']
})
export class CarrinhoComponent implements OnInit {
  itensCarrinho = [
    { nome: 'Pizza Margherita', quantidade: 2, preco: 25.00 },
    { nome: 'Pizza Pepperoni', quantidade: 1, preco: 30.00 }
  ];
  total = 0;

  constructor(private router: Router) {}

  ngOnInit() {
    this.calcularTotal();
  }

  calcularTotal() {
    this.total = this.itensCarrinho.reduce((acc, item) => acc + (item.quantidade * item.preco), 0);
  }

  finalizarPedido() {
    // Lógica para finalizar o pedido
    console.log('Pedido finalizado!');
    this.router.navigate(['/confirmacao']); // Navega para a página de confirmação
  }
}
