import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExternalModule } from './external.module';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
  declarations: [],
  exports: [
    CommonModule,
    ExternalModule,
    TranslateModule,
    FormsModule
  ]
})
export class SharedModule {}
