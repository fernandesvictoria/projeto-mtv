import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientesRoutingModule } from './clientes-routing.module';
import { ClienteListagemComponent } from './cliente-listagem/cliente-listagem.component';


@NgModule({
  declarations: [
    ClienteListagemComponent
  ],
  imports: [
    CommonModule,
    ClientesRoutingModule
  ]
})
export class ClientesModule { }
