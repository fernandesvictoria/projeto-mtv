import { Component } from '@angular/core';
import { LoginService } from '../../shared/service/login.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Usuario } from '../../shared/model/usuario';
import { UsuarioDTO } from '../../shared/model/usuario-dto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  public dto: UsuarioDTO = new UsuarioDTO();

  constructor(
    private loginService: LoginService,
    private router: Router,
  ) {}

  public login () {
    this.loginService.autenticar(this.dto).subscribe(
      (usuarioAutenticado: Usuario) => {
        Swal.fire('Usuário autenticado com sucesso!', '', 'success')
        localStorage.setItem('usuarioASutenticado', JSON.stringify(usuarioAutenticado));
        this.router.navigate(['']);
      },
      (e) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao realizar login: ' + e.error.mensagem,
          icon: 'error',
        });

      }
    )
  }

}
