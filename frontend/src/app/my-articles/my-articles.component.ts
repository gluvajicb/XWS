import { Component, OnInit } from '@angular/core';
import { ArticleViewComponent } from '../article-view/article-view.component';
import { ArticleService } from '../services/article.service';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import {} from 'xml2js'


@Component({
  selector: 'app-my-articles',
  templateUrl: './my-articles.component.html',
  styleUrls: ['./my-articles.component.css'],
})


export class MyArticlesComponent implements OnInit {

  constructor(private ArticleService: ArticleService,
              private router: Router,
              private fb: FormBuilder) { }
  status: String;
  articles: JSON[];


  ngOnInit() {
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

    //console.log(searchParams_JSON)

    this.ArticleService.searchArticle(searchParams_JSON).subscribe(
      result => {
//        this.articles = result
        this.articles.splice(0, this.articles.length)
        console.log("Rezultat ispod")
        console.log(result)

       /* */
       let parseString = require('xml2js').parseString;
       let i;

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

       console.log(this.articles)
       console.log(this.articles[0]["ns1:article"]["ns1:abstract"][0]["ns1:keywords"][0]["ns1:keyword"][0])
       let a = this.articles[0];
       console.log(a["ns1:article"]["ns1:authors"])
       
     }
   );
  }

  submit(id: string) {
    this.ArticleService.submit(id).subscribe(
      result => { 
        
      }
    );
    this.getArticles();
    console.log(id)
  }

  delete(id: string) {
    this.ArticleService.delete(id).subscribe(
      result => { 
      }
    );
    this.getArticles();
    console.log(id)
  }

  edit(id: string) {
    this.router.navigate(['/edit-article/' + id]);

  }
  reviews(id:string) {
    this.router.navigate(['/reviews/' + id]);
  }

}
