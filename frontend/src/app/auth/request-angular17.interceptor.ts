import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { LoginService } from '../shared/service/login.service';

export const requestAngular17Interceptor: HttpInterceptorFn = (req, next) => {

  let authReq = req;

  if (typeof window !== 'undefined' && window.localStorage) {
    const usuarioAutenticado = localStorage.getItem('usuarioAutenticado');
    if (usuarioAutenticado) {
      const usuario = JSON.parse(usuarioAutenticado);
      authReq = req.clone({
        setHeaders: { idSessao: usuario.idSessao }
      });
    }
  }

  return next(authReq).pipe(
    catchError((error: HttpErrorResponse) => {
      const router = inject(Router);
      const loginService = inject(LoginService);
      if (error.status === 401 || error.status === 403) {
        loginService.sair();
        router.navigate(['/login']); // VERIFICAR ROTA
      }
      return throwError(error);
    })
  );
};
