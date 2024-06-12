import { Peca } from './../model/peca';

export class QueimaSeletor {
  idQueima?: number;
  dataQueima?: Date;
  tipoQueima?: string;
  peca?: Peca;
  forno?: string;
  pago?: boolean;
}
