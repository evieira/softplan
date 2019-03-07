import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SituacaoDTO} from "../../dto/situacao.dto";
import {ProcessoService} from "../../service/processo.service";
import {ProcessoDTO} from "../../dto/processo.dto";
import {ResponsavelService} from "../../service/responsavel.service";
import {ResponsavelDTO} from "../../dto/responsavel.dto";
import {forEach} from "@angular/router/src/utils/collection";
import {MensagemDTO} from "../../dto/mensagem.dto";

const moment = require("moment");

@Component({
  templateUrl: 'cadastro.processo.component.html'
})
export class CadastroProcessoComponent implements OnInit {

  submitted: boolean = false;
  mensagem: string;
  mensagemErro: string;

  idProcesso:string;
  dtDistribuicao:Date;
  processo: ProcessoDTO;
  processoPai: ProcessoDTO;
  situacaoSelecionada: SituacaoDTO;
  processosFilhos1: ProcessoDTO;
  processosFilhos2: ProcessoDTO;
  processosFilhos3: ProcessoDTO;
  processosFilhos4: ProcessoDTO;

  listaSituacao: SituacaoDTO[];
  listaResponsaveis: ResponsavelDTO[];
  listaProcessos: ProcessoDTO[];

  responsavel1: ResponsavelDTO;
  responsavel2: ResponsavelDTO;
  responsavel3: ResponsavelDTO;

  constructor(
    private route: ActivatedRoute,
    private processoService:ProcessoService,
    private responsavelService: ResponsavelService) {}


  ngOnInit() {
    this.getListaSituacao();
    this.idProcesso = this.route.snapshot.paramMap.get('id');
    this.getProcessoPorId();
  }

  getProcessoPorId(): void {
    this.responsavel1 = new ResponsavelDTO();
    this.responsavel2 = new ResponsavelDTO();
    this.responsavel3 = new ResponsavelDTO();
    this.processo = new ProcessoDTO();

    if(this.idProcesso != null) {
      this.processoService.getProcessoPorId(this.idProcesso)
        .subscribe((response) => {
          this.processo = response;
          this.dtDistribuicao = new Date(this.processo.dtDistribuicao);
          for(let i=0; i < this.listaSituacao.length; i++) {
            if(this.listaSituacao[i].descricao == this.processo.situacao) {
              this.situacaoSelecionada = this.listaSituacao[i];
            }
          }
          if(this.processo.processoFilho != null) {
            this.processosFilhos1 = this.processo.processoFilho;
            if(this.processosFilhos1.processoFilho != null) {
              this.processosFilhos2 = this.processosFilhos1.processoFilho;
              if(this.processosFilhos2 != null) {
                this.processosFilhos3 = this.processosFilhos2.processoFilho;
              }
              if(this.processosFilhos3 != null) {
                this.processosFilhos4 = this.processosFilhos3.processoFilho;
              }
            }
          }
          if(this.processo.processoPai != null) {
            this.processoPai = this.processo.processoPai;
          }
          if(this.processo.listaResponsavel.length > 0) {
            this.responsavel1 = this.processo.listaResponsavel[0];
          }
          if(this.processo.listaResponsavel.length > 1) {
            this.responsavel2 = this.processo.listaResponsavel[1];
          }
          if(this.processo.listaResponsavel.length > 2) {
            this.responsavel3 = this.processo.listaResponsavel[2];
          }
        });
    }
  }

  getListaSituacao(): void {
    this.processoService.getListaSituacao()
      .subscribe((response) => {
        this.listaSituacao = response;
        this.situacaoSelecionada = {descricao: "Em andamento", valor: "EM_ANDAMENTO"}
      });
  }

  getListaResponsaveis(event): void {
    let query = event.query;
    this.responsavelService.getResponsaveisPorNome(query)
      .subscribe((response) => {
        this.listaResponsaveis = response;
      })
  }

  getListaProcessos(event): void {
    let query = event.query;
    this.processoService.getListaProcessosPorNumeroProcesso(query)
      .subscribe((response) => {
        this.listaProcessos = response;
      })
  }

  salvarProcesso():void {
    this.submitted = true;
    this.mensagem = null;
    this.mensagemErro = null;
    let dtDistribuicao = this.dtDistribuicao == undefined ? null : moment(this.dtDistribuicao).format("YYYY-MM-DD");

    this.processo.dtDistribuicao = dtDistribuicao;
    this.processo.situacao = this.situacaoSelecionada.valor;
    this.processo.listaResponsavel = [];
    this.processo.processoPai = this.processoPai;
    if(this.responsavel1.id > 0){
      this.processo.listaResponsavel.push(this.responsavel1);
    }
    if(this.responsavel2.id > 0){
      this.processo.listaResponsavel.push(this.responsavel2);
    }
    if(this.responsavel3.id > 0){
      this.processo.listaResponsavel.push(this.responsavel3);
    }
    console.log(this.processo);
    this.processoService.salvarProcesso(this.processo)
      .subscribe((response) => {
        let mensagem: MensagemDTO;
        mensagem = response;
        this.mensagem = mensagem.mensagem;
        this.processo = new ProcessoDTO();
        this.dtDistribuicao = null;
        this.responsavel1 = new ResponsavelDTO();
        this.responsavel2 = new ResponsavelDTO();
        this.responsavel3 = new ResponsavelDTO();
        this.processoPai = new ProcessoDTO();
        this.processosFilhos1 = null;
        this.processosFilhos2 = null;
        this.processosFilhos3 = null;
        this.processosFilhos4 = null;
      },(error => {
        this.mensagemErro = error.error.mensagem;
      }));


  }
}
