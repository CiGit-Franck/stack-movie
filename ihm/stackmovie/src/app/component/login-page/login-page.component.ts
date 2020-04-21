import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';

import { UserService } from '../../service/user.service';
import { User } from '../../model/user';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  formLogin: FormGroup;
  currentUser: User;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) {
    this.formLogin = formBuilder.group({
      mail: new FormControl(''),
      password: new FormControl('')
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    let user:User = new User(this.formLogin.value);
    if (user.mail === '' || user.password === '') {
      alert('Champ non renseigné');
    }
    else {
      this.userService.getUserWithLogin(user.mail.toString(), user.password.toString()).subscribe(currentUser => {
        this.currentUser = currentUser;
      });
      this.router.navigate(['']);
    }
  }
}
