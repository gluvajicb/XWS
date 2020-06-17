import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { ReviewService } from '../services/review.service';

declare var Xonomy: any;

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private router: Router,
              private ReviewService: ReviewService,
              private route: ActivatedRoute) { }

  private id: any;
  private sub: any;

  docSpec = {

    elements: {

      review: {

      },

      article_id: {

      },

      overall_recommendation: {
        hasText: true,
        asker: Xonomy.askPicklist,
        askerParameter: ['accept', 'reject', 'major_revisions', 'minor_revisions']
      },

      questionnaire: {
        menu: [{
          caption: 'Append <question_element>',
          action: Xonomy.newElementChild,
          actionParameter: '<question_element></question_element>'
        }]

      },

      question_element: {
        menu: [{
          caption: 'Append a <question>',
          action: Xonomy.newElementChild,
          actionParameter: '<question></question>'
          }, {
            caption: 'Append a <response>',
            action: Xonomy.newElementChild,
            actionParameter: '<response></response>'
          }        
        ]
      },

      question: {
        hasText: true
      },

      response: {
        hasText: true,
        asker: Xonomy.askPicklist,
        askerParameter: ['strongly disagree', 'disagree', 'neutral', 'agree', 'strongly agree']
      },

      comments: {
        hasText: true
      },

      "confidental-comments": {
        hasText: true
      }


    }
  };

  ngOnInit() {
    this.start();
  }

  start(){

    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    const xml = '<review xmlns="http://www.uns.ac.rs/XMLtim/Review" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:co="http://www.uns.ac.rs/XMLtim/Review" co:ID="ID1">' + 
                    '<article_id>' + this.id + '</article_id>' +
                    '<overall_recommendation>' +
                    '</overall_recommendation>' +
                    '<questionnaire>' +
                    '</questionnaire>' +
                    '<comments>' +
                    '</comments>' +
                    '<confidental-comments>' +
                    '</confidental-comments>' +
                '</review>';
    
    const editor = document.getElementById('editor');
    Xonomy.render(xml, editor, this.docSpec);

  }

  submit() {
    const xml = Xonomy.harvest();

    console.log(xml);
    this.ReviewService.add(xml as string).subscribe(
      result => {
        this.router.navigate(['home']);
      }
    );
  }

}
