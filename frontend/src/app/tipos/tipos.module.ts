import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TiposRoutingModule } from './tipos-routing.module';
import { TipoDetalheComponent } from './tipo-detalhe/tipo-detalhe.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    TipoDetalheComponent,
  ],
  imports: [
    CommonModule,
    TiposRoutingModule,
    FormsModule
  ]
})
export class TiposModule { }
