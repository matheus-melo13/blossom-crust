import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-fazer-login',
  standalone: true,
  imports: [HeaderComponent, RouterModule],
  templateUrl: './fazer-login.component.html',
  styleUrl: './fazer-login.component.scss'
})
export class FazerLoginComponent {

  
}
