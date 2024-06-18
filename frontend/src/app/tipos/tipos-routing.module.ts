import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TipoDetalheComponent } from './tipo-detalhe/tipo-detalhe.component';

const routes: Routes = [
  {path: 'detalhe', component: TipoDetalheComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TiposRoutingModule { }
