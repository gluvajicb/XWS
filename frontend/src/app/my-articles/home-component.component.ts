import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../services/article.service';
import { SearchParams } from '../model/searchArticle';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {

  articles: JSON[];

  constructor(private ArticleService: ArticleService,
              private router: Router,
              private fb: FormBuilder) {

  }

  

  ngOnInit() {
    this.articles = []
    this.getAllSubmitted();
  }

  getAllSubmitted() {
    this.ArticleService.getAllSubmitted().subscribe(
      result => {
        this.articles = result
        console.log(this.articles);

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
              "keywords" : keywords
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

  

}
