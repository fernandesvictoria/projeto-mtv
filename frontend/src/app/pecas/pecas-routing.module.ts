import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PecaListagemComponent } from './peca-listagem/peca-listagem.component';
import { PecaDetalheComponent } from './peca-detalhe/peca-detalhe.component';

const routes: Routes = [
  {path: '', component: PecaListagemComponent},
  {path: 'detalhe', component: PecaDetalheComponent},
  {path: 'detalhe/:id', component: PecaDetalheComponent},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PecasRoutingModule { }
