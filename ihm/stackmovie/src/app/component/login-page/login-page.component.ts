import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';

import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  formLogin;

  constructor(private formBuilder: FormBuilder, private userService: UserService) {
    this.formLogin = formBuilder.group({
      controlMail: '',
      controlPassword: '',
    });
  }

  ngOnInit(): void {
  }

  onSubmit(formLogin) {
    if (formLogin.controlMail === '' || formLogin.controlPassword === '') {
      alert('Champ non renseigné');
    }
    else {
      this.userService.getUserWithLogin(formLogin.controlMail, formLogin.controlPassword);
    }
  }
}
