import { Component, OnInit } from '@angular/core';
import { ClientesService } from '../../shared/service/clientes.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from '../../shared/model/cliente';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cliente-detalhe',
  templateUrl: './cliente-detalhe.component.html',
  styleUrl: './cliente-detalhe.component.scss',
})
export class ClienteDetalheComponent implements OnInit {
  constructor(
    private service: ClientesService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  public cliente: Cliente = new Cliente();
  public idCliente: number;

  ngOnInit(): void {
    this.hasIdCliente();
  }

  public salvarNovoCliente() {
    this.service.salvar(this.cliente).subscribe(
      (resultado) => {
        this.cliente = resultado;
        Swal.fire('Cliente salvo com sucesso!').then((resultado) => {
          if (resultado.isConfirmed) {
            this.cliente = new Cliente();
          } else if (resultado.dismiss === Swal.DismissReason.cancel) {
            this.voltar();
          }
        });
      },
      (erro) => {
        Swal.fire({
          icon: 'error',
          title: 'Erro ao salvar novo cliente',
          text: 'Erro ao salvar novo cliente.' + erro.error.mensagem,
        });
      }
    );
  }

  public editarCliente(): void {
    this.service.editar(this.cliente).subscribe(
      (r) => {
        Swal.fire('Cliente atualizado com sucesso!', '', 'success');
        this.voltar();
      },
      (e) => {
        Swal.fire('Erro ao atualizar cliente: ' + e.error.mensagem, 'error');
      }
    );
  }

  private hasIdCliente(): void {
    this.route.params.subscribe((params) => {
      this.idCliente = params['id'];
      if (this.idCliente) {
        this.consultarClienteId(this.idCliente);
      }
    });
  }

  public salvar() {
    if (this.idCliente) {
      this.editarCliente();
    } else {
      this.salvarNovoCliente();
    }
  }

  public consultarClienteId(idCliente: number): void {
    this.service.consultarClienteId(idCliente).subscribe(
      (cliente) => {
        this.cliente = cliente; // Atribui o cliente recebido à propriedade cliente
      },
      (erro) => {
        Swal.fire('Erro ao consultar cliente.', erro, 'error');
      }
    );
  }

  public voltar(): void {
    this.router.navigate(['/clientes']);
  }

  public compareById(r1: any, r2: any): boolean {
    return r1 && r2 ? r1.id === r2.id : r1 === r2;
  }
}
