import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../services/article.service';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-articles',
  templateUrl: './new-articles.component.html',
  styleUrls: ['./new-articles.component.css']
})
export class NewArticlesComponent implements OnInit {

  constructor(private ArticleService: ArticleService, private router: Router) { }
  articles: JSON[];
  status: string;
  ngOnInit(): void {
    this.status = "in_progress";
    this.articles = [];
    this.getArticles();
  }

  assign(id: string) {
    this.router.navigate(['/assign-reviewers/' + id]);

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
        let parseString = require('xml2js').parseString;
        let i;
        this.articles.splice(0, this.articles.length)
        for (i = 0; i < result.length; i++) {
          console.log(result[i]);
          let res;
          let authors = [];
          let keywords = [];
          
          parseString(result[i], function (err, result1) {
            
            console.log(result1)

            let j, k;
            let authors = []
            for(j = 0; j < result1["ns1:article"]["ns1:authors"][0]["ns1:author"].length; j ++){

              authors[j] = result1["ns1:article"]["ns1:authors"][0]["ns1:author"][j]["ns1:name"] + " " + result1["ns1:article"]["ns1:authors"][0]["ns1:author"][j]["ns1:surname"];
            }
            if(result1["ns1:article"]["ns1:abstract"][0]["ns1:keywords"] !== undefined) {
              for(k = 0; k < result1["ns1:article"]["ns1:abstract"][0]["ns1:keywords"].length; k++){

                keywords[k] = result1["ns1:article"]["ns1:abstract"][0]["ns1:keywords"][k]["ns1:keyword"];
              }
            }
            
            let retVal = {
              "title": result1["ns1:article"]["ns1:title"],
              "authors": authors,
              "keywords" : keywords,
              "id": result1["ns1:article"]["$"]["ns1:ID"]

            }
            res = retVal
          });

          this.articles[i] = res
        }
      }
    );
  }

}
