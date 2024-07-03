import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ClienteListagemComponent } from '../clientes/cliente-listagem/cliente-listagem.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: 'clientes',
        loadChildren: () =>
          import('../clientes/clientes.module').then((m) => m.ClientesModule),
      },
      {
        path: 'pecas',
        loadChildren: () =>
          import('../pecas/pecas.module').then((m) => m.PecasModule),
      },
      {
        path: 'queimas',
        loadChildren: () =>
          import('../queimas/queimas.module').then((m) => m.QueimasModule),
      },
      {
        path: 'tipos',
        loadChildren: () =>
          import('../tipos/tipos.module').then((m) => m.TiposModule),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
