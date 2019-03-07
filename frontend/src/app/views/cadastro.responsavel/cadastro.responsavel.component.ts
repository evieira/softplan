import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Validators,FormControl,FormGroup,FormBuilder} from '@angular/forms';

import {ResponsavelDTO} from "../../dto/responsavel.dto";
import {ResponsavelService} from "../../service/responsavel.service";
import {ProcessoService} from '../../service/processo.service';
import {ProcessoDTO} from "../../dto/processo.dto";
import {MensagemDTO} from "../../dto/mensagem.dto";

@Component({
  templateUrl: 'cadastro.responsavel.component.html'
})
export class CadastroResponsavelComponent implements OnInit {

  idResponsavel: string;
  listProcessos: ProcessoDTO[];
  mensagem: string;
  mensagemErro: string;
  formResponsavel:FormGroup;
  responsavel: ResponsavelDTO;
  submitted = false;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private responsavelService: ResponsavelService,
    private processoService: ProcessoService
  ) {}

  ngOnInit(): void {
    this.idResponsavel = this.route.snapshot.paramMap.get('id');
    this.getResponsavelPorId();
    this.formResponsavel = this.fb.group({
      'nome' : new FormControl('', Validators.required),
      'cpf' : new FormControl('', Validators.required)
    });
    this.responsavel = new ResponsavelDTO();
  }

  getResponsavelPorId() {
    this.responsavel = new ResponsavelDTO();
    if(this.idResponsavel != null) {
      this.responsavelService.getResponsavelPorId(this.idResponsavel)
        .subscribe((response) => {
          this.responsavel = response;
          this.getProcessosPorIdResponsavel();
        });
    }
  }

  getProcessosPorIdResponsavel() {
    this.processoService.getListaProcessosPorIdResponsavel(this.idResponsavel)
      .subscribe((response) => {
        this.listProcessos = response;
        console.log(this.listProcessos);
      });
  }

  get f() { return this.formResponsavel.controls; }

  salvar() {
    this.submitted = true;
    this.mensagem = null;
    this.mensagemErro = null;
    if (this.formResponsavel.invalid) {
      return;
    }
    this.responsavel.nome = this.formResponsavel.value.nome;
    this.responsavel.cpf = this.formResponsavel.value.cpf;
    this.responsavelService.salvar(this.responsavel)
      .subscribe((response) => {
        let mensagem: MensagemDTO;
        mensagem = response;
        this.mensagem = mensagem.mensagem;
      },(error => {
        this.mensagemErro = error.error.mensagem;
      }));
    this.submitted = false;
    this.responsavel = new ResponsavelDTO();
    this.formResponsavel.reset();
  }
}
