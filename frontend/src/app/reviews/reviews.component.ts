import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/compiler/src/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ReviewService } from '../services/review.service';
import { FormBuilder } from '@angular/forms';
import {} from 'xml2js'


@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent implements OnInit {

  reviews: JSON[];

  private id: any;
  private sub: any;

  constructor(private router: Router,
              private ReviewService: ReviewService,
              private fb: FormBuilder,
              private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.reviews = [];
    this.getReviews();
  }

  getReviews() {

    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    this.ReviewService.getReviewsForArticle(this.id).subscribe(
        result => {

            this.reviews = result
            console.log("Ovde je result")
            console.log(result)

            /* ... */
            let parseString = require('xml2js').parseString;
            let i;
            for (i = 0; i < result.length; i++) {

                let res;

                parseString(result[i], function(err, result1){
                  console.log(result1)
                  console.log(result1["review"]["overall_recommendation"])

                  let retVal = {
                    "overall_recommendation": result1["review"]["overall_recommendation"],
                    "comments": result1["review"]["comments"],
                    "confidential-comments": result1["review"]["confidential-comments"],
                  }

                  res = retVal
                });

                this.reviews[i] = res

            }
        }
    ); 
  }

}
