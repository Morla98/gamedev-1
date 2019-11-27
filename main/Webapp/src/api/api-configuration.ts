/* tslint:disable */
import { Injectable } from '@angular/core';

/**
 * Global configuration for Api services
 */
@Injectable({
  providedIn: 'root',
})
export class ApiConfiguration {
  rootUrl: string = window.location.protocol + '//' + window.location.host; //'//localhost:8082';
}

export interface ApiConfigurationInterface {
  rootUrl?: string;
}
