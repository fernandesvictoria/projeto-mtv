// queima-detalhe.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { QueimasService } from '../../shared/service/queimas.service';
import { PecasService } from '../../shared/service/pecas.service';
import { Queima } from '../../shared/model/queima';
import { Peca } from '../../shared/model/peca';

@Component({
  selector: 'app-queima-detalhe',
  templateUrl: './queima-detalhe.component.html',
  styleUrls: ['./queima-detalhe.component.scss'],
})
export class QueimaDetalheComponent implements OnInit {
  public queima: Queima = new Queima();
  public pecas: Array<Peca> = [];
  public idQueima: number;

  constructor(
    private queimaService: QueimasService,
    private pecaService: PecasService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.consultarTodasPecas();
    this.hasIdQueima();
  }

  private consultarTodasPecas(): void {
    this.pecaService.consultarTodasPecas().subscribe(
      (r) => {
        this.pecas = r;
      },
      (e) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao consultar todas as peças: ' + e.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  private hasIdQueima(): void {
    this.route.params.subscribe((params) => {
      this.idQueima = params['id'];
      if (this.idQueima) {
        this.consultarQueimaPorId();
      }
    });
  }

  private consultarQueimaPorId() {
    this.queimaService.consultarQueimaPorId(this.idQueima).subscribe(
      (r) => {
        this.queima = r;
      },
      (e) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao consultar a queima: ' + e.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  public salvar() {
    if (this.idQueima) {
      this.editarQueima();
    } else {
      this.salvarNovaQueima();
    }
  }

  public salvarNovaQueima(): void {
    this.queimaService.salvarQueima(this.queima).subscribe(
      (r) => {
        this.queima = r;
        Swal.fire({
          icon: 'success',
          text: 'Queima salva com sucesso!',
          showCancelButton: true,
          confirmButtonText: 'Nova queima',
          cancelButtonText: 'Voltar',
        }).then((resultado) => {
          if (resultado.isConfirmed) {
            this.queima = new Queima();
          } else if (resultado.dismiss === Swal.DismissReason.cancel) {
            this.voltar();
          }
        });
      },
      (e) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao salvar queima: ' + e.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  private editarQueima(): void {
    this.queimaService.alterarQueima(this.queima).subscribe(
      (r) => {
        Swal.fire('Queima atualizada com sucesso!', '', 'success');
        this.voltar();
      },
      (e) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao atualizar queima: ' + e.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  public compareById(r1: any, r2: any): boolean {
    return r1 && r2 ? r1.idPeca === r2.idPeca : r1 === r2;
  }

  public voltar(): void {
    this.router.navigate(['/queimas/']);
  }

  trackById(index: number, item: any): number {
    return item.id;
  }
}
