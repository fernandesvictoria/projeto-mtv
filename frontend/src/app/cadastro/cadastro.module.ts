import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CadastroRoutingModule } from './cadastro-routing.module';
import { FormsModule } from '@angular/forms';
import { CadastroDetalheComponent } from './cadastro-detalhe/cadastro-detalhe.component';


@NgModule({
  declarations: [CadastroDetalheComponent],
  imports: [
    CommonModule,
    CadastroRoutingModule,
    FormsModule,
  ]
})
export class CadastroModule { }
