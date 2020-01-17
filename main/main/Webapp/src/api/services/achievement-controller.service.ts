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
  static readonly addAchievementListUsingPOSTPath = '/api/achievements';
  static readonly getAllAchievementsUsingGETPath = '/api/achievements/all';
  static readonly getAchievementByCollectorIdUsingGETPath = '/api/achievements/by-collector-id';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param achievements achievements
   */
  addAchievementListUsingPOSTResponse(achievements: Array<Achievement>): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = achievements;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/achievements`,
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
   * @param achievements achievements
   */
  addAchievementListUsingPOST(achievements: Array<Achievement>): __Observable<null> {
    return this.addAchievementListUsingPOSTResponse(achievements).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @return OK
   */
  getAllAchievementsUsingGETResponse(): __Observable<__StrictHttpResponse<Array<Achievement>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/achievements/all`,
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
   * @return OK
   */
  getAllAchievementsUsingGET(): __Observable<Array<Achievement>> {
    return this.getAllAchievementsUsingGETResponse().pipe(
      __map(_r => _r.body as Array<Achievement>)
    );
  }

  /**
   * @param collectorId collectorId
   * @return OK
   */
  getAchievementByCollectorIdUsingGETResponse(collectorId: string): __Observable<__StrictHttpResponse<Array<Achievement>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (collectorId != null) __params = __params.set('collectorId', collectorId.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/achievements/by-collector-id`,
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
   * @param collectorId collectorId
   * @return OK
   */
  getAchievementByCollectorIdUsingGET(collectorId: string): __Observable<Array<Achievement>> {
    return this.getAchievementByCollectorIdUsingGETResponse(collectorId).pipe(
      __map(_r => _r.body as Array<Achievement>)
    );
  }
}

module AchievementControllerService {
}

export { AchievementControllerService }
