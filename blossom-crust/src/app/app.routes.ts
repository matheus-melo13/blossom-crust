import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginSelectionComponent } from './pages/login-selection/login-selection.component';
import { ClientLoginComponent } from './pages/client-login/client-login.component';

export const routes: Routes = [
  { path: '', component: HomeComponent }, // Página inicial
  { path: 'login-selection', component: LoginSelectionComponent }, // Página para escolha de login
  { path: 'client-login', component: ClientLoginComponent } // Página para login do cliente
];
