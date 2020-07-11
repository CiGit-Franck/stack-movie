import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
      mail: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.formLogin.invalid) {
      alert('Champ(s) non renseigné(s)');
    }
    else {
      let user:User = new User(this.formLogin.value);
      this.userService.getUserWithLogin(user.mail.toString(), user.password.toString()).subscribe(currentUser => {
        this.currentUser = currentUser;
        if(this.currentUser === null ){
          alert('Utilisateur inconnu');
        }
        console.log(currentUser);
      });
      // @todo : affiche la liste ou retour login
      this.router.navigate(['']);
    }
  }
}
