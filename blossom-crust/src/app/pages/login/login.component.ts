import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'login-component',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']  // Corrigido para "styleUrls" (plural)
})
export class LoginComponent {

}
