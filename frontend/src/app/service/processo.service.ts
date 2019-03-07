import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {ProcessoDTO} from "../dto/processo.dto";

const HOST:string = "http://localhost:8080/processos";

@Injectable({
  providedIn: 'root',
})
export class ProcessoService {

  constructor(private httpClient:HttpClient) {}

  salvarProcesso(processo:ProcessoDTO): Observable<any> {
    return this.httpClient.post(HOST, processo);
  }

  removerProcesso(id): Observable<any> {
    return this.httpClient.delete(HOST + "/id/" + id);
  }

  getListaSituacao(): Observable<any> {
    return this.httpClient.get(HOST + "/listaSituacao");
  }

  getProcessoPorId(id): Observable<any> {
    return this.httpClient.get(HOST + "/id/" + id);
  }

  getListaProcessosPorIdResponsavel(id): Observable<any> {
    return this.httpClient.get(HOST + "/porIdResponsavel/idResponsavel/" + id);
  }

  getListaProcessosPorNumeroProcesso(numeroProcesso): Observable<any> {
    return this.httpClient.get(HOST + "/numeroProcesso/" + numeroProcesso);
  }

  getListaProcessosPorFiltros(numeroProcesso,
                              dtInicioDistribuicao,
                              dtFimDistribuicao,
                              situacao,
                              segredoJustica,
                              pastaFisicaCliente,
                              responsavel): Observable<any> {
    return this.httpClient.get(HOST + "/numeroProcesso/" + numeroProcesso +
                              "/dtInicioDistribuicao/" + dtInicioDistribuicao +
                              "/dtFimDistribuicao/" + dtFimDistribuicao +
                              "/situacao/" + situacao +
                              "/segredoJustica/" + segredoJustica +
                              "/pastaFisicaCliente/" + pastaFisicaCliente +
                              "/responsavel/" + responsavel);
  }
}
