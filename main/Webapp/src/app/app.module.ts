import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthenticationModule } from './authentication/authentication.module';
import { SharedModule } from './shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppGuard } from './app.guard';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ApiModule } from 'src/api/api.module';
import { RequestInterceptor } from 'src/app/shared/http/request-interceptor';
import { MAT_SNACK_BAR_DEFAULT_OPTIONS } from '@angular/material';

@NgModule({
  declarations: [AppComponent],
  imports: [
    SharedModule,
    TranslateModule.forRoot(),
    AppRoutingModule,
    AuthenticationModule,
    BrowserModule,
    BrowserAnimationsModule,
    ApiModule.forRoot({ rootUrl: '' })
  ],
  providers: [
    AppGuard,
    { provide: HTTP_INTERCEPTORS, multi: true, useClass: RequestInterceptor },
    { provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: { duration: 4000 } },
    RequestInterceptor
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
