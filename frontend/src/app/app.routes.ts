import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'queimas', pathMatch: 'full' },
  {
    path: 'queimas',
    loadChildren: () =>
      import('./queimas/queimas.module').then((m) => m.QueimasModule),
  }
];
