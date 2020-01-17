import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Observable } from 'rxjs';

export class RequestInterceptor implements HttpInterceptor {
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    const token = sessionStorage.getItem('jwt');

    // this allows a flexible host for the website
    const baseUrl = window.location.protocol + '//' + window.location.host;

    // if a token is present in the browser storage it will be added to every request
    if (token) {
      request = request.clone({
        setHeaders: {
          'X-Auth-Token': token
        },
        url: baseUrl + request.url
      });
    } else {
      request = request.clone({ url: baseUrl + request.url });
    }
    return next.handle(request);
  }
}
