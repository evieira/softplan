import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ResponsavelService } from '../../service/responsavel.service';
import { ResponsavelDTO } from '../../dto/responsavel.dto';

@Component({
  templateUrl: 'responsavel.component.html'
})
export class ResponsavelComponent implements OnInit {

  nome:string;
  cpf:string;
  numeroProcesso:string;
  listaResponsaveis: ResponsavelDTO[];

  constructor(private responsavelService:ResponsavelService) {}

  ngOnInit(): void {

  }

  getResponsaveis() {
    this.nome = this.nome != '' ? this.nome : undefined;
    this.cpf = this.cpf != '' ? this.cpf : undefined;
    this.numeroProcesso = this.numeroProcesso != '' ? this.numeroProcesso : undefined;
    this.responsavelService.getResponsaveisPorNomeCPFNumeroProcesso(this.nome, this.cpf, this.numeroProcesso)
      .subscribe((response) => {
        this.listaResponsaveis = response;
      });
  }
}
