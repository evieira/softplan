import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CadastroProcessoComponent } from './cadastro.processo.component';

const routes: Routes = [
  {
    path: '',
    component: CadastroProcessoComponent,
    data: {
      title: 'Cadastrar Processo'
    }
  },
  {
    path: 'editar/:id',
    component: CadastroProcessoComponent,
    data: {
      title: 'Editar Processo'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CadastroProcessoRoutingModule {}
