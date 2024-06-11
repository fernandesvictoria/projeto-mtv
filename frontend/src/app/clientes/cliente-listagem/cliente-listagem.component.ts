import { Component, OnInit } from '@angular/core';
import { ClientesService } from '../../shared/service/clientes.service';
import { Cliente } from '../../shared/model/cliente';
import Swal from 'sweetalert2';
import { ClienteSeletor } from '../../shared/seletor/cliente.seletor';

@Component({
  selector: 'app-cliente-listagem',
  templateUrl: './cliente-listagem.component.html',
  styleUrl: './cliente-listagem.component.scss',
})
export class ClienteListagemComponent implements OnInit {
  constructor(private clienteService: ClientesService) {}

  public clientes: Array<Cliente> = [];
  public seletor: ClienteSeletor = new ClienteSeletor();

  ngOnInit(): void {
    this.consultarTodosClientes();
  }
  public consultarTodosClientes(): void {
    this.clienteService.consultarTodosClientes().subscribe(
      (resultado) => {
        this.clientes = resultado;
      },
      (erro) => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao consultar todas os clientes.: ' + erro.error.mensagem,
          icon: 'error',
        });
      }
    );
  }

  public limpar() {
    this.seletor = new ClienteSeletor();
  }

  public pesquisar() {
    this.clienteService.listarComSeletor(this.seletor).subscribe(
      (resultado) => {
        this.clientes = resultado;
      },
      (erro) => {
        console.error('Erro ao consultar clientes', erro);
      }
    );
  }
}
