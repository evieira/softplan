import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';

import { CadastroProcessoComponent } from './cadastro.processo.component';
import { CadastroProcessoRoutingModule } from './cadastro.processo-routing.module';

import {CalendarModule} from 'primeng/calendar';
import {InputTextModule} from 'primeng/inputtext';
import {CheckboxModule} from 'primeng/checkbox';
import {DropdownModule} from 'primeng/dropdown';
import {ButtonModule} from 'primeng/button';
import {InputMaskModule} from 'primeng/inputmask';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {AutoCompleteModule} from 'primeng/autocomplete';

@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    CadastroProcessoRoutingModule,
    ChartsModule,
    BsDropdownModule,
    CalendarModule,
    InputTextModule,
    CheckboxModule,
    DropdownModule,
    ButtonModule,
    InputMaskModule,
    InputTextareaModule,
    AutoCompleteModule,
    ButtonsModule.forRoot()
  ],
  declarations: [ CadastroProcessoComponent ]
})
export class CadastroProcessoModule { }
