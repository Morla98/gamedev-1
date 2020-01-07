/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { UserAchievementWOT } from '../models/user-achievement-wot';
import { UserAchievement } from '../models/user-achievement';
import { AchievementDto } from '../models/achievement-dto';
import { PreviewDto } from '../models/preview-dto';

/**
 * User Achievement Controller
 */
@Injectable({
  providedIn: 'root',
})
class UserAchievementControllerService extends __BaseService {
  static readonly updateUserAchievementsUsingPOSTPath = '/api/user-achievements';
  static readonly getAllUsersUsingGETPath = '/api/user-achievements/all';
  static readonly getUserAchievementsByCollectorIdUsingGETPath = '/api/user-achievements/by-collector-id';
  static readonly getUserAchievementsPreviewUsingGETPath = '/api/user-achievements/preview';
  static readonly getUserAchievementsCollectorPreviewUsingGETPath = '/api/user-achievements/preview-for-collector';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param u u
   */
  updateUserAchievementsUsingPOSTResponse(u: Array<UserAchievementWOT>): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = u;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/user-achievements`,
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
   * @param u u
   */
  updateUserAchievementsUsingPOST(u: Array<UserAchievementWOT>): __Observable<null> {
    return this.updateUserAchievementsUsingPOSTResponse(u).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @return OK
   */
  getAllUsersUsingGETResponse(): __Observable<__StrictHttpResponse<Array<UserAchievement>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/user-achievements/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<UserAchievement>>;
      })
    );
  }
  /**
   * @return OK
   */
  getAllUsersUsingGET(): __Observable<Array<UserAchievement>> {
    return this.getAllUsersUsingGETResponse().pipe(
      __map(_r => _r.body as Array<UserAchievement>)
    );
  }

  /**
   * @param collectorId collectorId
   * @return OK
   */
  getUserAchievementsByCollectorIdUsingGETResponse(collectorId: string): __Observable<__StrictHttpResponse<Array<AchievementDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (collectorId != null) __params = __params.set('collectorId', collectorId.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/user-achievements/by-collector-id`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<AchievementDto>>;
      })
    );
  }
  /**
   * @param collectorId collectorId
   * @return OK
   */
  getUserAchievementsByCollectorIdUsingGET(collectorId: string): __Observable<Array<AchievementDto>> {
    return this.getUserAchievementsByCollectorIdUsingGETResponse(collectorId).pipe(
      __map(_r => _r.body as Array<AchievementDto>)
    );
  }

  /**
   * @return OK
   */
  getUserAchievementsPreviewUsingGETResponse(): __Observable<__StrictHttpResponse<Array<PreviewDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/user-achievements/preview`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<PreviewDto>>;
      })
    );
  }
  /**
   * @return OK
   */
  getUserAchievementsPreviewUsingGET(): __Observable<Array<PreviewDto>> {
    return this.getUserAchievementsPreviewUsingGETResponse().pipe(
      __map(_r => _r.body as Array<PreviewDto>)
    );
  }

  /**
   * @param collectorId collectorId
   * @return OK
   */
  getUserAchievementsCollectorPreviewUsingGETResponse(collectorId: string): __Observable<__StrictHttpResponse<PreviewDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (collectorId != null) __params = __params.set('collectorId', collectorId.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/user-achievements/preview-for-collector`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<PreviewDto>;
      })
    );
  }
  /**
   * @param collectorId collectorId
   * @return OK
   */
  getUserAchievementsCollectorPreviewUsingGET(collectorId: string): __Observable<PreviewDto> {
    return this.getUserAchievementsCollectorPreviewUsingGETResponse(collectorId).pipe(
      __map(_r => _r.body as PreviewDto)
    );
  }
}

module UserAchievementControllerService {
}

export { UserAchievementControllerService }
