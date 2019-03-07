import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';

import { DashboardComponent } from './dashboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';

import {CalendarModule} from 'primeng/calendar';
import {InputTextModule} from 'primeng/inputtext';
import {CheckboxModule} from 'primeng/checkbox';
import {DropdownModule} from 'primeng/dropdown';
import {ButtonModule} from 'primeng/button';
import {InputMaskModule} from 'primeng/inputmask';
import {TableModule} from 'primeng/table';

@NgModule({
  imports: [
    FormsModule,
    DashboardRoutingModule,
    CommonModule,
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
  declarations: [ DashboardComponent ]
})
export class DashboardModule { }
