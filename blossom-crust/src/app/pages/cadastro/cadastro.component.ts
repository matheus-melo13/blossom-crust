import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { HeaderComponent } from '../../components/header/header.component';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [HeaderComponent, ReactiveFormsModule, RouterModule],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.scss'
})
export class CadastroComponent implements OnInit{

  public tipo!: string;
  formRegistro!: FormGroup

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ){
    this.createForm();
  }

  ngOnInit() {
    this.route.data.subscribe(data => {
      this.tipo = data['tipo'];
    })
  }

  private createForm() {
    this.formRegistro = this.formBuilder.group({
      nome: new FormControl(''),
      endereco: new FormControl(''),
      dataNascimento: new FormControl(''),
      telefone: new FormControl(''),
      email: new FormControl(''),
    })
  }

  fazerCadastro() {
    
  }
}
