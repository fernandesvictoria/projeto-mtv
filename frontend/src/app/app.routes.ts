import { Routes } from '@angular/router';

export const routes: Routes = [

  {
    path: 'pecas',
    loadChildren: () =>
      import('./pecas/pecas.module').then((m) => m.PecasModule)
  },

  {
    path: 'clientes',
    loadChildren: () =>
      import('./clientes/clientes.module').then((m) => m.ClientesModule)
  },

  {
    path: 'queimas',
    loadChildren: () =>
      import('./queimas/queimas.module').then((m) => m.QueimasModule),
  },

  {
    path: 'tipos',
    loadChildren: () =>
      import('./tipos/tipos.module').then((m) => m.TiposModule),
  }

];
