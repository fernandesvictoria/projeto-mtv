import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PecasRoutingModule } from './pecas-routing.module';
import { PecaListagemComponent } from './peca-listagem/peca-listagem.component';


@NgModule({
  declarations: [
    PecaListagemComponent
  ],
  imports: [
    CommonModule,
    PecasRoutingModule
  ]
})
export class PecasModule { }
