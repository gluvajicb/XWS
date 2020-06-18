import { Component, OnInit } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router'
import { ArticleService } from '../services/article.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-view-article',
  templateUrl: './view-article.component.html',
  styleUrls: ['./view-article.component.css']
})
export class ViewArticleComponent implements OnInit {

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  private id: any;
  private sub: any;

  constructor(private route: ActivatedRoute,
              private ArticleService: ArticleService,
              private router: Router) { }

  ngOnInit() {
    this.logArticle();
  }

  logArticle() {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });


    this.ArticleService.getArticle(this.id).subscribe(
      result => {

        console.log(result);
      }
    );
  }

}
