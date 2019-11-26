import { NgModule } from '@angular/core';
import {
  MatSidenavModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatButtonModule,
  MatSelectModule,
  MatTableModule,
  MatIconModule,
  MatSnackBarModule
} from '@angular/material';

@NgModule({
  declarations: [],
  exports: [
    MatSidenavModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatTableModule,
    MatCardModule,
    MatSnackBarModule,
    MatIconModule
  ]
})
export class ExternalModule {}
