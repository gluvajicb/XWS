import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../services/article.service';

@Component({
  selector: 'app-new-articles',
  templateUrl: './new-articles.component.html',
  styleUrls: ['./new-articles.component.css']
})
export class NewArticlesComponent implements OnInit {

  constructor(private ArticleService: ArticleService) { }
  articles: [];
  status: string;
  ngOnInit(): void {
    this.status = "in_progress";
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
