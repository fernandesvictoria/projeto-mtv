import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { QueimasRoutingModule } from './queimas-routing.module';
import { QueimaListagemComponent } from './queima-listagem/queima-listagem.component';
import { QueimaDetalheComponent } from './queima-detalhe/queima-detalhe.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    QueimaListagemComponent,
    QueimaDetalheComponent
  ],
  imports: [
    CommonModule,
    QueimasRoutingModule,
    FormsModule,
    HttpClientModule,
    SharedModule
  ],
})
export class QueimasModule { }

