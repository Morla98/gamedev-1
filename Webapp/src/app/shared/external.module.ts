import { NgModule } from '@angular/core';
import {
  MatSidenavModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatButtonModule,
  MatSelectModule
} from '@angular/material';

@NgModule({
  declarations: [],
  exports: [MatSidenavModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatSelectModule]
})
export class ExternalModule {}
