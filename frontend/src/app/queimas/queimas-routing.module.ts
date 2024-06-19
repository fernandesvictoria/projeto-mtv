import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QueimaListagemComponent } from './queima-listagem/queima-listagem.component';
import { QueimaDetalheComponent } from './queima-detalhe/queima-detalhe.component';

const routes: Routes = [
  { path: '', component: QueimaListagemComponent },
  { path: 'detalhe', component: QueimaDetalheComponent },
  { path: 'detalhe/:id', component: QueimaDetalheComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QueimasRoutingModule { }
