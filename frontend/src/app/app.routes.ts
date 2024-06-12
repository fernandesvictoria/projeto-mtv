import { Routes } from '@angular/router';

export const routes: Routes = [

  { path: '', redirectTo: 'pecas', pathMatch: 'full' },
  {
    path: 'pecas',
    loadChildren: () =>
      import('./pecas/pecas.module').then((m) => m.PecasModule)
  },

  { path: '', redirectTo: 'clientes', pathMatch: 'full' },
  {
    path: 'clientes',
    loadChildren: () =>
      import('./clientes/clientes.module').then((m) => m.ClientesModule)
  },
  
  // { path: '', redirectTo: 'queimas', pathMatch: 'full' },
  {
    path: 'queimas',
    loadChildren: () =>
      import('./queimas/queimas.module').then((m) => m.QueimasModule),
  }

];
