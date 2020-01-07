/* tslint:disable */
import { NgModule, ModuleWithProviders } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ApiConfiguration, ApiConfigurationInterface } from './api-configuration';

import { AchievementControllerService } from './services/achievement-controller.service';
import { AuthenticationControllerService } from './services/authentication-controller.service';
import { CollectorControllerService } from './services/collector-controller.service';
import { UserAchievementControllerService } from './services/user-achievement-controller.service';
import { UserControllerService } from './services/user-controller.service';

/**
 * Provider for all Api services, plus ApiConfiguration
 */
@NgModule({
  imports: [
    HttpClientModule
  ],
  exports: [
    HttpClientModule
  ],
  declarations: [],
  providers: [
    ApiConfiguration,
    AchievementControllerService,
    AuthenticationControllerService,
    CollectorControllerService,
    UserAchievementControllerService,
    UserControllerService
  ],
})
export class ApiModule {
  static forRoot(customParams: ApiConfigurationInterface): ModuleWithProviders {
    return {
      ngModule: ApiModule,
      providers: [
        {
          provide: ApiConfiguration,
          useValue: {rootUrl: customParams.rootUrl}
        }
      ]
    }
  }
}
