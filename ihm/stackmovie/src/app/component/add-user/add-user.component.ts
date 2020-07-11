import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { User } from '../../model/user';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  formUser: FormGroup;
  currentUser: User;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) {
    this.formUser = formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      mail: ['', Validators.required],
      password: ['', Validators.required],
      repassword: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    let addUser = new User(this.formUser.value);
    if (this.formUser.invalid) {
      alert('Les champs email et password ne peuvent pas être vide');
    } else if (addUser.password !== this.formUser.get('repassword').value) {
      alert('Les mots de passe sont différents');
    } else {
      this.userService.addUser(addUser).subscribe(currentUser => {
        this.currentUser = currentUser;
      });
      this.router.navigate(['']);
    }
  }
}
