import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';

declare var Xonomy: any;

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  constructor(private router: Router) { }

  doSpec = {

    elements: {

      article_id: {

      },

      overall_recommendation: {

      },

      questionnaire: {
        menu: [{
          caption: 'Append <question_element>',
          action: Xonomy.newElementChild,
          actionParameter: '<question_element></question_element>'
        }]

      },

      question_element: {

      },

      question: {

      },

      response: {

      },

      comments: {

      }


    }
  };

  ngOnInit() {
    this.start();
  }

  start(){

    

  }

}
