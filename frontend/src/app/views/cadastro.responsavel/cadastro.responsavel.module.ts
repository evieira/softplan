import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';

import { CadastroResponsavelComponent } from './cadastro.responsavel.component';
import { CadastroResponsavelRoutingModule } from './cadastro.responsavel-routing.module';

import {CalendarModule} from 'primeng/calendar';
import {InputTextModule} from 'primeng/inputtext';
import {CheckboxModule} from 'primeng/checkbox';
import {DropdownModule} from 'primeng/dropdown';
import {ButtonModule} from 'primeng/button';
import {InputMaskModule} from 'primeng/inputmask';
import {TableModule} from 'primeng/table';

@NgModule({
  imports: [
    ReactiveFormsModule,
    CommonModule,
    CadastroResponsavelRoutingModule,
    ChartsModule,
    BsDropdownModule,
    CalendarModule,
    InputTextModule,
    CheckboxModule,
    DropdownModule,
    ButtonModule,
    InputMaskModule,
    TableModule,
    ButtonsModule.forRoot()
  ],
  declarations: [ CadastroResponsavelComponent ]
})
export class CadastroResponsavelModule { }
