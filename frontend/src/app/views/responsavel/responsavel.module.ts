import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';

import { ResponsavelComponent } from './responsavel.component';
import { ResponsavelRoutingModule } from './responsavel-routing.module';
import { ResponsavelService } from '../../service/responsavel.service';

import {CalendarModule} from 'primeng/calendar';
import {InputTextModule} from 'primeng/inputtext';
import {CheckboxModule} from 'primeng/checkbox';
import {DropdownModule} from 'primeng/dropdown';
import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';
import {InputMaskModule} from 'primeng/inputmask';

@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    ResponsavelRoutingModule,
    ChartsModule,
    HttpClientModule,
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
  declarations: [ ResponsavelComponent ],
  providers: [ ResponsavelService ]
})
export class ResponsavelModule { }
