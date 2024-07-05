import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TiposRoutingModule } from './tipos-routing.module';
import { TipoDetalheComponent } from './tipo-detalhe/tipo-detalhe.component';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    TipoDetalheComponent,
  ],
  imports: [
    CommonModule,
    TiposRoutingModule,
    FormsModule,
    SharedModule
  ]
})
export class TiposModule { }
