import { Component } from '@angular/core';
import { Usuario } from '../../shared/model/usuario';
import { LoginService } from '../../shared/service/login.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cadastro-detalhe',
  templateUrl: './cadastro-detalhe.component.html',
  styleUrl: './cadastro-detalhe.component.scss'
})
export class CadastroDetalheComponent {

  public usuario: Usuario = new Usuario();

  constructor(
    private service: LoginService,
    private router: Router,
    private route: ActivatedRoute
  ){}

  public salvar() {
    this.service.salvar(this.usuario).subscribe(
      (resultado) => {
        this.usuario = resultado;
        Swal.fire('Usuário salvo com sucesso!').then((resultado) => {
          if (resultado.isConfirmed) {
            this.usuario = new Usuario();
          } else if (resultado.dismiss === Swal.DismissReason.cancel) {
            this.voltar();
          }
        });
      },
      (erro) => {
        Swal.fire({
          icon: 'error',
          title: 'Erro ao salvar novo usuário',
          text: 'Erro ao salvar novo usuário.' + erro.error.mensagem,
        });
      }
    );
  }

  public voltar(): void {
    this.router.navigate(['/login']);
  }

}
