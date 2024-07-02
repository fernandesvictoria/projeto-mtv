import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroDetalheComponent } from './cadastro-detalhe/cadastro-detalhe.component';

const routes: Routes = [
  {path: '', component: CadastroDetalheComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CadastroRoutingModule { }
