import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QueimasRoutingModule } from './vacinas-routing.module';
import { QueimaListagemComponent } from './vacina-listagem/vacina-listagem.component';

@NgModule({
  declarations: [
    QueimaListagemComponent
  ],
  imports: [
    CommonModule,
    QueimasRoutingModule
  ]
})
export class QueimasModule { }


