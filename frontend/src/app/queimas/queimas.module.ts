import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Importar FormsModule
import { HttpClientModule } from '@angular/common/http';

import { QueimaListagemComponent } from './queima-listagem/queima-listagem.component'; // Importar o componente da lista de queimas
import { QueimasRoutingModule } from './queimas-routing.module';

@NgModule({
  declarations: [
    QueimaListagemComponent
  ],
  imports: [
    CommonModule,
    QueimasRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
})
export class QueimasModule { }

