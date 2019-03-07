import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent } from './containers';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },
  {
    path: '404',
    component: P404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: P500Component,
    data: {
      title: 'Page 500'
    }
  },
  {
    path: '',
    component: DefaultLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'dashboard',
        loadChildren: './views/dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'cadastroProcesso',
        loadChildren: './views/cadastro.processo/cadastro.processo.module#CadastroProcessoModule'
      },
      {
        path: 'responsavel',
        loadChildren: './views/responsavel/responsavel.module#ResponsavelModule'
      },
      {
        path: 'cadastroResponsavel',
        loadChildren: './views/cadastro.responsavel/cadastro.responsavel.module#CadastroResponsavelModule'
      },
      {
        path: 'removerResponsavel',
        loadChildren: './views/remover.responsavel/remover.responsavel.module#RemoverResponsavelModule'
      },
      {
        path: 'removerProcesso',
        loadChildren: './views/remover.processo/remover.processo.module#RemoverProcessoModule'
      }
    ]
  },
  { path: '**', component: P404Component }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
