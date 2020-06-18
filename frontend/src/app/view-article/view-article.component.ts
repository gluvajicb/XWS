import { Component, OnInit } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router'
import { ArticleService } from '../services/article.service';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-view-article',
  templateUrl: './view-article.component.html',
  styleUrls: ['./view-article.component.css']
})
export class ViewArticleComponent implements OnInit {

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  private id: any;
  private sub: any;
  url: any;

  constructor(private route: ActivatedRoute,
              private ArticleService: ArticleService,
              private router: Router,
              private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.logArticle();
  }

  logArticle() {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    this.ArticleService.getPDF(this.id)
      .subscribe(
        res => {
          const file = new Blob([res], {type: 'application/pdf'});
          const fileURL = URL.createObjectURL(file);
          this.url = fileURL;
          console.log(fileURL);
          window.open(fileURL);
        }
      );
   
  }

}
