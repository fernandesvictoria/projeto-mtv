import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ClientesService {
  private readonly API = 'http://localhost:8080/projeto-mtv/rest/cliente';

  constructor(private httpClient: HttpClient) {}


  //Métodos DE ClienteController aqui.
}
