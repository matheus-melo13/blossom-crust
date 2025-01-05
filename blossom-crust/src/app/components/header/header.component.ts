import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'header-component',
  standalone: true,
  imports: [RouterModule],  
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  
  constructor(
    private router: Router
  ) {}

  navigateTo(path: string): void {
    this.router.navigate([path]);
  }
}
