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
import { Router } from '@angular/router';

@Component({
  selector: 'app-peca-detalhe',
  templateUrl: './peca-detalhe.component.html',
  styleUrl: './peca-detalhe.component.scss'
})
export class PecaDetalheComponent implements OnInit{


  public peca: Peca = new Peca();
  public pecas: Array<Peca> = [];
  public estagios: Array<string> = [];
  public tamanhos: Array<string> = [];
  public clientes: Array<Cliente> = [];
  public tipos: Array<Tipo> = [];


  constructor(
    private pecaService: PecasService,
    private clienteService: ClientesService,
    private tipoService: TipoServiceService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.consultarTodosClientes();
    this.carregarEstagios();
    this.consultarTodosTipos();
    this.carregarTamanhos();
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

  private carregarEstagios(): void {
    this.estagios = Object.values(EstagioPeca).filter((value) => typeof value === 'string') as string[];
  }

  private carregarTamanhos(): void {
    this.tamanhos = Object.values(Tamanho).filter((value) => typeof value === 'string') as string[];
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

  salvar() {
    // if (this.idVacina) {
    //   this.editarVacina();
    // } else {
      this.salvarNovaPeca();
    }

  public salvarNovaPeca(): void {
    this.pecaService.salvarPeca(this.peca).subscribe(
      (r) => {
        this.peca = r;
        Swal.fire({
          icon: 'success',
          text: 'Peça salva com sucesso!',
          showCancelButton: true,
          confirmButtonText:'Nova Peça',
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

  public compareById (r1: any, r2: any): boolean {
    return r1 && r2 ? r1.id === r2.id : r1 === r2;
  }
  public voltar(): void {
    this.router.navigate(['/pecas/']);
  }
}
