import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly API = 'http://localhost:8080/projeto-mtv/rest/auth/login'; //verificar path do controller

  constructor(private httpClient: HttpClient) {}

  // fazer método para login

  sair() {
    localStorage.removeItem('usuarioAutenticado');
  }
}
