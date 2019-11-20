import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthenticationModule } from './authentication/authentication.module';
import { SharedModule } from './shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppGuard } from './app.guard';
import { HttpClientModule } from '@angular/common/http';
import { ApiModule } from 'src/api/api.module';

@NgModule({
  declarations: [AppComponent],
  imports: [
    SharedModule,
    TranslateModule.forRoot(),
    AppRoutingModule,
    AuthenticationModule,
    BrowserModule,
    BrowserAnimationsModule,
    ApiModule.forRoot({rootUrl: 'http://localhost:8082'}),
  ],
  providers: [AppGuard],
  bootstrap: [AppComponent]
})
export class AppModule {}
