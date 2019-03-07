import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RemoverProcessoComponent } from './remover.processo.component';

const routes: Routes = [

  {
    path: 'remover/:id',
    component: RemoverProcessoComponent,
    data: {
      title: 'Remover Responsável'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RemoverProcessoRoutingModule {}
