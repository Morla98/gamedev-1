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

/**
 * User Achievement Controller
 */
@Injectable({
  providedIn: 'root',
})
class UserAchievementControllerService extends __BaseService {
  static readonly updateUserAchievementsUsingPOSTPath = '/api/user-achievements';
  static readonly getAllUserssUsingGETPath = '/api/user-achievements/all';
  static readonly getUserAchievementsByUserEmailUsingGETPath = '/api/user-achievements/by-user-email';
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
  getAllUserssUsingGETResponse(): __Observable<__StrictHttpResponse<Array<UserAchievement>>> {
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
  getAllUserssUsingGET(): __Observable<Array<UserAchievement>> {
    return this.getAllUserssUsingGETResponse().pipe(
      __map(_r => _r.body as Array<UserAchievement>)
    );
  }

  /**
   * @param params The `UserAchievementControllerService.GetUserAchievementsByUserEmailUsingGETParams` containing the following parameters:
   *
   * - `userEmail`: userEmail
   *
   * - `collectorId`: collectorId
   *
   * @return OK
   */
  getUserAchievementsByUserEmailUsingGETResponse(params: UserAchievementControllerService.GetUserAchievementsByUserEmailUsingGETParams): __Observable<__StrictHttpResponse<Array<AchievementDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.userEmail != null) __params = __params.set('userEmail', params.userEmail.toString());
    if (params.collectorId != null) __params = __params.set('collectorId', params.collectorId.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/user-achievements/by-user-email`,
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
   * @param params The `UserAchievementControllerService.GetUserAchievementsByUserEmailUsingGETParams` containing the following parameters:
   *
   * - `userEmail`: userEmail
   *
   * - `collectorId`: collectorId
   *
   * @return OK
   */
  getUserAchievementsByUserEmailUsingGET(params: UserAchievementControllerService.GetUserAchievementsByUserEmailUsingGETParams): __Observable<Array<AchievementDto>> {
    return this.getUserAchievementsByUserEmailUsingGETResponse(params).pipe(
      __map(_r => _r.body as Array<AchievementDto>)
    );
  }

  /**
   * @param userEmail userEmail
   * @return OK
   */
  getUserAchievementsPreviewUsingGETResponse(userEmail: string): __Observable<__StrictHttpResponse<Array<UserAchievement>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (userEmail != null) __params = __params.set('userEmail', userEmail.toString());
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
        return _r as __StrictHttpResponse<Array<UserAchievement>>;
      })
    );
  }
  /**
   * @param userEmail userEmail
   * @return OK
   */
  getUserAchievementsPreviewUsingGET(userEmail: string): __Observable<Array<UserAchievement>> {
    return this.getUserAchievementsPreviewUsingGETResponse(userEmail).pipe(
      __map(_r => _r.body as Array<UserAchievement>)
    );
  }

  /**
   * @param params The `UserAchievementControllerService.GetUserAchievementsCollectorPreviewUsingGETParams` containing the following parameters:
   *
   * - `userEmail`: userEmail
   *
   * - `collectorId`: collectorId
   *
   * @return OK
   */
  getUserAchievementsCollectorPreviewUsingGETResponse(params: UserAchievementControllerService.GetUserAchievementsCollectorPreviewUsingGETParams): __Observable<__StrictHttpResponse<Array<AchievementDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.userEmail != null) __params = __params.set('userEmail', params.userEmail.toString());
    if (params.collectorId != null) __params = __params.set('collectorId', params.collectorId.toString());
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
        return _r as __StrictHttpResponse<Array<AchievementDto>>;
      })
    );
  }
  /**
   * @param params The `UserAchievementControllerService.GetUserAchievementsCollectorPreviewUsingGETParams` containing the following parameters:
   *
   * - `userEmail`: userEmail
   *
   * - `collectorId`: collectorId
   *
   * @return OK
   */
  getUserAchievementsCollectorPreviewUsingGET(params: UserAchievementControllerService.GetUserAchievementsCollectorPreviewUsingGETParams): __Observable<Array<AchievementDto>> {
    return this.getUserAchievementsCollectorPreviewUsingGETResponse(params).pipe(
      __map(_r => _r.body as Array<AchievementDto>)
    );
  }
}

module UserAchievementControllerService {

  /**
   * Parameters for getUserAchievementsByUserEmailUsingGET
   */
  export interface GetUserAchievementsByUserEmailUsingGETParams {

    /**
     * userEmail
     */
    userEmail: string;

    /**
     * collectorId
     */
    collectorId: string;
  }

  /**
   * Parameters for getUserAchievementsCollectorPreviewUsingGET
   */
  export interface GetUserAchievementsCollectorPreviewUsingGETParams {

    /**
     * userEmail
     */
    userEmail: string;

    /**
     * collectorId
     */
    collectorId: number;
  }
}

export { UserAchievementControllerService }
