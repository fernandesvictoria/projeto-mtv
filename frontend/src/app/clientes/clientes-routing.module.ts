import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteListagemComponent } from './cliente-listagem/cliente-listagem.component';
import { ClienteDetalheComponent } from './cliente-detalhe/cliente-detalhe.component';

const routes: Routes = [
  { path: '', component: ClienteListagemComponent },
  { path: 'detalhe/:id', component: ClienteDetalheComponent },
  { path: 'detalhe', component: ClienteDetalheComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClientesRoutingModule {}
