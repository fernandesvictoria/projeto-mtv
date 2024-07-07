import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../model/usuario';
import { UsuarioDTO } from '../model/usuario-dto';

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  private readonly API = 'http://localhost:8080/projeto-mtv/rest/auth'; //verificar path do controller

  constructor(private httpClient: HttpClient) {}

  autenticar(loginRequest: UsuarioDTO): Observable<Usuario> {
    return this.httpClient.post<Usuario>(this.API + '/autenticar', loginRequest)
  }

  salvar(novoUsuario: Usuario): Observable<Usuario> {
    return this.httpClient.post<Usuario>(this.API + '/cadastrar', novoUsuario)
  }

  sair() {
    localStorage.removeItem('usuarioAutenticado');
  }
}
