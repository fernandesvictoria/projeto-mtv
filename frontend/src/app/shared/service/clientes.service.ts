import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cliente } from '../model/cliente';
import { Observable } from 'rxjs';
import { ClienteSeletor } from '../seletor/cliente.seletor';

@Injectable({
  providedIn: 'root',
})
export class ClientesService {
  private readonly API = 'http://localhost:8080/projeto-mtv/rest/cliente';

  constructor(private httpClient: HttpClient) {}

  consultarTodosClientes(): Observable<Array<Cliente>>{
    return this.httpClient.get<Array<Cliente>>(this.API + '/todos')
  }


  listarComSeletor(seletor: ClienteSeletor): Observable<Array<Cliente>> {
    return this.httpClient.post<Array<Cliente>>(this.API + '/filtrar', seletor);
  }

  consultarVacinaID(idCliente: number): Observable<Cliente> {
    return this.httpClient.get<Cliente>(this.API + '/consultar/' + idCliente )
  }

  excluir (clienteID: number): Observable<boolean> {
    return this.httpClient.delete<boolean>(this.API + '/excluir/' + clienteID)
  }


  salvar(novoCliente: Cliente): Observable<Cliente> {
    return this.httpClient.post<Cliente>(this.API + '/salvar', novoCliente)
  }


  editar(clienteEditado: Cliente): Observable<boolean>{
    return this.httpClient.put<boolean>(this.API + '/alterar', clienteEditado)
  }
}
