import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { User } from '../../model/user';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  formUser: FormGroup;
  newUser: User = new User();

  constructor(private formBuilder: FormBuilder, private userService: UserService) {
    this.formUser = formBuilder.group({
      controlFirstName: '',
      controlLastName: '',
      controlMail: '',
      controlPassword: '',
    });
  }

  ngOnInit(): void {
  }

  onSubmit(formResponse: FormGroup) {
    if (formResponse.value.controlMail === '' || formResponse.value.controlPassword === '') {
      alert('Les champs email et password ne peuvent pas être vide');
    } else {
      this.newUser = <User> formResponse.value;
      this.addUser(this.newUser);
    }
  }

  addUser(newUser: User) {
    this.userService.addUser(newUser);
  }
}
