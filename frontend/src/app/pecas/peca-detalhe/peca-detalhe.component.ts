import { Component, OnInit } from '@angular/core';
import { ClientesService } from '../../shared/service/clientes.service';
import { PecasService } from '../../shared/service/pecas.service';
import { TipoServiceService } from '../../shared/service/tipo-service.service';
import { Peca } from '../../shared/model/peca';
import Swal from 'sweetalert2';
import { Cliente } from '../../shared/model/cliente';
import { Tipo } from '../../shared/model/tipo';
import { EstagioPeca } from '../../shared/model/enum/estagio-peca';
import { Tamanho } from '../../shared/model/enum/tamanho';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-peca-detalhe',
  templateUrl: './peca-detalhe.component.html',
  styleUrl: './peca-detalhe.component.scss',
})
export class PecaDetalheComponent implements OnInit {
  public peca: Peca = new Peca();
  public pecas: Array<Peca> = [];
  public estagios: Array<string> = [];
  public tamanhos: Array<string> = [];
  public clientes: Array<Cliente> = [];
  public tipos: Array<Tipo> = [];
  public idPeca: number;

  constructor(
    private pecaService: PecasService,
    private clienteService: ClientesService,
    private tipoService: TipoServiceService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.consultarTodosClientes();
    this.carregarEstagios();
    this.consultarTodosTipos();
    this.carregarTamanhos();
    this.hasIdPeca();
  }

  private consultarTodosClientes() {
    this.clienteService.consultarTodosClientes().subscribe(
      (r) => {
        this.clientes = r;
      },
      (e) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao consultar todos os clientes: ' + e.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  private hasIdPeca(): void {
    this.route.params.subscribe((params) => {
      this.idPeca = params['id'];
      if (this.idPeca) {
        this.consultarPecaPorId();
      }
    });
  }

  private carregarEstagios(): void {
    this.estagios = Object.values(EstagioPeca).filter(
      (value) => typeof value === 'string'
    ) as string[];
  }

  private carregarTamanhos(): void {
    this.tamanhos = Object.values(Tamanho).filter(
      (value) => typeof value === 'string'
    ) as string[];
  }

  private consultarTodosTipos(): void {
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

  public salvar() {
    if (this.idPeca) {
      this.editarPeca();
    } else {
      this.salvarNovaPeca();
    }
  }

  public salvarNovaPeca(): void {
    this.pecaService.salvarPeca(this.peca).subscribe(
      (r) => {
        this.peca = r;
        Swal.fire({
          icon: 'success',
          text: 'Peça salva com sucesso!',
          showCancelButton: true,
          confirmButtonText: 'Nova peça',
          cancelButtonText: 'Voltar',
        }).then((resultado) => {
          if (resultado.isConfirmed) {
            this.peca = new Peca();
          } else if (resultado.dismiss === Swal.DismissReason.cancel) {
            this.voltar();
          }
        });
      },
      (e) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao salvar peça: ' + e.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  private editarPeca(): void {
    this.pecaService.editarPeca(this.peca).subscribe(
      (r) => {
        Swal.fire('Peça atualizada com sucesso!', '', 'success');
        this.voltar();
      },
      (e) => {
        Swal.fire('Erro ao atualizar peça: ' + e.error.mensagem, 'error');
      }
    );
  }

  private consultarPecaPorId() {
    this.pecaService.consultarPecaPorId(this.idPeca).subscribe(
      (r) => {
        this.peca = r;
      },
      (e) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao consultar todos os clientes: ' + e.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  // public compareById(r1: any, r2: any): boolean {
  //   return r1 && r2 ? r1.id === r2.id : r1 === r2;
  // }

  public compareById(item1: any, item2: any): boolean {
    if (item1 && item2) {
      if (item1.idCliente !== undefined && item2.idCliente !== undefined) {
        return item1.idCliente === item2.idCliente;
      }
      if (item1.idTipo !== undefined && item2.idTipo !== undefined) {
        return item1.idTipo === item2.idTipo;
      }
    }
    return item1 === item2;
  }

  public voltar(): void {
    this.router.navigate(['/pecas/']);
  }

}
