import { Component, OnInit } from '@angular/core';
import { QueimasService } from '../../shared/service/queimas.service';
import { Queima } from '../../shared/model/queima';
import Swal from 'sweetalert2';
import { QueimaSeletor } from '../../shared/seletor/queima.seletor';

@Component({
  selector: 'app-queima-listagem',
  templateUrl: './queima-listagem.component.html',
  styleUrls: ['./queima-listagem.component.scss'],
})
export class QueimaListagemComponent implements OnInit {
  constructor(private queimaService: QueimasService) {}

  public queimas: Array<Queima> = [];
  public seletor: QueimaSeletor = new QueimaSeletor();

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

  trackById(index: number, queima: Queima): number {
    return queima.idQueima;
  }
}
