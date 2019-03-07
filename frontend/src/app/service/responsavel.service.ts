
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {ResponsavelDTO} from "../dto/responsavel.dto";

const HOST:string = "http://localhost:8080/responsaveis";

@Injectable({
  providedIn: 'root',
})
export class ResponsavelService {

  constructor(private httpClient:HttpClient) {}

  salvar(responsavelDTO: ResponsavelDTO): Observable<any> {
    return this.httpClient.post(HOST, responsavelDTO);
  }

  remover(id): Observable<any> {
    return this.httpClient.delete(HOST + "/id/"+id);
  }

  getResponsavelPorId(id): Observable<any> {
    return this.httpClient.get(HOST + "/id/" + id);
  }

  getResponsaveisPorNome(nome: string): Observable<any> {
    return this.httpClient.get(HOST + "/nome/" + nome);
  }

  getResponsaveisPorNomeCPFNumeroProcesso(nome:string, cpf:string, numeroProcesso:string) : Observable<any> {
    return this.httpClient.get(HOST + "/nome/" + nome +
      "/cpf/" + cpf + "/numeroProcesso/" + numeroProcesso);

  }



}
