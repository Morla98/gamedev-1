/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';


/**
 * Authentication Controller
 */
@Injectable({
  providedIn: 'root',
})
class AuthenticationControllerService extends __BaseService {
  static readonly loginUsingGETPath = '/api/auth/login';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param params The `AuthenticationControllerService.LoginUsingGETParams` containing the following parameters:
   *
   * - `password`: password
   *
   * - `email`: email
   *
   * @return OK
   */
  loginUsingGETResponse(params: AuthenticationControllerService.LoginUsingGETParams): __Observable<__StrictHttpResponse<string>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.password != null) __params = __params.set('password', params.password.toString());
    if (params.email != null) __params = __params.set('email', params.email.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/auth/login`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<string>;
      })
    );
  }
  /**
   * @param params The `AuthenticationControllerService.LoginUsingGETParams` containing the following parameters:
   *
   * - `password`: password
   *
   * - `email`: email
   *
   * @return OK
   */
  loginUsingGET(params: AuthenticationControllerService.LoginUsingGETParams): __Observable<string> {
    return this.loginUsingGETResponse(params).pipe(
      __map(_r => _r.body as string)
    );
  }
}

module AuthenticationControllerService {

  /**
   * Parameters for loginUsingGET
   */
  export interface LoginUsingGETParams {

    /**
     * password
     */
    password: string;

    /**
     * email
     */
    email: string;
  }
}

export { AuthenticationControllerService }
