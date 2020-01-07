import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {
  public email?: string;
  public password?: string;
  constructor(private router: Router, private authService: AuthenticationService) {}

  ngOnInit() {}

  login() {
   this.authService.login(this.email, this.password);
  }
}
