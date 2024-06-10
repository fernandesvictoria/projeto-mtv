import { Cliente } from "./cliente";
import { EstagioPeca } from "./enum/estagio-peca";
import { Tamanho } from "./enum/tamanho";
import { Tipo } from "./tipo";

export class Peca {
  idPeca: number;
  cliente: Cliente;
  tamanho: Tamanho;
  tipo: Tipo;
  descricao: string;
  estagio: EstagioPeca;
  valorTotal: number;
}
