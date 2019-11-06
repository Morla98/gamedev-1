/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { Achievement } from '../models/achievement';

/**
 * Achievement Controller
 */
@Injectable({
  providedIn: 'root',
})
class AchievementControllerService extends __BaseService {
  static readonly getAchievementByCollectorIdUsingGETPath = '/achievements';
  static readonly addAchievementUsingPOSTPath = '/achievements';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param controllerId controllerId
   * @return OK
   */
  getAchievementByCollectorIdUsingGETResponse(controllerId?: number): __Observable<__StrictHttpResponse<Array<Achievement>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (controllerId != null) __params = __params.set('controllerId', controllerId.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/achievements`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<Achievement>>;
      })
    );
  }
  /**
   * @param controllerId controllerId
   * @return OK
   */
  getAchievementByCollectorIdUsingGET(controllerId?: number): __Observable<Array<Achievement>> {
    return this.getAchievementByCollectorIdUsingGETResponse(controllerId).pipe(
      __map(_r => _r.body as Array<Achievement>)
    );
  }

  /**
   * @param a a
   */
  addAchievementUsingPOSTResponse(a: Achievement): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = a;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/achievements`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }
  /**
   * @param a a
   */
  addAchievementUsingPOST(a: Achievement): __Observable<null> {
    return this.addAchievementUsingPOSTResponse(a).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module AchievementControllerService {
}

export { AchievementControllerService }
