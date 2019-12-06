/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { CollectorWOT } from '../models/collector-wot';
import { Collector } from '../models/collector';

/**
 * Collector Controller
 */
@Injectable({
  providedIn: 'root',
})
class CollectorControllerService extends __BaseService {
  static readonly addCollectorUsingPOSTPath = '/api/collectors';
  static readonly getAllCollectorsUsingGETPath = '/api/collectors/all';
  static readonly getCollectorsByIdUsingGETPath = '/api/collectors/by-id';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param params The `CollectorControllerService.AddCollectorUsingPOSTParams` containing the following parameters:
   *
   * - `secret`: secret
   *
   * - `collectorWOT`: collectorWOT
   *
   * @return OK
   */
  addCollectorUsingPOSTResponse(params: CollectorControllerService.AddCollectorUsingPOSTParams): __Observable<__StrictHttpResponse<string>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.secret != null) __params = __params.set('secret', params.secret.toString());
    __body = params.collectorWOT;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/collectors`,
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
   * @param params The `CollectorControllerService.AddCollectorUsingPOSTParams` containing the following parameters:
   *
   * - `secret`: secret
   *
   * - `collectorWOT`: collectorWOT
   *
   * @return OK
   */
  addCollectorUsingPOST(params: CollectorControllerService.AddCollectorUsingPOSTParams): __Observable<string> {
    return this.addCollectorUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as string)
    );
  }

  /**
   * @return OK
   */
  getAllCollectorsUsingGETResponse(): __Observable<__StrictHttpResponse<Array<Collector>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/collectors/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<Collector>>;
      })
    );
  }
  /**
   * @return OK
   */
  getAllCollectorsUsingGET(): __Observable<Array<Collector>> {
    return this.getAllCollectorsUsingGETResponse().pipe(
      __map(_r => _r.body as Array<Collector>)
    );
  }

  /**
   * @param id id
   * @return OK
   */
  getCollectorsByIdUsingGETResponse(id: string): __Observable<__StrictHttpResponse<Array<Collector>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (id != null) __params = __params.set('id', id.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/collectors/by-id`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<Collector>>;
      })
    );
  }
  /**
   * @param id id
   * @return OK
   */
  getCollectorsByIdUsingGET(id: string): __Observable<Array<Collector>> {
    return this.getCollectorsByIdUsingGETResponse(id).pipe(
      __map(_r => _r.body as Array<Collector>)
    );
  }
}

module CollectorControllerService {

  /**
   * Parameters for addCollectorUsingPOST
   */
  export interface AddCollectorUsingPOSTParams {

    /**
     * secret
     */
    secret: string;

    /**
     * collectorWOT
     */
    collectorWOT: CollectorWOT;
  }
}

export { CollectorControllerService }
