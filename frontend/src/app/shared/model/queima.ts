import { Peca } from "./peca";

export class Queima {
  idQueima: number;
  dataQueima: Date;
  tipoQueima: string;
  temperaturaAlcancada: number;
  peca: Peca;
  forno: string;
  precoQueima: number;
  pago: boolean;
}
