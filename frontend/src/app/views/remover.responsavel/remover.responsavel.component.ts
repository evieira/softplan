import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import {ResponsavelDTO} from "../../dto/responsavel.dto";
import {ResponsavelService} from "../../service/responsavel.service";

@Component({
  templateUrl: './remover.responsavel.component.html'
})
export class RemoverResponsavelComponent implements OnInit {

  idResponsavel: string;
  mensagem: string;
  mensagemErro: string;
  responsavel: ResponsavelDTO;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private responsavelService: ResponsavelService) {}

  ngOnInit(): void {
    this.idResponsavel = this.route.snapshot.paramMap.get('id');
    this.getResponsavelPorId();
    this.responsavel = new ResponsavelDTO();
  }

  getResponsavelPorId() {
    this.responsavel = new ResponsavelDTO();
    if(this.idResponsavel != null) {
      this.responsavelService.getResponsavelPorId(this.idResponsavel)
        .subscribe((response) => {
          this.responsavel = response;
        });
    }
  }


  remover():void {
    this.mensagem = null;
    this.mensagemErro = null;
    this.responsavelService.remover(this.responsavel.id)
      .subscribe((response) => {
        this.mensagem = response.mensagem;
        console.log(response);
      },(error => {
        this.mensagemErro = error.error;
      }));
    this.responsavel = new ResponsavelDTO();
  }

  voltar(): void {
    console.log("voltar");
    this.location.back();
  }
}
