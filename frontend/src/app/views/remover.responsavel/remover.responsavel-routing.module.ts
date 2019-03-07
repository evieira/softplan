import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RemoverResponsavelComponent } from './remover.responsavel.component';

const routes: Routes = [

  {
    path: 'remover/:id',
    component: RemoverResponsavelComponent,
    data: {
      title: 'Remover Responsável'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RemoverResponsavelRoutingModule {}
