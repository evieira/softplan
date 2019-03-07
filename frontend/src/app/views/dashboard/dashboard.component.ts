import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {SituacaoDTO} from "../../dto/situacao.dto";
import {ProcessoService} from "../../service/processo.service";
import {ProcessoDTO} from "../../dto/processo.dto";


const moment = require("moment");

@Component({
  templateUrl: 'dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  numeroProcesso: string;
  dtInicio: Date;
  dtFim: Date;
  situacaoSelecionada: SituacaoDTO;
  segredoJustica: boolean;
  pastaFisicaCliente: string;
  responsavel: string;

  listaSituacao: SituacaoDTO[];
  listaProcesso: ProcessoDTO[];


  constructor(private processoService:ProcessoService) {}

  ngOnInit(): void {
    this.getListaSituacao();
  }

  getListaSituacao(): void {
    this.processoService.getListaSituacao()
      .subscribe((response) => {
        this.listaSituacao = response;
        this.situacaoSelecionada = {descricao: "Em andamento", valor: "EM_ANDAMENTO"}
      });
  }

  getListaProcessos(): void {
    let dtFim:Date;
    let dtInicio:Date;
    this.numeroProcesso = this.numeroProcesso != '' ? this.numeroProcesso : undefined;
    this.pastaFisicaCliente = this.pastaFisicaCliente != '' ? this.pastaFisicaCliente : undefined;
    this.responsavel = this.responsavel != '' ? this.responsavel : undefined;
    this.dtInicio = this.dtInicio == null ? undefined : this.dtInicio;
    this.dtFim = this.dtFim == null ? undefined : this.dtFim;
    dtInicio = this.dtInicio == undefined ? this.dtInicio :  moment(this.dtInicio).format("YYYY-MM-DD");
    dtFim = this.dtFim == undefined ? this.dtFim :  moment(this.dtFim).format("YYYY-MM-DD");

    this.processoService.getListaProcessosPorFiltros(this.numeroProcesso, dtInicio, dtFim,
      this.situacaoSelecionada.valor, this.segredoJustica, this.pastaFisicaCliente, this.responsavel)
      .subscribe((response) => {
        this.listaProcesso = response;

      })
  }
}
