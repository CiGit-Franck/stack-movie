import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
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
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      mail: new FormControl(''),
      password: new FormControl('')
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    let addUser = new User(this.formUser.value);
    if (addUser.mail === '' || addUser.password === '') {
      alert('Les champs email et password ne peuvent pas être vide');
    } else {
      this.userService.addUser(addUser).subscribe(currentUser => {
        this.currentUser = currentUser;
      });
      this.router.navigate(['']);
    }
  }
}
