import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationComponent } from './authentication.component';
import { SharedModule } from '../shared/shared.module';
import { AuthenticationService } from './authentication.service';

@NgModule({
  declarations: [AuthenticationComponent],
  imports: [
    SharedModule
  ],
  providers:[AuthenticationService]
})
export class AuthenticationModule { }
