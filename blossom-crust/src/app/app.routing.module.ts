import { Route } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { CadastroComponent } from './pages/cadastro/cadastro.component';
import { FazerLoginComponent } from './components/fazer-login/fazer-login.component';
import { CardapioComponent } from './pages/cardapio/cardapio.component';
import { CarrinhoComponent } from './pages/carrinho/carrinho.component';

export const routes: Route[] = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'login/cliente/cadastro', component: CadastroComponent, data: {tipo: 'cliente' } },
  { path: 'login/funcionario/cadastro', component: CadastroComponent, data: {tipo: 'funcionario' } },
  { path: 'login/cliente', component: FazerLoginComponent, data: {tipo: 'cliente'} },
  { path: 'login/funcionario', component: FazerLoginComponent, data: {tipo: 'funcionario'} },
  { path: 'cardapio', component: CardapioComponent },
  { path: 'carrinho', component: CarrinhoComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }  // Rota padrão
];