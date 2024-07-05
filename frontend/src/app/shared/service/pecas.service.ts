import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Peca } from '../model/peca';
import { PecaSeletor } from '../seletor/peca.seletor';

@Injectable({
  providedIn: 'root',
})
export class PecasService {
  private readonly API = 'http://localhost:8080/projeto-mtv/rest/restrito/peca';

  constructor(private httpClient: HttpClient) {}

  excluirPeca(id: number): Observable<boolean> {
    return this.httpClient.delete<boolean>(this.API + '/excluir/' + id);
  }

  salvarPeca(novaPeca: Peca): Observable<Peca> {
    return this.httpClient.post<Peca>(this.API + '/salvar', novaPeca);
  }

  editarPeca(pecaEditada: Peca): Observable<boolean> {
    return this.httpClient.put<boolean>(this.API + '/alterar', pecaEditada);
  }

  consultarTodasPecas(): Observable<Array<Peca>> {
    return this.httpClient.get<Array<Peca>>(this.API + '/todos');
  }

  consultarPecasComFiltros(pecaSeletor: PecaSeletor): Observable<Array<Peca>> {
    return this.httpClient.post<Array<Peca>>(this.API + '/filtrar', pecaSeletor);
  }

  consultarPecaPorId(id: number): Observable<Peca> {
    return this.httpClient.get<Peca>(this.API + '/consultar/' + id)
  }

}
