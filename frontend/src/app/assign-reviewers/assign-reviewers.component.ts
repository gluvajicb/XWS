import { Component, OnInit } from '@angular/core';
import { XmlParser } from '@angular/compiler';
import { UserService } from '../services/user/user.service';

@Component({
  selector: 'app-assign-reviewers',
  templateUrl: './assign-reviewers.component.html',
  styleUrls: ['./assign-reviewers.component.css']
})
export class AssignReviewersComponent implements OnInit {

  users: any;
  private elements : string = "";

  constructor(private UserService: UserService) { }

  ngOnInit() {
    this.getAllReviewers();
  }

  getAllReviewers() {
    this.UserService.getAllReviewers().subscribe(
      result => {
        this.users = result
        let i;
        for(i = 0; i < this.users.length; i++) {
          this.users[i].checked = false;
        }
      }
    );
  }
  makeReviewElement(){
      console.log("Ovo je ako je checkbox1 = true")
      
      let reviewElement = "<review_element>" +
                          "<review_id>" +
                          "</review_id>" +
                          "<reviewer_id>" +
                          "</reviewer_id>" +
                     "</review_element>"
  }

  click(ev){
    console.log(ev.target.defaultValue);
    this.elements //da bude lista a ne string i da se u tu listu doda defaultValue ukoliko je kliknut, a ukoliko nije - da se izbrise iz liste ukoliko postoji
  }

  assign(){
    let i;
    for(i = 0; i < this.users.length; i++) {
      if(this.users[i].checked) {
        console.log(this.users[i].id)
      }
    }
  }

}
