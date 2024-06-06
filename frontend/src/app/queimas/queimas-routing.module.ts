import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QueimaListagemComponent } from './queima-listagem/queima-listagem.component';

const routes: Routes = [
  { path: "", component: QueimaListagemComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QueimasRoutingModule { }
