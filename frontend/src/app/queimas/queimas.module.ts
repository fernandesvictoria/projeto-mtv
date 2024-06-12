import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QueimasRoutingModule } from './queimas-routing.module';
import { QueimaListagemComponent } from './queima-listagem/queima-listagem.component';

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


