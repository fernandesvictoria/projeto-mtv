import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { TipoQueima } from '../../shared/model/enum/tipo-queima';
import { Queima } from '../../shared/model/queima';
import { QueimaSeletor } from '../../shared/seletor/queima.seletor';
import { QueimasService } from '../../shared/service/queimas.service';

@Component({
  selector: 'app-queima-listagem',
  templateUrl: './queima-listagem.component.html',
  styleUrls: ['./queima-listagem.component.scss']
})
export class QueimaListagemComponent implements OnInit {
  queimas: Queima[] = [];
  seletor: QueimaSeletor = new QueimaSeletor();
  tiposDeQueima: string[] = [];

  constructor(private queimaService: QueimasService, private router: Router) {
    this.tiposDeQueima = this.getEnumValues(TipoQueima);
  }

  ngOnInit(): void {
    this.pesquisar();
  }

  pesquisar(): void {
    this.queimaService.filtrarQueimas(this.seletor).subscribe(
      (result: Queima[]) => {
        this.queimas = result;
      },
      (error: any) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao buscar queimas: ' + error.message,
          icon: 'error',
        });
      }
    );
  }

  limpar(): void {
    this.seletor = new QueimaSeletor();
    this.pesquisar();
  }

  editar(id: number): void {
    this.router.navigate(['/queimas/detalhe', id]);
  }

  excluir(id: number): void {
    Swal.fire({
      title: 'Tem certeza?',
      text: 'Você não poderá reverter isso!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sim, excluir!',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.queimaService.excluirQueima(id).subscribe(
          () => {
            Swal.fire('Excluído!', 'A queima foi excluída.', 'success');
            this.pesquisar();
          },
          (error) => {
            Swal.fire({
              title: 'Erro!',
              text: 'Erro ao excluir a queima: ' + error.message,
              icon: 'error',
            });
          }
        );
      }
    });
  }

  getEnumValues(enumType: any): string[] {
    return Object.keys(enumType).filter(key => isNaN(Number(key)));
  }

  trackById(index: number, item: Queima): number {
    return item.idQueima;
  }
}
