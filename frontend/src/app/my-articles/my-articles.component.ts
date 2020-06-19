import { Component, OnInit } from '@angular/core';
import { ArticleViewComponent } from '../article-view/article-view.component';
import { ArticleService } from '../services/article.service';

@Component({
  selector: 'app-my-articles',
  templateUrl: './my-articles.component.html',
  styleUrls: ['./my-articles.component.css'],
})


export class MyArticlesComponent implements OnInit {

  constructor(private ArticleService: ArticleService) { }
  status: String;
  articles: [];
  ngOnInit(): void {
    this.articles = [];
    this.status = "accepted";
    this.getArticles();

  }

  changeStatus(newstatus: String) {
    this.status = newstatus;
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
