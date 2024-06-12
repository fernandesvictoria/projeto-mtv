import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PecaListagemComponent } from './peca-listagem/peca-listagem.component';

const routes: Routes = [
  {path: "", component: PecaListagemComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PecasRoutingModule { }
