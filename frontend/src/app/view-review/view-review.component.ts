import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReviewService } from '../services/review.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-view-review',
  templateUrl: './view-review.component.html',
  styleUrls: ['./view-review.component.css']
})
export class ViewReviewComponent implements OnInit {

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  private id: any;
  private sub: any;

  constructor(private route: ActivatedRoute,
              private reviewService: ReviewService,
              private router: Router) { }

  ngOnInit() {
    this.logReview();
  }

  logReview() {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });


    this.reviewService.getReview(this.id).subscribe(
      result => {

        console.log(result);
      }
    );
  }

}
