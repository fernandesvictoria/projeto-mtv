import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Queima } from '../model/queima';
import { QueimaSeletor } from '../seletor/queima.seletor';

@Injectable({
  providedIn: 'root'
})
export class QueimasService {

  private readonly API = 'http://localhost:8080/rest/queima';
  constructor(private httpClient: HttpClient) { }

  consultarTodasQueimas(): Observable<Array<Queima>> {
    return this.httpClient.get<Array<Queima>>(this.API + '/todos');
  }

  salvarQueima(novaQueima: Queima): Observable<Queima> {
    return this.httpClient.post<Queima>(this.API + '/salvar', novaQueima);
  }

  alterarQueima(queimaAlterada: Queima): Observable<boolean> {
    return this.httpClient.put<boolean>(this.API + '/alterar', queimaAlterada);
  }

  excluirQueima(id: number): Observable<boolean> {
    return this.httpClient.delete<boolean>(`${this.API}/excluir/${id}`);
  }

  consultarQueimaPorId(id: number): Observable<Queima> {
    return this.httpClient.get<Queima>(`${this.API}/consultar/${id}`);
  }

  filtrarQueimas(seletor: QueimaSeletor): Observable<Array<Queima>> {
    return this.httpClient.post<Array<Queima>>(this.API + '/filtrar', seletor);
  }
}
