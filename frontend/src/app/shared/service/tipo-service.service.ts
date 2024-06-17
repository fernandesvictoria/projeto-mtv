import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tipo } from '../model/tipo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TipoServiceService {

  private readonly API = 'http://localhost:8080/projeto-mtv/rest/tipo';
  constructor(private httpClient: HttpClient) {}


  salvarTipo(novoTipo: Tipo): Observable<Tipo> {
    return this.httpClient.post<Tipo>(this.API + '/salvar', novoTipo);
  }

  excluirTipo(id: number): Observable<boolean> {
    return this.httpClient.delete<boolean>(this.API + '/excluir/' + id);
  }

  editarTipo(tipoAlterado: Tipo): Observable<boolean>{
    return this.httpClient.put<boolean>(this.API + '/alterar', tipoAlterado)
  }

  consultarTodosTipos(): Observable<Array<Tipo>> {
    return this.httpClient.get<Array<Tipo>>(this.API + '/todos');
  }

  consultarTipoPorId(id: number): Observable<Tipo> {
    return this.httpClient.get<Tipo>(this.API + '/consultar/' + id)
  }

}
