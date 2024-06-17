import { Component, OnInit } from '@angular/core';
import { PecasService } from '../../shared/service/pecas.service';
import { Peca } from '../../shared/model/peca';
import Swal from 'sweetalert2';
import { PecaSeletor } from '../../shared/seletor/peca.seletor';
import { Cliente } from '../../shared/model/cliente';
import { EstagioPeca } from '../../shared/model/enum/estagio-peca';
import { ClientesService } from '../../shared/service/clientes.service';
import { Tipo } from '../../shared/model/tipo';
import { TipoServiceService } from '../../shared/service/tipo-service.service';

@Component({
  selector: 'app-peca-listagem',
  templateUrl: './peca-listagem.component.html',
  styleUrl: './peca-listagem.component.scss',
})
export class PecaListagemComponent implements OnInit {
  public pecas: Array<Peca> = [];
  public estagios: Array<string> = [];
  public clientes: Array<Cliente> = [];
  public tipos: Array<Tipo> = [];
  public seletor = new PecaSeletor();

  constructor(
    private pecaService: PecasService,
    private clienteService: ClientesService,
    private tipoService: TipoServiceService,
  ) {}

  ngOnInit(): void {
    this.consultarTodasPecas();
    this.carregarEstagios();
    this.consultarTodosClientes();
    this.consultarTodosTipos();
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

  public consultarTodasPecas(): void {
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

  public consultarPecasComFiltros(): void {
    this.pecaService.consultarPecasComFiltros(this.seletor).subscribe(
      (r) => {
        this.pecas = r;
      },
      (e) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao filtrar as peças: ' + e.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  public limpar(): void {
    this.consultarTodasPecas();
  }
}
