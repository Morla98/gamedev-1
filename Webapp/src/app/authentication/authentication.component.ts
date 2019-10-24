import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {
  public name?: string;
  public password?: string;
  constructor(private router: Router) {}

  ngOnInit() {}

  login() {
    if (this.name === 'admin' && this.password === 'gameDev') {
      this.router.navigate(['/profile']);
    }
  }
}
