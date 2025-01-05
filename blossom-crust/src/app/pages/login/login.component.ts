import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from '../../components/header/header.component';

@Component({
  selector: 'login-component',
  standalone: true,
  imports: [HeaderComponent, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']  // Corrigido para "styleUrls" (plural)
})
export class LoginComponent {

}
