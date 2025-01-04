import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'home-component',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']  // Corrigido para "styleUrls" (plural)
})
export class HomeComponent {

}
