import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';

import { RemoverProcessoComponent } from './remover.processo.component';
import { RemoverProcessoRoutingModule } from './remover.processo-routing.module';

import {CalendarModule} from 'primeng/calendar';
import {InputTextModule} from 'primeng/inputtext';
import {CheckboxModule} from 'primeng/checkbox';
import {DropdownModule} from 'primeng/dropdown';
import {ButtonModule} from 'primeng/button';
import {InputMaskModule} from 'primeng/inputmask';

@NgModule({
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RemoverProcessoRoutingModule,
    ChartsModule,
    BsDropdownModule,
    CalendarModule,
    InputTextModule,
    CheckboxModule,
    DropdownModule,
    ButtonModule,
    InputMaskModule,
    ButtonsModule.forRoot()
  ],
  declarations: [ RemoverProcessoComponent ]
})
export class RemoverProcessoModule { }
