import { Component, OnInit } from '@angular/core';
import { Tipo } from '../../shared/model/tipo';
import { TipoServiceService } from '../../shared/service/tipos.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tipo-detalhe',
  templateUrl: './tipo-detalhe.component.html',
  styleUrl: './tipo-detalhe.component.scss'
})
export class TipoDetalheComponent implements OnInit{

  public tipos: Array<Tipo> = [];
  public tipo: Tipo = new Tipo();
  public idTipo: number;

  constructor (
    private tipoService: TipoServiceService,
    private router: Router,
  ){}

  ngOnInit(): void {
    this.consultarTodosTipos();

  }

  public consultarTodosTipos(): void {
    this.tipoService.consultarTodosTipos().subscribe(
      (r) => {
        this.tipos = r;
      },
      (e) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao consultar todos os tipos: ' + e.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  public salvarNovoTipo(): void {
    this.tipoService.salvarTipo(this.tipo).subscribe(
      (r) => {
        this.tipo = r;
        Swal.fire({
          icon: 'success',
          text: 'Tipo salvo com sucesso!',
          showCancelButton: true,
          confirmButtonText: 'Novo tipo',
          cancelButtonText: 'Voltar',
        }).then((resultado) => {
          if (resultado.isConfirmed) {
            this.consultarTodosTipos();
            this.tipo = new Tipo();
          } else if (resultado.dismiss === Swal.DismissReason.cancel) {
            this.voltar();
          }
        });
      },
      (e) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao salvar tipo: ' + e.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  public excluir(id: number): void {
    Swal.fire({
      title: 'Você deseja excluir?',
      text: 'Não será possível reverter a exclusão!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Sim, continue!',
    }).then((resultado) => {
      if (resultado.isConfirmed) {
        this.tipoService.excluirTipo(id).subscribe(
          (r) => {
            Swal.fire({
              title: 'Excluído!',
              text: 'O tipo foi excluído com sucesso!',
              icon: 'success',
            });
            this.consultarTodosTipos();
          },
          (erro) => {
            Swal.fire({
              title: 'Atenção!',
              text: 'Erro ao excluir tipo: ' + erro.error.mensagem,
              icon: 'error',
            });
          }
        );
      }
    });
  }

  public voltar(): void {
    this.router.navigate(['/pecas/']);
  }

}
