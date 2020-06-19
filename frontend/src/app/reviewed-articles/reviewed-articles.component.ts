import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../services/article.service';

@Component({
  selector: 'app-reviewed-articles',
  templateUrl: './reviewed-articles.component.html',
  styleUrls: ['./reviewed-articles.component.css']
})
export class ReviewedArticlesComponent implements OnInit {


  constructor(private ArticleService: ArticleService) { }
  articles: [];
  status: string;
  ngOnInit(): void {
    this.status = "reviewing";
    this.articles = [];
    this.getArticles();
  }

  getArticles() {
    var searchParams_JSON =
                     {
                         "abst": "" ,
                         "title": "",
                         "keyword": "",
                         "author": "",
                         "section": "",
                         "status": this.status
                     };

    console.log(searchParams_JSON)

    this.ArticleService.searchArticle(searchParams_JSON).subscribe(
      result => {
        this.articles = result
        console.log(result)
      }
    );
  }
}
