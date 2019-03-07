import {ResponsavelDTO} from "./responsavel.dto";

export class ProcessoDTO {

  id: number;
  numeroProcessoUnificado: string;
  pastaFisicaCliente: string;
  descricao: string;
  dtDistribuicao: Date;
  segredoDeJustica: boolean;
  situacao: string;
  responsaveis: string;
  processoPai: ProcessoDTO;
  processoFilho: ProcessoDTO;
  listaResponsavel: ResponsavelDTO[];

}
