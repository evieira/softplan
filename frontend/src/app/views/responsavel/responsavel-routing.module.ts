import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ResponsavelComponent } from './responsavel.component';

const routes: Routes = [
  {
    path: '',
    component: ResponsavelComponent,
    data: {
      title: 'Consultar Responsável'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResponsavelRoutingModule {}
