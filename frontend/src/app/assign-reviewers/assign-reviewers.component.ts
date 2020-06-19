import { Component, OnInit } from '@angular/core';
import { XmlParser } from '@angular/compiler';
import { UserService } from '../services/user/user.service';
import { HttpHeaders } from '@angular/common/http';
import { ProcessService } from '../services/process.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-assign-reviewers',
  templateUrl: './assign-reviewers.component.html',
  styleUrls: ['./assign-reviewers.component.css']
})
export class AssignReviewersComponent implements OnInit {

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  private id: any;
  private sub: any;

  users: any;
  private elements : string = "";

  constructor(private UserService: UserService,
              private ProcessService: ProcessService,
              private router: Router,
              private route: ActivatedRoute) { }

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
    let xml = "";
    let process = "";

    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    for(i = 0; i < this.users.length; i++) {
      if(this.users[i].checked) {
        console.log(this.users[i].id)
        xml += "<review_element>" +
                    "<review_id>" +
                    "</review_id>" +
                    "<reviewer_id>" + this.users[i].id +
                    "</reviewer_id>" +
                "</review_element>"
      }
    }

    
    process = "<process xmlns=" + "'http://www.uns.ac.rs/XMLtim/Process'" + ">" +
                    "<article_id>" + this.id + "</article_id>" +
                    "<author_id>" + "AUTHOR_ID_HERE" + "</author_id>" +
                    "<reviews>" + xml + "</reviews>" +
              "</process>"


    this.ProcessService.add(process as string).subscribe(
      result => {
        this.router.navigate(['new-articles']);
      }
    )

    console.log(process)
    console.log(xml)
    console.log("Iznad ovog")
  }

}
