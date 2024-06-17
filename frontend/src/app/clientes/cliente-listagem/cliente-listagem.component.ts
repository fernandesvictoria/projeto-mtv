import { Component, OnInit } from '@angular/core';
import { ClientesService } from '../../shared/service/clientes.service';
import { Cliente } from '../../shared/model/cliente';
import Swal from 'sweetalert2';
import { ClienteSeletor } from '../../shared/seletor/cliente.seletor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cliente-listagem',
  templateUrl: './cliente-listagem.component.html',
  styleUrl: './cliente-listagem.component.scss',
})
export class ClienteListagemComponent implements OnInit {
  constructor(private clienteService: ClientesService,private router : Router) {}

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
          text: 'Erro ao consultar todos os clientes.: ' + erro.error.mensagem,
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


  public editar(idCliente: number): void {
    this.router.navigate(['/clientes/detalhe/', idCliente]);
  }

  public excluir(id: number) {
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
        this.clienteService.excluir(id).subscribe(
          (resultado) => {
            Swal.fire({
              title: 'Excluído!',
              text: 'Cliente excluído com sucesso!',
              icon: 'success',
            });
            this.pesquisar();
          },
          (erro) => {
            Swal.fire({
              title: 'Atenção!',
              text: 'Erro ao excluir cliente: ' + erro.error.mensagem,
              icon: 'error',
            });
          }
        );
      }
    });
  }


}
