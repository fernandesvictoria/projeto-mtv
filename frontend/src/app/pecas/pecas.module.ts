import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PecasRoutingModule } from './pecas-routing.module';
import { PecaListagemComponent } from './peca-listagem/peca-listagem.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PecaDetalheComponent } from './peca-detalhe/peca-detalhe.component';
import { HomeComponent } from '../home/home/home.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [PecaListagemComponent, PecaDetalheComponent],
  imports: [CommonModule, PecasRoutingModule, FormsModule, SharedModule],
})
export class PecasModule {}
