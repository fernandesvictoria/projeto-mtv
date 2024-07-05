import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full' },

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
  },

  {
    path: 'login',
    loadChildren: () =>
      import('./login/login.module').then((m) => m.LoginModule),
  },

  {
    path: 'cadastro',
    loadChildren: () =>
      import('./cadastro/cadastro.module').then((m) => m.CadastroModule),
  },
  // {
  //   path: 'home',
  //   loadChildren: () =>
  //     import('./home/home.module').then((m) => m.HomeModule),
  // }
];
