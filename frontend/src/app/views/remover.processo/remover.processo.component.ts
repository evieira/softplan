import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import {ProcessoDTO} from "../../dto/processo.dto";
import {ProcessoService} from "../../service/processo.service";

@Component({
  templateUrl: './remover.processo.component.html'
})
export class RemoverProcessoComponent implements OnInit {

  idProcesso: string;
  mensagem: string;
  mensagemErro: string;
  processo: ProcessoDTO;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private processoService: ProcessoService) {}

  ngOnInit(): void {
    this.idProcesso = this.route.snapshot.paramMap.get('id');
    this.getResponsavelPorId();
    this.processo = new ProcessoDTO();
  }

  getResponsavelPorId() {
    this.processo = new ProcessoDTO();
    if(this.processo != null) {
      this.processoService.getProcessoPorId(this.idProcesso)
        .subscribe((response) => {
          this.processo = response;
        });
    }
  }


  remover():void {
    this.mensagem = null;
    this.mensagemErro = null;
    this.processoService.removerProcesso(this.processo.id)
      .subscribe((response) => {
        this.mensagem = response.mensagem;
        console.log(response);
        this.processo = new ProcessoDTO();
      },(error => {
        this.mensagemErro = error.error;
      }));
  }

  voltar(): void {
    console.log("voltar");
    this.location.back();
  }
}
