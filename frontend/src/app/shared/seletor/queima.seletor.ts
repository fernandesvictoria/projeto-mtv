import { Peca } from './../model/peca';

export class QueimaSeletor {
  idQueima?: number;
  dataInicio?: Date;
  dataFim?: Date;
  tipoQueima?: string;
  peca?: Peca;
  forno?: string;
  pago?: boolean;
}
