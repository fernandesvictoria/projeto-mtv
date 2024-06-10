import { Component, OnInit } from '@angular/core';
import { PecasService } from '../../shared/service/pecas.service';
import { Peca } from '../../shared/model/peca';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-peca-listagem',
  templateUrl: './peca-listagem.component.html',
  styleUrl: './peca-listagem.component.scss'
})
export class PecaListagemComponent implements OnInit{
  constructor(private pecaService: PecasService){ }
  public pecas: Array<Peca> = [];

  ngOnInit(): void {
    this.consultarTodasPecas();

  }

  public consultarTodasPecas(): void {
    this.pecaService.consultarTodasPecas().subscribe(
      resultado => {
        this.pecas = resultado;
      },
      erro => {
        Swal.fire({
          title: 'Erro!',
          text: 'Erro ao consultar todas as peças: ' + erro.error.mensagem,
          icon: 'error'
        });
      }
    );
  }



}
