import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientesRoutingModule } from './clientes-routing.module';
import { ClienteListagemComponent } from './cliente-listagem/cliente-listagem.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ClienteDetalheComponent } from './cliente-detalhe/cliente-detalhe.component';


@NgModule({
  declarations: [
    ClienteListagemComponent, ClienteDetalheComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ClientesRoutingModule,
    FormsModule,

  ]
})
export class ClientesModule { }
