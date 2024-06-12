import { Component, OnInit } from '@angular/core';
import { ClientesService } from '../../shared/service/clientes.service';
import { Router } from '@angular/router';
import { Cliente } from '../../shared/model/cliente';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cliente-detalhe',
  templateUrl: './cliente-detalhe.component.html',
  styleUrl: './cliente-detalhe.component.scss',
})
export class ClienteDetalheComponent implements OnInit {
  constructor(private service: ClientesService, private router: Router) {}


  public cliente: Cliente = new Cliente();
  
  ngOnInit(): void {}

  public salvarNovoCliente() {
    this.service.salvar(this.cliente).subscribe(
      (resultado) => {
        this.cliente = resultado;
        Swal.fire('Cliente salvo com sucesso!');
      },
      (erro) => {
        Swal.fire({
          icon: 'error',
          text: 'Erro ao salvar novo cliente.',
        });
        console.error('Erro ao salvar novo cliente.', erro);
      }
    );
  }

  private editarCliente(): void {
    this.service.editar(this.cliente).subscribe(
      (resposta) => {
      Swal.fire('Cliente atualizado com sucesso!', '', 'success');
      this.voltar();
    },
    (erro) => {
      Swal.fire('Erro ao atualizar cliente: ' + erro.error.mensagem, 'error')
    }
  );
  }

  public voltar(): void {
    this.router.navigate(['/clientes']);
  }
}
