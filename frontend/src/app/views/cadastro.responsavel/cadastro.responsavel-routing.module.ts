import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CadastroResponsavelComponent } from './cadastro.responsavel.component';

const routes: Routes = [
  {
    path: '',
    component: CadastroResponsavelComponent,
    data: {
      title: 'Cadastrar Responsável'
    }
  },
  {
    path: 'editar/:id',
    component: CadastroResponsavelComponent,
    data: {
      title: 'Editar Responsável'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CadastroResponsavelRoutingModule {}
