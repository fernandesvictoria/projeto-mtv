import { Component, OnInit } from '@angular/core';

export interface Queima {
  idQueima: number;
  dataQueima: Date;
  tipoQueima: string;
  temperaturaAlcancada: number;
  peca: string;
  forno: string;
  precoQueima: number;
  pago: boolean;
}

@Component({
  selector: 'app-queima-listagem',
  templateUrl: './queima-listagem.component.html',
  styleUrl: './queima-listagem.component.scss'
})

export class QueimaListagemComponent implements OnInit {

  public queimas: Queima[] = [
    {
      idQueima: 1,
      dataQueima: new Date('2024-06-01'),
      tipoQueima: 'Queima Rápida',
      temperaturaAlcancada: 800,
      peca: 'Vaso de cerâmica',
      forno: 'Forno A',
      precoQueima: 50.00,
      pago: true
    },
    {
      idQueima: 2,
      dataQueima: new Date('2024-05-25'),
      tipoQueima: 'Queima Lenta',
      temperaturaAlcancada: 1200,
      peca: 'Estátua de bronze',
      forno: 'Forno B',
      precoQueima: 120.00,
      pago: false
    },
    {
      idQueima: 3,
      dataQueima: new Date('2024-06-05'),
      tipoQueima: 'Queima Normal',
      temperaturaAlcancada: 1000,
      peca: 'Prato de porcelana',
      forno: 'Forno C',
      precoQueima: 30.00,
      pago: true
    }]

  constructor() { }

  ngOnInit(): void {
  }
}
