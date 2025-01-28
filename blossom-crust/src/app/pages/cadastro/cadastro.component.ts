import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { HeaderComponent } from '../../components/header/header.component';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CadastroService } from './cadastro.service';
import { ClienteDTO } from '../model/cliente.model';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [HeaderComponent, ReactiveFormsModule, RouterModule],
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {

  public tipo!: string;
  formRegistro!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private cadastroService: CadastroService
  ) {
    this.createForm();
  }

  ngOnInit() {
    this.route.data.subscribe(data => {
      this.tipo = data['tipo'];
    });
  }

  private createForm() {
    this.formRegistro = this.formBuilder.group({
      nome: new FormControl('', [Validators.required, Validators.maxLength(100)]),
      endereco: new FormControl('', [Validators.required, Validators.maxLength(255)]),
      dataNascimento: new FormControl(''),
      telefone: new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(15)]),
      email: new FormControl('', [Validators.required, Validators.email, Validators.maxLength(150)]),
      senha: new FormControl('', [Validators.required])
    });
  }

  fazerCadastro() {
    if (this.formRegistro.valid) {
      const cliente: ClienteDTO = this.formRegistro.value;
      this.cadastroService.cadastrar(cliente).subscribe(response => {
        console.log('Cliente cadastrado com sucesso:', response);
      }, error => {
        console.error('Erro ao cadastrar cliente:', error);
      });
    }
  }
}
