import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Peca } from '../model/peca';

@Injectable({
  providedIn: 'root'
})
export class PecasService {

  private readonly API = 'http://localhost:8080/projeto-mtv/rest/peca'
  constructor(private httpClient: HttpClient) { }

  consultarTodasPecas(): Observable<Array<Peca>>{
    return this.httpClient.get<Array<Peca>>(this.API + '/todos')
  }

}
