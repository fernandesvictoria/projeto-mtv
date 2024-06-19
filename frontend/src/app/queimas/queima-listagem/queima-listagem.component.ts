import { Component, OnInit } from '@angular/core';
import { QueimasService } from '../../shared/service/queimas.service';
import { Queima } from '../../shared/model/queima';
import Swal from 'sweetalert2';
import { QueimaSeletor } from '../../shared/seletor/queima.seletor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-queima-listagem',
  templateUrl: './queima-listagem.component.html',
  styleUrls: ['./queima-listagem.component.scss'],
})
export class QueimaListagemComponent implements OnInit {
  public queimas: Array<Queima> = [];
  public seletor: QueimaSeletor = new QueimaSeletor();

  constructor(private queimaService: QueimasService, private router: Router) {}

  ngOnInit(): void {
    this.consultarTodasQueimas();
  }

  public consultarTodasQueimas(): void {
    this.queimaService.consultarTodasQueimas().subscribe(
      (resultado) => {
        this.queimas = resultado;
      },
      (erro) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao consultar todas as queimas: ' + erro.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  public limpar() {
    this.seletor = new QueimaSeletor();
    this.consultarTodasQueimas();
  }

  public pesquisar() {
    this.queimaService.filtrarQueimas(this.seletor).subscribe(
      (resultado) => {
        this.queimas = resultado;
      },
      (erro) => {
        console.error('Erro ao consultar queimas', erro);
      }
    );
  }

  public editar(idQueima: number): void {
    this.router.navigate(['/queimas/detalhe/', idQueima]);
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
    }).then((result) => {
      if (result.isConfirmed) {
        this.queimaService.excluirQueima(id).subscribe(
          (r) => {
            Swal.fire({
              title: 'Excluída!',
              text: 'A queima foi excluída com sucesso!',
              icon: 'success',
            });
            this.consultarTodasQueimas();
          },
          (erro) => {
            Swal.fire({
              title: 'Atenção!',
              text: 'Erro ao excluir queima: ' + erro.error.mensagem,
              icon: 'error',
            });
          }
        );
      }
    });
  }

  trackById(index: number, queima: Queima): number {
    return queima.idQueima;
  }
}
